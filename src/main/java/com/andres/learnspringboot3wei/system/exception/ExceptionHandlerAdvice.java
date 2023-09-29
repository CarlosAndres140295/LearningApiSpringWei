package com.andres.learnspringboot3wei.system.exception;

import com.andres.learnspringboot3wei.artifact.exception.ArtifactNotFoundException;
import com.andres.learnspringboot3wei.system.Result;
import com.andres.learnspringboot3wei.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArtifactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handlerArtifactNotFoundException(ArtifactNotFoundException ex){
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }
}
