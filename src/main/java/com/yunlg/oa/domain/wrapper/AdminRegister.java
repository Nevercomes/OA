package com.yunlg.oa.domain.wrapper;

import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.AdminSignIn;
import lombok.Data;

@Data
public class AdminRegister {
    private Admin admin;
    private AdminSignIn adminSignIn;
}
