package com.yunlg.oa.controller;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
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

@Controller
@RequestMapping({"/account"})
public class AccountController  {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<Result> modifyStaffPassword(@RequestBody StaffModifyPwd staffModifyPwd) {
        try {
            if (accountService.modifyPwd(staffModifyPwd))
                return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }
}
