package com.roclh.blps.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private int up;
    private int down;
    private String content;

    private Long accountId;

    @OneToMany(mappedBy = "article")
    @JsonManagedReference(value = "article_reference")
    private List<Comment> comments;

    public StudopediaArticle(String name, String content, Category category, Long accountId) {
        this.name = name;
        this.content = content;
        this.category = category;
        this.up = 0;
        this.down = 0;
        this.comments = new ArrayList<>();
    }

    public void addUp(){
        this.up++;
    }

    public void addDown(){
        this.down++;
    }

    public void removeUp(){
        this.up--;
    }

    public void removeDown(){
        this.down--;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
