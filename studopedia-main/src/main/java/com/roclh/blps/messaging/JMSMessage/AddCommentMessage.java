package com.roclh.blps.messaging.JMSMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class AddCommentMessage implements Serializable {
    private Long articleId;
    private Long accountId;
    private String comment;

    @Override
    public String toString(){
        return articleId + ";" + accountId + ";" + comment + ";";
    }

}
