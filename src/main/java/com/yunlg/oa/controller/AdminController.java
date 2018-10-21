package com.yunlg.oa.controller;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.model.SignIn;
import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.wrapper.*;
import com.yunlg.oa.exception.*;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.service.AssessService;
import com.yunlg.oa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    private AccountService accountService;
    private AssessService assessService;
    private WorkService workService;

    @Autowired
    public AdminController(AccountService accountService, AssessService assessService, WorkService workService) {
        this.accountService = accountService;
        this.assessService = assessService;
        this.workService = workService;
    }

    @RequestMapping(value = "/assess/view", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AA1003)
    public ResponseEntity<List<AssessmentWrapper>> viewAssessment(
            @RequestParam(value = "department") int department,
            @RequestParam(value = "month") int month) {
        try {
            List<AssessmentWrapper> list = assessService.viewAssessment(department, month);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(ExceptionMessage.NORECORD, sse);
        }
    }

    @RequestMapping(value = "/assess/submit", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AA1004)
    public ResponseEntity<Result> evaluateAssessment(@RequestBody Assessment assessment) {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            String department = (String) session.getAttribute("department");
            assessService.evaluateAssessment(assessment, department);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "/result/view", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AA1001)
    public ResponseEntity<List<List<ResultWrapper>>> viewResult(
            @RequestParam int department,
            @RequestParam int month) {
        try {
            List<List<ResultWrapper>> resultWrapperLists = assessService.getResultLists(department, month);
            return new ResponseEntity<>(resultWrapperLists, HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "/result/public", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AA1002)
    public ResponseEntity<Result> publicAssessResult(
            @RequestParam(value = "department") int department,
            @RequestParam(value = "month") int month) {
        try {
            assessService.saveAssessResult(department, month);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "/plan/view", method = RequestMethod.GET)
    public ResponseEntity<List<WorkPlanWrapper>> viewWorkPlan(
            @RequestParam(value = "department") int department,
            @RequestParam(value = "month") int month) {
        try {
            List<WorkPlanWrapper> workPlanWrapperList = workService.getWorkPlanWrapperList(department, month);
            return new ResponseEntity<>(workPlanWrapperList, HttpStatus.OK);
        } catch (WorkServiceException we) {
            throw new CatchServiceException(we);
        }
    }

    @RequestMapping(value = "account/batch", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AA1000)
    public ResponseEntity<Result> batchRegister(@RequestBody List<BatchRegister> batchRegisterList) {
        try {
//            List<User> userList = batchRegister.getUserList();
//            List<SignIn> signInList = batchRegister.getSignInList();
            accountService.batchRegister(batchRegisterList);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "account/reset", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AA1006)
    public ResponseEntity<Result> resetStaffPwd(
            @RequestParam(value = "userId") String userId) {
        try {
            accountService.resetStaffPwd(userId);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AA2000)
    public ResponseEntity<Result> adminRegister(
            @RequestBody UserRegister userRegister) {
        try {
            accountService.adminRegister(userRegister);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException ae) {
            ae.printStackTrace();
            throw new CatchServiceException(ae);
        }
    }
}
