package com.yunlg.oa.controller;

import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.AdminSignIn;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.domain.wrapper.AdminModifyPwd;
import com.yunlg.oa.domain.wrapper.AdminRegister;
import com.yunlg.oa.domain.wrapper.BatchRegister;
import com.yunlg.oa.domain.wrapper.StaffModifyPwd;
import com.yunlg.oa.exception.AccountServiceException;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {""})
public class AccountController{

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login() {
        return "login.jsp";
    }

//    @RequestMapping(value = "/staff", method = RequestMethod.POST)
//    public ResponseEntity<Staff> staffLogin(
//            @RequestParam(value = "userId") String userId,
//            @RequestParam(value = "password") String password,
//            ModelMap modelMap) {
//        try {
//            Staff staff = accountService.staffLogin(userId, password);
//            modelMap.addAttribute("userId", staff.getUserId());
//            modelMap.addAttribute("name", staff.getName());
//            modelMap.addAttribute("department", staff.getDepartment());
//            modelMap.addAttribute("position", staff.getPosition());
//            return new ResponseEntity<>(staff, HttpStatus.OK);
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

//    @RequestMapping(value = "/staff", method = RequestMethod.POST)
//    public ModelAndView staffLogin(
//            @RequestParam(value = "userId") String userId,
//            @RequestParam(value = "password") String password,
//            ModelMap modelMap) {
//        try {
//            Staff staff = accountService.staffLogin(userId, password);
//            modelMap.addAttribute("userId", staff.getUserId());
//            modelMap.addAttribute("name", staff.getName());
//            modelMap.addAttribute("department", staff.getDepartment());
//            modelMap.addAttribute("position", staff.getPosition());
//            return new ModelAndView("staff.jsp");
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public ModelAndView staffLogin(ModelMap modelMap) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = userDetails.getUsername();
            String password = userDetails.getPassword();
            System.out.println(userId);
            Staff staff = accountService.staffLogin(userId, password);
            modelMap.addAttribute("userId", staff.getUserId());
            modelMap.addAttribute("name", staff.getName());
            modelMap.addAttribute("department", staff.getDepartment());
            modelMap.addAttribute("position", staff.getPosition());
            return new ModelAndView("staff.jsp");
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "/login/admin", method = RequestMethod.POST)
    public ResponseEntity<Admin> adminLogin(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "numbering") String numbering) {
        try {
            Admin admin = accountService.adminLogin(userId, password, numbering);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "/register/admin", method = RequestMethod.POST)
    public ResponseEntity<String> adminRegister(@RequestBody AdminRegister adminRegister) {
        try {
            Admin admin = adminRegister.getAdmin();
            AdminSignIn adminSignIn = adminRegister.getAdminSignIn();
            String numbering = accountService.adminRegister(admin, adminSignIn);
            return new ResponseEntity<>(numbering, HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "register/batch", method = RequestMethod.POST)
    public ResponseEntity<Result> batchRegister(@RequestBody BatchRegister batchRegister) {
        try {
            List<Staff> staffList = batchRegister.getStaffList();
            List<StaffSignIn> staffSignInList = batchRegister.getStaffSignInList();
            accountService.batchRegister(staffList, staffSignInList);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "modify/staff", method = RequestMethod.POST)
    public ResponseEntity<Result> modifyStaffPassword(@RequestBody StaffModifyPwd staffModifyPwd) {
        try {
            if(accountService.staffModifyPwd(staffModifyPwd))
                return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "modify/admin", method = RequestMethod.POST)
    public ResponseEntity<Result> modifyAdminPassword(@RequestBody AdminModifyPwd adminModifyPwd) {
        try {
            if(accountService.adminModifyPwd(adminModifyPwd))
                return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "modify/force", method = RequestMethod.POST)
    public ResponseEntity<Result> forceModifyStaff(
            @RequestParam(value = "userId") String userId) {
        try {
            accountService.forceModifyStaff(userId);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "modify/force/admin", method = RequestMethod.POST)
    public ResponseEntity<Result> forceModifyAdmin(
            @RequestParam(value = "userId") String userId) {
        try {
            accountService.forceModifyAdmin(userId);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }
}
