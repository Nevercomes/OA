package com.yunlg.oa.domain;

import lombok.Data;

@Data
public class Result {

    private String result;
    private String message;
    private Object obj;
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";
    public static final String RESULT_NO_AUTHORITY = "no authority";
    public static final String RESULT_WRONG_PASSWORD = "wrong password";

    public Result(String result) {
        this.result = result;
        String message = result.equals(RESULT_SUCCESS) ? "success" : "";
        message = result.equals(RESULT_ERROR) ? "error" : message;
        this.setMessage(message);
    }

    public Result(String result, Object object) {
        this.result = result;
        this.obj = object;
        String message = result.equals(RESULT_SUCCESS) ? "success" : "";
        message = result.equals(RESULT_ERROR) ? "error" : message;
        this.setMessage(message);
    }
}
