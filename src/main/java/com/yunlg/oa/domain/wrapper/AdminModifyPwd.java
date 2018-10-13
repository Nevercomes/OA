package com.yunlg.oa.domain.wrapper;

import lombok.Data;

@Data
public class AdminModifyPwd {
    private String adminId;
    private String numbering;
    private String oldPassword;
    private String newPassword;
}
