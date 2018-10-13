package com.yunlg.oa.controller;

import com.yunlg.oa.domain.Error;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AbstractController {
    @ExceptionHandler(CatchServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Error serviceError(CatchServiceException e) {
        e.printStackTrace();
        Error error = new Error(e.getServiceException().getErrorCode(), e.getMessage());
        return error;
    }
}
