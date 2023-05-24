package com.roclh.blps.RequestAndResponse.JMSMessage;

import com.roclh.blps.entities.StudopediaArticle;
import com.roclh.blps.entities.Account;
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
