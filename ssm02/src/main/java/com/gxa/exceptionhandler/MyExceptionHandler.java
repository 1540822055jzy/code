package com.gxa.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//@Component
//@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler({Exception.class})
    public String error(){


        return "error";
    }
}
