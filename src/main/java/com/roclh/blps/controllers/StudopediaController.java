package com.roclh.blps.controllers;

import com.roclh.blps.Exceptions.ArticleNotFoundException;
import com.roclh.blps.entities.StudopediaArticle;
import com.roclh.blps.service.StudopediaService;
import com.roclh.blps.utils.HttpResponseErrorMessages;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudopediaController {

    private static final Logger log = LoggerFactory.getLogger(StudopediaController.class);
    private final StudopediaService service;

    @Autowired
    public StudopediaController(StudopediaService service){
        log.info("Initializing Studopedia Controller");
        this.service = service;
    }

    @PostMapping("/article")
    @ApiOperation(value = "Get article by name", notes = "Throws exception if Article doesn't exist")
    public StudopediaArticle getArticle(@RequestParam(name="name") String articleName) throws ArticleNotFoundException {
        log.info("Received request to get article with name {}", articleName);
            return service.getArticleByName(articleName);
    }

    @PostMapping("/article/search")
    public List<StudopediaArticle> searchArticle(@RequestParam(name="search") String search, @RequestParam(name="page") int page) {
        log.info("Received a search for " + search + " article request");
        return service.getArticlesAsPage(page);
    }

    @PostMapping("/article/random")
    public StudopediaArticle randomArticle() throws ArticleNotFoundException {
        log.info("Received a request for random article");
        return service.getRandomArticle();
    }

    @GetMapping("/article")
    public String test(){
        return "Hello, World!";
    }
}




