package com.yunlg.oa.domain;

public class Result {
    private String result;
    private String message;
    private Object object;
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";

    public Result(String result) {
        this.result = result;
        String message = result.equals(RESULT_SUCCESS) ? "操作成功" : "";
        message = result.equals(RESULT_ERROR) ? "操作失败" : message;
        this.setMessage(message);
    }

    public Result(String result, Object object) {
        this.result = result;
        this.object = object;
        String message = result.equals(RESULT_SUCCESS) ? "操作成功" : "";
        message = result.equals(RESULT_ERROR) ? "操作失败" : message;
        this.setMessage(message);
    }

    public String getResult() {
        return result;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
