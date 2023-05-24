package com.roclh.blps.RequestAndResponse.JMSMessage;

import com.roclh.blps.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

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
