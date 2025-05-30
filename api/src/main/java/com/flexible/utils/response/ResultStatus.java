package com.flexible.utils.response;

/**
 * 自定义请求状态码
 *
 * @date 2015/7/15.
 */
public enum ResultStatus {
    //系统相关的状态码
    SUCCESS("20000", "成功"),
    ERROR("0", "系统异常"),


    //日志相关的状态码
    LOG_SAVE_FAILED("2001", "保存CRUD相关日志失败");


    /**
     * 返回码.
     */
    private String code;

    /**
     * 返回结果描述.
     */
    private String message;

    ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

