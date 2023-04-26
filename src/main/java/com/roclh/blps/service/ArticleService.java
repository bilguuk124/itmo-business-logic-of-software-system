package com.roclh.blps.service;

import com.roclh.blps.Exceptions.ArticleNotFoundException;
import com.roclh.blps.database.AccountDatabase;
import com.roclh.blps.database.CommentDatabase;
import com.roclh.blps.database.StudopediaDatabase;
import com.roclh.blps.entities.Account;
import com.roclh.blps.entities.Comment;
import com.roclh.blps.entities.StudopediaArticle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {
    private final Logger log = LogManager.getLogger(ArticleService.class);
    private final StudopediaDatabase studopediaDatabase;
    private final CommentDatabase commentDatabase;
    private final AccountDatabase accountDatabase;

    @Autowired
    public ArticleService(StudopediaDatabase studopediaDatabase, CommentDatabase commentDatabase, AccountDatabase accountDatabase) {
        this.studopediaDatabase = studopediaDatabase;
        this.commentDatabase = commentDatabase;
        this.accountDatabase = accountDatabase;
    }


    @Transactional
    public void upAnArticle(Long articleId) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = studopediaDatabase.findByIdEquals(articleId);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        StudopediaArticle article = optional.get();
        article.addUp();
        studopediaDatabase.save(article);
    }

    @Transactional
    public void downAnArticle(Long articleId) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = studopediaDatabase.findByIdEquals(articleId);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        StudopediaArticle article = optional.get();
        article.addDown();
        studopediaDatabase.save(article);
    }

    @Transactional
    public void cancelUpAnArticle(Long articleId) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = studopediaDatabase.findByIdEquals(articleId);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        StudopediaArticle article = optional.get();
        article.removeUp();
        studopediaDatabase.save(article);
    }

    @Transactional
    public void cancelDownAnArticle(Long articleId) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = studopediaDatabase.findByIdEquals(articleId);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        StudopediaArticle article = optional.get();
        article.removeDown();
        studopediaDatabase.save(article);
    }

    @Transactional
    public void commentArticle(Long articleId, Long accountId, String commentString) throws ArticleNotFoundException {
        Optional<StudopediaArticle> article = studopediaDatabase.findByIdEquals(articleId);
        Optional<Account> account = accountDatabase.findByIdEquals(accountId);
        if (article.isEmpty() || account.isEmpty()) throw new ArticleNotFoundException();
        Comment comment = new Comment(article.get(), account.get(), commentString);
        commentDatabase.save(comment);
    }

    @Transactional
    public void deleteComment(Long articleId, Long accountId, String commentString) throws ArticleNotFoundException {
        Optional<StudopediaArticle> article = studopediaDatabase.findByIdEquals(articleId);
        if (article.isEmpty()) throw new ArticleNotFoundException();
        Optional<Comment> comment = commentDatabase.findCommentByStudopediaArticleAndAccountIdAndComment(article.get(), accountId, commentString);
        if (comment.isEmpty()) throw new ArticleNotFoundException();
        commentDatabase.delete(comment.get());
    }

    @Transactional
    public void deleteArticle(Long articleId, Long accountId) throws ArticleNotFoundException {
        Optional<StudopediaArticle> article = studopediaDatabase.findByIdEquals(articleId);
        if (article.isEmpty()) throw new ArticleNotFoundException();
        if (!Objects.equals(article.get().getAccountId(), accountId)) throw new ArticleNotFoundException();
        studopediaDatabase.delete(article.get());
    }
}
