package com.yunlg.oa.domain.wrapper;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import lombok.Data;

import java.util.List;

@Data
public class BatchRegister {
    List<Staff> staffList;
    List<StaffSignIn> staffSignInList;
}
