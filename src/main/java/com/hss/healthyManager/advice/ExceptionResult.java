package com.hss.healthyManager.advice;

import lombok.Data;


@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums enums) {
        this.status = enums.getCode();
        this.message = enums.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
