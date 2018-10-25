package com.yunlg.oa.domain.wrapper;

import lombok.Data;

@Data
public class UserModifyPwd {
    private String oldPassword;
    private String newPassword;
}
