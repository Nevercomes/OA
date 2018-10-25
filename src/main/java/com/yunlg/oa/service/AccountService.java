package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.wrapper.BatchRegister;
import com.yunlg.oa.domain.wrapper.UserModifyPwd;
import com.yunlg.oa.domain.wrapper.UserRegister;
import com.yunlg.oa.exception.AccountServiceException;

import java.util.List;

public interface AccountService {
    User userLogin(String userId, String password) throws AccountServiceException;

//    Admin adminLogin(String userId, String password, String numbering) throws AccountServiceException;
//
    void adminRegister(UserRegister userRegister) throws AccountServiceException;

    void batchRegister(List<BatchRegister> batchRegisterList) throws AccountServiceException;

    boolean modifyPwd(String userId, UserModifyPwd userModifyPwd) throws AccountServiceException;

//    boolean adminModifyPwd(AdminModifyPwd adminModifyPwd) throws AccountServiceException;

    void resetStaffPwd(String userId) throws AccountServiceException;

//    void forceModifyAdmin(String userId) throws AccountServiceException;

    User getUser(String userId) throws AccountServiceException;

    List<User> getUserList(int department) throws AccountServiceException;

    void singleRegister(BatchRegister batchRegister) throws AccountServiceException;
}
