package com.roclh.blps.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
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


}
