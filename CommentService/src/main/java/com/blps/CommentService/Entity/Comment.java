package com.blps.CommentService.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@IdClass(CommentKey.class)
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_gen")
    @SequenceGenerator(name = "comment_id_gen", sequenceName = "comment_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @Column(name = "article_id", nullable = false, insertable = false, updatable = false)
    private Long articleId;

    @Id
    private Long accountId;

    @Transient
    @JsonBackReference(value = "article_reference")
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="article_id", nullable = false, insertable = false, updatable = false)
    private StudopediaArticle article;

    String comment;

    public Comment(StudopediaArticle article, Long accountId, String comment) {
        this.articleId = article.getId();
        this.article = article;
        this.accountId = accountId;
        this.comment = comment;
    }
}

