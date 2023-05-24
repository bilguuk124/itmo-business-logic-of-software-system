package com.roclh.commentmodule.Controller;

import com.roclh.commentmodule.Exception.CommentNotFoundException;
import com.roclh.commentmodule.Exception.NotOwnerException;
import com.roclh.commentmodule.Service.CommentService;
import com.roclh.mainmodule.exceptions.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.Arrays;

@Component
@Slf4j
public class ActiveMQController {

    private final CommentService commentService;

    @Autowired
    public ActiveMQController(CommentService commentService) {
        this.commentService = commentService;
    }

    @JmsListener(destination = "addQueue")
    public void addComment(String message) throws NotOwnerException, ArticleNotFoundException, JMSException {
        log.info("Received " + message);
        String[] result = message.split(";");
        log.info("Array: " + Arrays.toString(result));
        Long articleId = Long.parseLong(result[0]);
        Long accountId = Long.parseLong(result[1]);
        String comment = result[2];
        commentService.addComment(articleId, accountId, comment);

    }

    @JmsListener(destination = "deleteQueue")
    public void deleteComment(String  message) throws NotOwnerException, ArticleNotFoundException, CommentNotFoundException, JMSException {
        log.info("Received " + message);
        String[] result = message.split(";");
        log.info("Array: " + Arrays.toString(result));
        Long articleId = Long.parseLong(result[0]);
        Long accountId = Long.parseLong(result[1]);
        Long commentId = Long.parseLong(result[2]);
        commentService.deleteComment(articleId, accountId, commentId);
    }
}
