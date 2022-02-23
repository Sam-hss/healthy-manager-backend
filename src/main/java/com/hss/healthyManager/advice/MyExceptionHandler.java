package com.hss.healthyManager.advice;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ExceptionResult> handleException(MyException e) {
        return ResponseEntity.status(e.getExceptionEnums().getCode())
                .body(new ExceptionResult(e.getExceptionEnums()));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ExceptionResult> handleException(AuthorizationException e) {
        return ResponseEntity.status(403)
                .body(new ExceptionResult(ExceptionEnums.NOT_AUTHORIZED));
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ResponseEntity<ExceptionResult> handleException(UnknownAccountException e) {
        return ResponseEntity.status(403)
                .body(new ExceptionResult(ExceptionEnums.NOT_AUTHORIZED));
    }


    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ExceptionResult> handleException(IncorrectCredentialsException e) {
        return ResponseEntity.status(400)
                .body(new ExceptionResult(ExceptionEnums.PASSWORD_WRONG));
    }


}
