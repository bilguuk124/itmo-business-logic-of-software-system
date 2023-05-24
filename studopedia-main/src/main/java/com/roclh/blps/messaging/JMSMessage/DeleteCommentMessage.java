package com.roclh.blps.messaging.JMSMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class DeleteCommentMessage implements Serializable {
    private Long articleId;
    private Long accountId;
    private Long commentId;

}
