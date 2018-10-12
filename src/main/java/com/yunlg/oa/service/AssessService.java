package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.exception.AssessServiceException;

import java.util.List;

public interface AssessService {
    Assessment getAssessment(String userId, int month) throws AssessServiceException;

    void submitAssessment(Assessment assessment) throws AssessServiceException;

    List<Assessment> viewAssessment(int department, int month) throws AssessServiceException;

    void evaluateAssessment(Assessment assessment) throws AssessServiceException;
}
