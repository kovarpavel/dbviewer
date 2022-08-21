package com.pkovar.dbviewer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class JdbcSQLIntegrityConstraintViolationAdvice {

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String SQLIntegrityConstraintViolationHandler (SQLIntegrityConstraintViolationException ex) {
        return ex.getMessage();
    }
}
