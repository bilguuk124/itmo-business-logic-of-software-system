package com.roclh.mainmodule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such article!")
public class ArticleNotFoundException extends Exception{
}
