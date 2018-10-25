package com.yunlg.oa.service;

import com.yunlg.oa.domain.model.AssessRecord;
import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.wrapper.ResultWrapper;
import com.yunlg.oa.domain.wrapper.AssessmentWrapper;
import com.yunlg.oa.exception.AssessServiceException;

import java.util.List;

public interface AssessService {
    Assessment getAssessment(String userId, int month) throws AssessServiceException;

    void submitAssessment(Assessment assessment) throws AssessServiceException;

    List<AssessmentWrapper> viewAssessment(int department, int month) throws AssessServiceException;

    AssessmentWrapper viewAssessment(String userId, int month) throws AssessServiceException;

    void evaluateAssessment(Assessment assessment, String department) throws AssessServiceException;

    List<ResultWrapper> getResultList(int department, int month) throws AssessServiceException;

    AssessResult getAssessResult(int department, int month) throws AssessServiceException;

    void saveAssessResult(int department, int month) throws AssessServiceException;

    List<List<ResultWrapper>> getResultLists(int department, int month) throws AssessServiceException;

    AssessRecord getAssessTime() throws AssessServiceException;

    void setAssessTime(AssessRecord assessRecord) throws AssessServiceException;

    List<List<ResultWrapper>> getResultListsByStaff(int department, int month) throws AssessServiceException;
}
