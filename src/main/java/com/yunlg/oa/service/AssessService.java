package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.wrapper.ViewAssessment;
import com.yunlg.oa.domain.wrapper.ViewResult;
import com.yunlg.oa.exception.AssessServiceException;

import java.util.List;

public interface AssessService {
    Assessment getAssessment(String userId, int month) throws AssessServiceException;

    void submitAssessment(Assessment assessment) throws AssessServiceException;

    List<ViewAssessment> viewAssessment(int department, int month) throws AssessServiceException;

    void evaluateAssessment(Assessment assessment) throws AssessServiceException;

    List<ViewResult> getViewResultList(int department, int month) throws AssessServiceException;

    AssessResult getAssessResult(int department, int month) throws AssessServiceException;
}
