package com.gmail.hvorostenko.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpRetryException;

@ControllerAdvice(basePackages = "com.gmail.hvorostenko.web.controller")
public class AppExceptionHandling {

    @ExceptionHandler(Exception.class)
    public String handle500Exception(HttpServletRequest req, Exception e){
        req.setAttribute("exception", e);
        return "customer_error";
    }
}
