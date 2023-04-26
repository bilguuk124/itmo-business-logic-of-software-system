package com.roclh.blps.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="article_id", nullable = false)
    @Id
    private StudopediaArticle studopediaArticle;

    @Id
    private Long accountId;

    String comment;

    public Comment(StudopediaArticle article, Account account, String comment) {
        this.studopediaArticle = article;
        this.accountId = account.getId();
        this.comment = comment;
    }
}
