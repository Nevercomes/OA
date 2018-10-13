package com.yunlg.oa.exception;

public class CatchServiceException extends RuntimeException {
    private ServiceException serviceException;

    public CatchServiceException(ServiceException serviceException) {
        this.serviceException = serviceException;
    }

    public ServiceException getServiceException() {
        return serviceException;
    }

    public CatchServiceException() {

    }

    public CatchServiceException(String message) {
        super(message);
    }

    public CatchServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatchServiceException(Throwable cause) {
        super(cause);
    }

    public CatchServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
