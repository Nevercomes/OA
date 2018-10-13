package com.yunlg.oa.controller;

import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.WorkPlan;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.exception.WorkServiceException;
import com.yunlg.oa.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/plan"})
public class WorkController {

    private WorkService workService;

    @RequestMapping(value = "/plan/submit", method = RequestMethod.POST)
    public ResponseEntity<Result> submitWorkPlan(@RequestBody WorkPlan workPlan) {
        try {
            workService.submitWorkPlan(workPlan);
            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (WorkServiceException we) {
            throw new CatchServiceException(we);
        }
    }

}
