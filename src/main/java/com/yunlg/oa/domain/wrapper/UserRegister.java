package com.yunlg.oa.domain.wrapper;

import lombok.Data;

@Data
public class UserRegister {
    private String userId;
    private String name;
    private String password;
    private int department;
    private int position;
}
