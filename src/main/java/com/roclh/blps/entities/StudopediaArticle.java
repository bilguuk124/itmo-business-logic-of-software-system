package com.roclh.blps.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Table(name = "article")
@Entity
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

//    @OneToMany(mappedBy = "article")
//    @JsonManagedReference(value = "article_reference")
//    private List<Comment> comments;

    public StudopediaArticle(String name, String content, Category category, Long accountId) {
        this.id = accountId;
        this.name = name;
        this.content = content;
        this.category = category;
        this.upVotes = 0;
        this.downVotes = 0;
        //this.comments = new ArrayList<>();
    }

    public void addUp() {
        this.upVotes++;
    }

    public void addDown() {
        this.downVotes++;
    }

    public void removeUp() {
        this.upVotes--;
    }

    public void removeDown() {
        this.downVotes--;
    }

//    public void addComment(Comment comment) {
//        comments.add(comment);
//    }
}
