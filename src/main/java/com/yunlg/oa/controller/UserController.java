package com.yunlg.oa.controller;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.domain.wrapper.AssessmentWrapper;
import com.yunlg.oa.domain.wrapper.ResultWrapper;
import com.yunlg.oa.domain.wrapper.WorkPlanWrapper;
import com.yunlg.oa.exception.AssessServiceException;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.exception.ExceptionMessage;
import com.yunlg.oa.exception.WorkServiceException;
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
@RequestMapping({"/staff"})
public class UserController {

    private AccountService accountService;
    private AssessService assessService;
    private WorkService workService;

    @Autowired
    public UserController(AccountService accountService, AssessService assessService, WorkService workService) {
        this.accountService = accountService;
        this.assessService = assessService;
        this.workService = workService;
    }

    @RequestMapping(value = "assess/fill", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<AssessmentWrapper> getAssessment(
            @RequestParam(value = "month") int month) {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            String userId = (String) session.getAttribute("userId");
            AssessmentWrapper assessmentWrapper = assessService.viewAssessment(userId, month);
            return new ResponseEntity<>(assessmentWrapper, HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(ExceptionMessage.NORECORD, sse);
        }
    }

    @RequestMapping(value = "assess/submit", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<Result> submitAssessment(@RequestBody Assessment assessment) {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            String userId = (String) session.getAttribute("userId");
            assessment.setUserId(userId);
            assessService.submitAssessment(assessment);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "plan/view", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<WorkPlanWrapper> getWorkPlan(
            @RequestParam int month) {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            String userId = (String) session.getAttribute("userId");
            WorkPlanWrapper workPlanWrapper = workService.getWorkPlanWrapper(userId, month);
            return new ResponseEntity<>(workPlanWrapper, HttpStatus.OK);
        } catch (WorkServiceException we) {
            throw new CatchServiceException(we);
        }
    }

    @RequestMapping(value = "/plan/submit", method = RequestMethod.POST)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<Result> submitWorkPlan(@RequestBody WorkPlan workPlan) {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            String userId = (String) session.getAttribute("userId");
            workPlan.setUserId(userId);
            workService.submitWorkPlan(workPlan);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (WorkServiceException we) {
            throw new CatchServiceException(we);
        }
    }

    @RequestMapping(value = "/result/view", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AU0000)
    public ResponseEntity<List<List<ResultWrapper>>> getResult(
            @RequestParam int department,
            @RequestParam int month) {
        try {
            AssessResult assessResult = assessService.getAssessResult(department, month);
            if(assessResult == null)
                return null;
            List<List<ResultWrapper>> resultWrapperLists = assessService.getResultLists(department, month);
            return new ResponseEntity<>(resultWrapperLists, HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }
}
