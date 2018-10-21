package com.yunlg.oa.global;

public enum Department {
    ALL(0, "中心"),
    RD(1, "研发"),
    NETWORKS(2, "网络"),
    ART(3, "美工");

    private int code;
    private String name;

    Department() {

    }

    Department(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
