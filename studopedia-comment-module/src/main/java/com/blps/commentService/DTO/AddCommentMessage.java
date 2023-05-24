package com.blps.commentService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class AddCommentMessage implements Serializable {
    private Long articleId;
    private Long accountId;
    private String comment;
}
