package com.mediLinkAI.utility;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mediLinkAI.exception.MediLinkAI;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalException(Exception exception) {
        ErrorInfo error = new ErrorInfo(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MediLinkAI.class)
    public ResponseEntity<ErrorInfo> generalException(MediLinkAI exception) {
        String msg = environment.getProperty(exception.getMessage());
        if (msg == null) {
            msg = exception.getMessage();
        }
        ErrorInfo error = new ErrorInfo(msg, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
        String msg = "";
        if (exception instanceof MethodArgumentNotValidException manvException) {
            msg = manvException.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
        } else {
            ConstraintViolationException cvException = (ConstraintViolationException) exception;
            msg = cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
        }
        ErrorInfo error = new ErrorInfo(msg, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
