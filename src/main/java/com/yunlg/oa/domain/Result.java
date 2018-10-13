package com.yunlg.oa.domain;

import lombok.Data;

@Data
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
}
