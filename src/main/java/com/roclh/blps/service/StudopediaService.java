package com.roclh.blps.service;

import com.roclh.blps.Exceptions.ArticleNotFoundException;
import com.roclh.blps.database.StudopediaDatabase;
import com.roclh.blps.entities.StudopediaArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudopediaService {

    private final StudopediaDatabase articleRepository;

    @Autowired
    public StudopediaService(StudopediaDatabase articleRepository){
        this.articleRepository = articleRepository;
    }

    public LinkedList<StudopediaArticle> getArticlesAsList(){
        return (LinkedList<StudopediaArticle>) articleRepository.findAll();
    }

    public List<StudopediaArticle> getArticlesAsPage(int page){
        Pageable pageWithFiveElements = PageRequest.of(page, 5);
        return articleRepository.findByNameContainsIgnoreCase("Hello", pageWithFiveElements);
    }

    public StudopediaArticle getArticleByID(Long id) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = articleRepository.findByIdEquals(id);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        return optional.get();
    }

    public StudopediaArticle getArticleByName(String name) throws ArticleNotFoundException{
        Optional<StudopediaArticle> optional = articleRepository.findByNameEqualsIgnoreCase(name);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        return optional.get();
    }

    public LinkedList<StudopediaArticle> getArticleSuggestionBySubStr(String subString){
        Pageable pageOneWithFiveElements = PageRequest.of(0, 5);
        return (LinkedList<StudopediaArticle>) articleRepository.findByNameContainsIgnoreCase(subString, pageOneWithFiveElements);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    public StudopediaArticle getRandomArticle() throws ArticleNotFoundException {
        long max = getArticleCount();
        long randomID;
        if (max < 0 ) throw new ArticleNotFoundException();
        else if (max == 0) randomID = 0;
        else{
            Random random = new Random();
            randomID = random.nextLong(max);

        }
        return getArticleByID(randomID);
    }




}
