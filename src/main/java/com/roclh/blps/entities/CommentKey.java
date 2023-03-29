package com.roclh.blps.entities;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
public class CommentKey implements Serializable {

    Long id;
    StudopediaArticle article;

    Long accountId;

}
