package com.roclh.commentmodule.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AddCommentMessage implements Serializable {
    private Long articleId;
    private Long accountId;
    private String comment;
}
