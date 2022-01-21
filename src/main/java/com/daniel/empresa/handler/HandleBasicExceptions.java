package com.daniel.empresa.handler;

import com.daniel.empresa.dto.BasicErroRespose;
import com.daniel.empresa.exception.BasicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleBasicExceptions {

    @ExceptionHandler(BasicException.class)
    protected ResponseEntity basicExveption(BasicException basic){

        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new BasicErroRespose(basic.getMotivo()));
    }
}
