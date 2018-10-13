package com.yunlg.oa.exception;

public class WorkServiceException extends ServiceException{
    public WorkServiceException() {

    }

    public WorkServiceException(String message) {
        super(message);
    }

    public WorkServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkServiceException(Throwable cause) {
        super(cause);
    }

    public WorkServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
