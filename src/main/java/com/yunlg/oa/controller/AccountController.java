package com.yunlg.oa.controller;

import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.exception.AccountServiceException;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {""})
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login/staff", method = RequestMethod.GET)
    public ResponseEntity<Staff> staffLogin(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password) {
        try {
            Staff staff = accountService.staffLogin(userId, password);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "/login/admin", method = RequestMethod.GET)
    public ResponseEntity<Admin> adminLogin(
            @RequestParam(value = "adminId") String userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "numbering") String numbering) {
        try {
            Admin admin = accountService.adminLogin(userId, password, numbering);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }
}
