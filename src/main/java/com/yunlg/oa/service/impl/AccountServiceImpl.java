package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.exception.AccountServiceException;
import com.yunlg.oa.persistence.AdminDAO;
import com.yunlg.oa.persistence.AdminSignInDAO;
import com.yunlg.oa.persistence.StaffDAO;
import com.yunlg.oa.persistence.StaffSignInDAO;
import com.yunlg.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class AccountServiceImpl implements AccountService {

    private StaffDAO staffDAO;
    private StaffSignInDAO staffSignInDAO;
    private AdminDAO adminDAO;
    private AdminSignInDAO adminSignInDAO;

    @Autowired
    public AccountServiceImpl(StaffDAO staffDAO, StaffSignInDAO staffSignInDAO, AdminDAO adminDAO, AdminSignInDAO adminSignInDAO) {
        this.staffDAO = staffDAO;
        this.staffSignInDAO = staffSignInDAO;
        this.adminDAO = adminDAO;
        this.adminSignInDAO = adminSignInDAO;
    }

    @Override
    public Staff staffLogin(String userId, String password) throws AccountServiceException {
        try {
            Staff staff = staffSignInDAO.staffSignIn(userId, password);
            System.out.println(staff.getName());
            return staff;
        } catch (PersistenceException pe) {
            throw new AccountServiceException(pe);
        }
    }

    @Override
    public Admin adminLogin(String userId, String password, String numbering) throws AccountServiceException {
        try {
            return adminSignInDAO.adminSignIn(userId, password, numbering);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(pe);
        }
    }
}
