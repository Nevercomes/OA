package com.yunlg.oa.domain.wrapper;

import lombok.Data;

@Data
public class StaffModifyPwd {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
