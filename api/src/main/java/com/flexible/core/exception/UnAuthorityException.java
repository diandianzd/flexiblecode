package com.flexible.core.exception;

public class UnAuthorityException extends RuntimeException {
    public UnAuthorityException(String message) {
        super(message);
        System.out.println("登录失效：" + message);
    }
}