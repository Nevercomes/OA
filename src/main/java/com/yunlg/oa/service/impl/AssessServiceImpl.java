package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.exception.AssessServiceException;
import com.yunlg.oa.persistence.AssessmentDAO;
import com.yunlg.oa.service.AssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class AssessServiceImpl implements AssessService {

    private AssessmentDAO assessmentDAO;

    @Autowired
    public AssessServiceImpl(AssessmentDAO assessmentDAO) {
        this.assessmentDAO = assessmentDAO;
    }

    @Override
    public Assessment getAssessment(String userId, int month) throws AssessServiceException {
        try {
            Assessment assessment = assessmentDAO.getAssessment(userId, month);
            assessment.setAssessHeadEva(null);
            assessment.setAssessHeadScore(0);
            assessment.setAssessDirectorEva(null);
            assessment.setAssessDirectorScore(0);
            return assessment;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public void submitAssessment(Assessment assessment) throws AssessServiceException {
        try {
            long assessId = assessment.getAssessId();
            if(assessId == 0)
                assessmentDAO.saveAssessment(assessment);
            else
                assessmentDAO.updateAssessment(assessment);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public List<Assessment> viewAssessment(int department, int month) throws AssessServiceException {
        try {
            List<Assessment> list = assessmentDAO.getAssessmentList(department, month);
            return list;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public void evaluateAssessment(Assessment assessment) throws AssessServiceException {
        try {
            assessmentDAO.evaluateAssessment(assessment);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }
}
