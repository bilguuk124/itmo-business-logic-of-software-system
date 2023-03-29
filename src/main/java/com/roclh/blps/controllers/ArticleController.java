package com.roclh.blps.controllers;

import com.roclh.blps.Exceptions.ArticleNotFoundException;
import com.roclh.blps.entities.Account;
import com.roclh.blps.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/article/{article_id}")
@RequiredArgsConstructor
public class ArticleController {

    private final Logger log = LogManager.getLogger(ArticleController.class);
    private final ArticleService service;
    private final Principal principal;

    @PutMapping("/up")
    public void upAnArticle(@PathVariable(name = "article_id") Long articleId) throws ArticleNotFoundException {
        service.upAnArticle(articleId);
    }

    @PutMapping("/down")
    public void downAnArticle(@PathVariable(name = "article_id") Long articleId) throws ArticleNotFoundException{
        service.downAnArticle(articleId);
    }

    @DeleteMapping("/up")
    public void cancelUp(@PathVariable(name = "article_id") Long articleId) throws ArticleNotFoundException{
        service.cancelUpAnArticle(articleId);
    }

    @DeleteMapping("/down")
    public void cancelDown(@PathVariable(name = "article_id") Long articleId) throws ArticleNotFoundException{
        service.cancelDownAnArticle(articleId);
    }

    @PutMapping("/comment")
    public void commentArticle(@PathVariable(name = "article_id") Long articleId, @RequestParam(name = "comment") String commentString) throws ArticleNotFoundException {
        Long accountId = ((Account) principal).getId();
        service.commentArticle(articleId, accountId, commentString);
    }

    @DeleteMapping("/comment")
    public void deleteComment(@PathVariable(name = "article_id") Long articleId, @RequestParam(name = "comment") String commentString) throws ArticleNotFoundException{
        Long accountId = ((Account) principal).getId();
        service.deleteComment(articleId, accountId, commentString);
    }

}
