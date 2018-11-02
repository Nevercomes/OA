package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.wrapper.BatchRegister;
import com.yunlg.oa.domain.wrapper.UserModifyPwd;
import com.yunlg.oa.domain.wrapper.UserRegister;
import com.yunlg.oa.exception.AccountServiceException;

import java.util.List;

public interface AccountService {
    /**
     * Exception:
     *  1. no account
     *      userId := RESULT_NO_ACCOUNT
     *  2. wrong password
     *      userId := RESULT_WRONG_PASSWORD
     * @param userId
     * @param password
     * @return User
     * @throws AccountServiceException
     */
    User userLogin(String userId, String password) throws AccountServiceException;

    /**
     * admin register
     * @param userRegister
     * @throws AccountServiceException
     */
    void adminRegister(UserRegister userRegister) throws AccountServiceException;

    /**
     * batch register
     * @param batchRegisterList
     * @throws AccountServiceException
     */
    void batchRegister(List<BatchRegister> batchRegisterList) throws AccountServiceException;

    /**
     * modify password
     * @param userId
     * @param userModifyPwd
     * @return
     * @throws AccountServiceException
     */
    boolean modifyPwd(String userId, UserModifyPwd userModifyPwd) throws AccountServiceException;

    /**
     * reset staff password for admin
     * @param userId
     * @throws AccountServiceException
     */
    void resetStaffPwd(String userId) throws AccountServiceException;

    /**
     * get obj:User
     * @param userId
     * @return
     * @throws AccountServiceException
     */
    User getUser(String userId) throws AccountServiceException;

    /**
     * get List of obj:User
     * @param department
     * @return
     * @throws AccountServiceException
     */
    List<User> getUserList(int department) throws AccountServiceException;

    /**
     * single register for batch register
     * @param batchRegister
     * @throws AccountServiceException
     */
    void singleRegister(BatchRegister batchRegister) throws AccountServiceException;
}

//    Admin adminLogin(String userId, String password, String numbering) throws AccountServiceException;
//    boolean adminModifyPwd(AdminModifyPwd adminModifyPwd) throws AccountServiceException;
//    void forceModifyAdmin(String userId) throws AccountServiceException;


