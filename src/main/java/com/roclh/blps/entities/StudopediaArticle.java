package com.roclh.blps.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class StudopediaArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studopedia_article_gen")
    @SequenceGenerator(name = "studopedia_article_gen", sequenceName = "studopedia_article_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    private String content;


    public StudopediaArticle(String name, String content, Category category) {
        this.name = name;
        this.content = content;
        this.category = category;
    }

    public StudopediaArticle() {

    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent(){
        return content;
    }

    public void setContent(){
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
