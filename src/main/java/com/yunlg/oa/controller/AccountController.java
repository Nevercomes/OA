package com.yunlg.oa.controller;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.wrapper.UserModifyPwd;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/account"})
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<Result> modifyStaffPassword(@RequestBody UserModifyPwd userModifyPwd) {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            String userId = (String) session.getAttribute("userId");
            if (accountService.modifyPwd(userId, userModifyPwd))
                return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Result(Result.RESULT_WRONG_PASSWORD), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AU0000)
    public String logout(HttpServletRequest request) {
        try {
            System.out.println("hello");
            HttpSession session = request.getSession(false);
            if (session == null) {
                return "redirect:/login";
            } else {
                session.removeAttribute("userId");
                session.removeAttribute("name");
                session.removeAttribute("department");
                session.removeAttribute("position");
                return "redirect:/login";
            }
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }
}
