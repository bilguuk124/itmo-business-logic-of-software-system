package com.roclh.blps.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

    private int upVotes;
    private int downVotes;
    private String content;

    private Long accountId;

    @OneToMany(mappedBy = "article")
    @JsonManagedReference(value = "article_reference")
    private List<Comment> comments;

    public StudopediaArticle(String name, String content, Category category, Long accountId) {
        this.id = accountId;
        this.name = name;
        this.content = content;
        this.category = category;
        this.upVotes = 0;
        this.downVotes = 0;
        this.comments = new ArrayList<>();
    }

    public void addUp(){
        this.upVotes++;
    }

    public void addDown(){
        this.downVotes++;
    }

    public void removeUp(){
        this.upVotes--;
    }

    public void removeDown(){
        this.downVotes--;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
