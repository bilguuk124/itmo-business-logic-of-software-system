package com.roclh.blps.Exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ArticleNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(ArticleNotFoundExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
    @ExceptionHandler(value = {ArticleNotFoundException.class})
    protected void handleArticleNotFound(Exception e, WebRequest req) {
        log.info("Article was not found!" + e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Illegal argument")
    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected void handleIllegalArgument(Exception e, WebRequest req) {
        log.info("Wrong argument!" + e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not valid")
    @ExceptionHandler(value = {DataValidationException.class})
    protected void handleDataValidation(Exception e, WebRequest req) {
        log.info("Arguments are not valid!" + e.getMessage());
    }
}
