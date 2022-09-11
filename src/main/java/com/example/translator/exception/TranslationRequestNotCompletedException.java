package com.example.translator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TranslationRequestNotCompletedException extends Exception {

    public TranslationRequestNotCompletedException(String message) {
        super(message);
    }
}
