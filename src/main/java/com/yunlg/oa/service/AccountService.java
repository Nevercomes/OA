package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.exception.AccountServiceException;

public interface AccountService {
    Staff staffLogin(String userId, String password) throws AccountServiceException;

    Admin adminLogin(String userId, String password, String numbering) throws AccountServiceException;
}
