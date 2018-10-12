package com.yunlg.oa.controller;

import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.exception.AssessServiceException;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.service.AssessService;
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
@RequestMapping(value = {"/assess"})
public class AssessController {

    private AssessService assessService;

    @Autowired
    public AssessController(AssessService assessService) {
        this.assessService = assessService;
    }

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public ResponseEntity<Assessment> getAssessment(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "month") int month) {
        try {
            Assessment assessment = assessService.getAssessment(userId, month);
            return new ResponseEntity<>(assessment, HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResponseEntity<Result> submitAssessment(@RequestBody Assessment assessment) {
        try {
            assessService.submitAssessment(assessment);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<List<Assessment>> viewAssessment(
            @RequestParam(value = "department") int department,
            @RequestParam(value = "month") int month) {
        try {
            List<Assessment> list = assessService.viewAssessment(department, month);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    public ResponseEntity<Result> evaluateAssessment(@RequestBody Assessment assessment) {
        try {
            assessService.evaluateAssessment(assessment);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AssessServiceException sse) {
            throw new CatchServiceException(sse);
        }
    }
}
