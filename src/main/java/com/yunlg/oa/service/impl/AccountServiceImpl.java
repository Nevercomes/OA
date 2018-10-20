package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.domain.wrapper.AdminModifyPwd;
import com.yunlg.oa.domain.wrapper.StaffModifyPwd;
import com.yunlg.oa.exception.AccountServiceException;
import com.yunlg.oa.exception.ExceptionMessage;
import com.yunlg.oa.persistence.AdminDAO;
import com.yunlg.oa.persistence.AdminSignInDAO;
import com.yunlg.oa.persistence.StaffDAO;
import com.yunlg.oa.persistence.StaffSignInDAO;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.utils.HashSalt;
import com.yunlg.oa.utils.NumberingRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;

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
            StaffSignIn staffSignIn = staffSignInDAO.getStaffSignIn(userId);
            if (staffSignIn == null)
                throw new AccountServiceException(ExceptionMessage.NOACCOUNT);
            Staff staff = new Staff();
            if (HashSalt.verify(staffSignIn.getPassword(), password, staffSignIn.getSalt()))
                staff = staffDAO.getStaff(userId);
            if (staff == null)
                throw new AccountServiceException(ExceptionMessage.FALSEPASSWORD);
            return staff;
        } catch (PersistenceException pe) {
            throw new AccountServiceException(pe);
        }
    }

//    @Override
//    public Admin adminLogin(String userId, String password, String numbering) throws AccountServiceException {
//        try {
//            AdminSignIn adminSignIn = adminSignInDAO.getAdminSignIn(userId, numbering);
//            if (adminSignIn == null)
//                throw new AccountServiceException(ExceptionMessage.NOACCOUNT);
//            Admin admin = new Admin();
//            if (HashSalt.verify(adminSignIn.getPassword(), password, adminSignIn.getSalt()))
//                admin = adminDAO.getAdmin(userId);
//            if (admin == null)
//                throw new AccountServiceException(ExceptionMessage.FALSEPASSWORD);
//            return admin;
//        } catch (PersistenceException pe) {
//            throw new AccountServiceException(pe);
//        }
//    }

//    @Override
//    public String adminRegister(Admin admin, AdminSignIn adminSignIn) throws AccountServiceException {
//        try {
//            String numbering = NumberingRandom.getAdminNumbering();
//            AdminSignIn adminSignIn1 = HashSalt.addSalt(adminSignIn);
//            adminSignIn1.setNumbering(numbering);
//            adminDAO.saveAdmin(admin, adminSignIn1);
//            return numbering;
//        } catch (PersistenceException pe) {
//            throw new AccountServiceException(ExceptionMessage.ALREADYHAVEACCOUNT, pe);
//        }
//    }

    @Override
    public void batchRegister(List<Staff> staffList, List<StaffSignIn> staffSignInList) throws AccountServiceException {
        try {
            List<StaffSignIn> staffSignInList1 = HashSalt.addSalt(staffSignInList);
            staffDAO.batchSaveStaff(staffList, staffSignInList1);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(ExceptionMessage.ALREADYHAVEACCOUNT, pe);
        }
    }

    @Override
    public boolean staffModifyPwd(StaffModifyPwd staffModifyPwd) throws AccountServiceException {
        try {
            StaffSignIn staffSignIn = staffSignInDAO.getStaffSignIn(staffModifyPwd.getUserId());
            if (staffSignIn == null)
                throw new AccountServiceException(ExceptionMessage.NOACCOUNT);
            if (HashSalt.verify(staffSignIn.getPassword(), staffModifyPwd.getOldPassword(), staffSignIn.getSalt())) {
                staffSignIn.setPassword(staffModifyPwd.getNewPassword());
                staffSignInDAO.updateStaffSign(HashSalt.addSalt(staffSignIn));
                return true;
            }
            return false;
        } catch (PersistenceException pe) {
            throw new AccountServiceException(pe);
        }
    }

//    @Override
//    public boolean adminModifyPwd(AdminModifyPwd adminModifyPwd) throws AccountServiceException {
//        try {
//            AdminSignIn adminSignIn = adminSignInDAO.getAdminSignIn(adminModifyPwd.getAdminId(), adminModifyPwd.getNumbering());
//            if (adminSignIn == null)
//                throw new AccountServiceException(ExceptionMessage.NOACCOUNT);
//            if (HashSalt.verify(adminSignIn.getPassword(), adminModifyPwd.getOldPassword(), adminSignIn.getSalt())) {
//                adminSignIn.setPassword(adminModifyPwd.getNewPassword());
//                adminSignInDAO.updateAdminSignIn(HashSalt.addSalt(adminSignIn));
//                return true;
//            }
//            return false;
//        } catch (PersistenceException pe) {
//            throw new AccountServiceException(pe);
//        }
//    }

    @Override
    public void forceModifyStaff(String userId) throws AccountServiceException {
        try {
            StaffSignIn staffSignIn = new StaffSignIn();
            staffSignIn.setUserId(userId);
            staffSignIn.setPassword("yunlugu000");
            staffSignInDAO.updateStaffSign(HashSalt.addSalt(staffSignIn));
        } catch (PersistenceException pe) {
            throw new AccountServiceException(ExceptionMessage.NOACCOUNT, pe);
        }
    }

//    @Override
//    public void forceModifyAdmin(String userId) throws AccountServiceException {
//        try {
//            AdminSignIn adminSignIn = new AdminSignIn();
//            adminSignIn.setAdminId(userId);
//            adminSignIn.setPassword("yunlugu000");
//            adminSignInDAO.forceUpdateAdminSignIn(HashSalt.addSalt(adminSignIn));
//        } catch (PersistenceException pe) {
//            throw new AccountServiceException(ExceptionMessage.NOACCOUNT, pe);
//        }
//    }

    @Override
    public Staff getStaff(String userId) throws AccountServiceException {
        try {
            return staffDAO.getStaff(userId);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(ExceptionMessage.NOACCOUNT, pe);
        }
    }
}
