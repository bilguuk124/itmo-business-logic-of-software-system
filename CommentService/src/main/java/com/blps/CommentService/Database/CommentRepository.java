package com.blps.CommentService.Database;


import com.blps.CommentService.Entity.Comment;
import com.blps.CommentService.Entity.CommentKey;
import com.blps.CommentService.Entity.StudopediaArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    long deleteByIdAndArticleIdAndAccountId(Long id, Long articleId, Long accountId);

}
