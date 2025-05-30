package com.flexible.core.exception;

public class FlexErrorException extends RuntimeException {
    public FlexErrorException(String message) {
        super(message);
        System.out.println("操作错误：" + message);
    }
}