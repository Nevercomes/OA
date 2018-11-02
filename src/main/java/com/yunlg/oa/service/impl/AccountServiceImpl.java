package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.model.SignIn;
import com.yunlg.oa.domain.wrapper.BatchRegister;
import com.yunlg.oa.domain.wrapper.UserModifyPwd;
import com.yunlg.oa.domain.wrapper.UserRegister;
import com.yunlg.oa.exception.AccountServiceException;
import com.yunlg.oa.exception.ExceptionMessage;
import com.yunlg.oa.persistence.UserDAO;
import com.yunlg.oa.persistence.SignInDAO;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.utils.HashSalt;
import com.yunlg.oa.utils.PositionAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private UserDAO userDAO;
    private SignInDAO signInDAO;

    @Autowired
    public AccountServiceImpl(UserDAO userDAO, SignInDAO signInDAO) {
        this.userDAO = userDAO;
        this.signInDAO = signInDAO;
    }

    @Override
    public User userLogin(String userId, String password) throws AccountServiceException {
        try {
            SignIn signIn = signInDAO.getSignIn(userId);
            User user = new User();
            if (signIn == null) {
//                throw new AccountServiceException(ExceptionMessage.NOACCOUNT);
                user.setUserId(Result.RESULT_NO_ACCOUNT);
                return user;
            }
//            password = Base64.decode(password);
            if (HashSalt.verify(signIn.getPassword(), password, signIn.getSalt())) {
                user = userDAO.getStaff(userId);
            } else {
//                throw new AccountServiceException(ExceptionMessage.FALSEPASSWORD);
                user.setUserId(Result.RESULT_WRONG_PASSWORD);
            }
            return user;
        } catch (PersistenceException e) {
            throw new AccountServiceException(e);
        }
    }

    @Override
    public void adminRegister(UserRegister userRegister) throws AccountServiceException {
        try {
            SignIn signIn = new SignIn();
            User user = new User();
            String password = userRegister.getPassword();
//            password = Base64.decode(password);
            signIn.setUserId(userRegister.getUserId());
            signIn.setPassword(password);
            signIn = HashSalt.addSalt(signIn);
            user.setUserId(userRegister.getUserId());
            user.setName(userRegister.getName());
            user.setDepartment(userRegister.getDepartment());
            user.setPosition(userRegister.getPosition());
            user.setAdmin(PositionAdmin.getAdminInPosition(userRegister.getPosition()));
            userDAO.saveAdmin(user, signIn);
        } catch (PersistenceException e) {
            throw new AccountServiceException(e);
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
    public void batchRegister(List<BatchRegister> batchRegisterList) throws AccountServiceException {
        try {
            List<User> userList = new ArrayList<>();
            List<SignIn> signInList = new ArrayList<>();
            for (BatchRegister batchRegister : batchRegisterList) {
                User user = new User();
                SignIn signIn = new SignIn();
                user.setUserId(batchRegister.getUserId());
                user.setName(batchRegister.getName());
                user.setDepartment(batchRegister.getDepartment());
                user.setPosition(batchRegister.getPosition());
                signIn.setUserId(batchRegister.getUserId());
                signIn.setPassword("yunlugu");
                userList.add(user);
                signInList.add(signIn);
            }
            List<SignIn> signInList1 = HashSalt.addSalt(signInList);
            userDAO.batchSaveStaff(userList, signInList1);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(ExceptionMessage.ALREADYHAVEACCOUNT, pe);
        }
    }

    @Override
    public boolean modifyPwd(String userId, UserModifyPwd userModifyPwd) throws AccountServiceException {
        try {
            SignIn signIn = signInDAO.getSignIn(userId);
            if (signIn == null)
                throw new AccountServiceException(ExceptionMessage.NOACCOUNT);
            if (HashSalt.verify(signIn.getPassword(), userModifyPwd.getOldPassword(), signIn.getSalt())) {
                signIn.setPassword(userModifyPwd.getNewPassword());
                signInDAO.updateSignIn(HashSalt.addSalt(signIn));
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
    public void resetStaffPwd(String userId) throws AccountServiceException {
        try {
            SignIn signIn = new SignIn();
            signIn.setUserId(userId);
            signIn.setPassword("yunlugu");
            signInDAO.updateSignIn(HashSalt.addSalt(signIn));
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
    public User getUser(String userId) throws AccountServiceException {
        try {
            return userDAO.getStaff(userId);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(ExceptionMessage.NOACCOUNT, pe);
        }
    }

    @Override
    public List<User> getUserList(int department) throws AccountServiceException {
        try {
            return userDAO.getStaffList(department);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(pe);
        }
    }

    @Override
    public void singleRegister(BatchRegister batchRegister) throws AccountServiceException {
        try {
            User user = new User();
            SignIn signIn = new SignIn();
            user.setUserId(batchRegister.getUserId());
            user.setName(batchRegister.getName());
            user.setDepartment(batchRegister.getDepartment());
            user.setPosition(batchRegister.getPosition());
            user.setAdmin(0);
            signIn.setUserId(batchRegister.getUserId());
            signIn.setPassword("yunlugu");
            SignIn signIn1 = HashSalt.addSalt(signIn);
            userDAO.singleSaveStaff(user, signIn);
        } catch (PersistenceException pe) {
            throw new AccountServiceException(ExceptionMessage.ALREADYHAVEACCOUNT, pe);
        }
    }
}
