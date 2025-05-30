package com.flexible.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexible.core.exception.FlexErrorException;
import com.flexible.core.exception.UnAuthorityException;
import com.flexible.utils.response.ResultModel;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;


@ControllerAdvice
public class MyExceptionHandler {

    Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = UnAuthorityException.class)
    @ResponseBody
    public ResultModel handleUnAuthorityException(UnAuthorityException e) {
        return ResultModel.error("401", "登录失效");
    }

    @ExceptionHandler(value = FlexErrorException.class)
    @ResponseBody
    public ResultModel handleStoreErrorException(FlexErrorException e) {
        logger.warn("handleStoreErrorException: " + e.getMessage());
        return ResultModel.error("0", e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResultModel validExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        System.out.println("REQUEST PARAMS ERROR: " + fieldError.getField() + "" + fieldError.getDefaultMessage());
        return ResultModel.error(fieldError.getField() + ' ' + fieldError.getDefaultMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResultModel handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder msgStr = new StringBuilder();
        int i = 0;
        for (ConstraintViolation<?> model : e.getConstraintViolations()) {
            i++;
            msgStr.append("第" + i + "行--").append(model.getMessage()).append(" ");
        }
        return ResultModel.error("0", msgStr.toString());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel exceptionHandler(Exception e) {
        System.out.println("SYSTEM ERROR: " + e + Arrays.toString(e.getStackTrace()));
        logger.error("SYSTEM ERROR: " + e.toString());
        logger.error("SYSTEM ERROR TRACE: \n" + Arrays.toString(e.getStackTrace()).replaceAll(",",",\n"));
        return ResultModel.error(e.toString());
    }
}