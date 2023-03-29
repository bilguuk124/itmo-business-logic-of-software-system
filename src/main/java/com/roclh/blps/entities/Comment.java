package com.roclh.blps.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@IdClass(CommentKey.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_gen")
    @SequenceGenerator(name = "comment_id_gen", sequenceName = "comment_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonBackReference(value = "article_reference")
    @Id
    @ManyToOne(cascade = CascadeType.DETACH)
    StudopediaArticle article;

    @Id
    Long accountId;

    String comment;

    public Comment(StudopediaArticle article, Account account, String comment){
        this.article = article;
        this.accountId = account.getId();
        this.comment = comment;
    }
}
