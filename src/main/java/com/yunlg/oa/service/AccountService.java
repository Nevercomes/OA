package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.AdminSignIn;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.domain.wrapper.AdminModifyPwd;
import com.yunlg.oa.domain.wrapper.StaffModifyPwd;
import com.yunlg.oa.exception.AccountServiceException;

import java.util.List;

public interface AccountService {
    Staff staffLogin(String userId, String password) throws AccountServiceException;

    Admin adminLogin(String userId, String password, String numbering) throws AccountServiceException;

    String adminRegister(Admin admin, AdminSignIn adminSignIn) throws AccountServiceException;

    void batchRegister(List<Staff> staffList, List<StaffSignIn> staffSignInList) throws AccountServiceException;

    boolean staffModifyPwd(StaffModifyPwd staffModifyPwd) throws AccountServiceException;

    boolean adminModifyPwd(AdminModifyPwd adminModifyPwd) throws AccountServiceException;

    void forceModifyStaff(String userId) throws AccountServiceException;

    void forceModifyAdmin(String userId) throws AccountServiceException;

    Staff getStaff(String userId) throws AccountServiceException;
}
