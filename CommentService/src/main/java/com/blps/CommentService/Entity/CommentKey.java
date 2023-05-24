package com.blps.CommentService.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
public class CommentKey implements Serializable {

    Long id;

    Long articleId;

    Long accountId;

}
