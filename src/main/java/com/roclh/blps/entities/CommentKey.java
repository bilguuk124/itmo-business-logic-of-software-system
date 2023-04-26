package com.roclh.blps.entities;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
public class CommentKey implements Serializable {

    Long id;
    StudopediaArticle studopediaArticle;

    Long accountId;

}
