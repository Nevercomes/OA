package com.yunlg.oa.exception;

public class AssessServiceException extends ServiceException{
    public AssessServiceException() {

    }

    public AssessServiceException(String message) {
        super(message);
    }

    public AssessServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssessServiceException(Throwable cause) {
        super(cause);
    }

    public AssessServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
