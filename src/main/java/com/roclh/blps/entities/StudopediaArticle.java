package com.roclh.blps.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "article")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudopediaArticle implements Serializable {
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "articleId", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ElementCollection
    private Set<Account> uppedUsers;
    @ElementCollection
    private Set<Account> downedUsers;

    private Boolean approved;

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
        uppedUsers = new HashSet<>();
        downedUsers = new HashSet<>();
        this.comments = new ArrayList<>();
        approved = false;
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

    public void approve() {
        this.approved = true;
    }

}
