package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.orm.ViewAssessORM;
import com.yunlg.oa.domain.wrapper.ViewAssessment;
import com.yunlg.oa.domain.wrapper.ViewResult;
import com.yunlg.oa.exception.AssessServiceException;
import com.yunlg.oa.persistence.AssessResultDAO;
import com.yunlg.oa.persistence.AssessmentDAO;
import com.yunlg.oa.service.AssessService;
import com.yunlg.oa.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssessServiceImpl implements AssessService {

    private AssessmentDAO assessmentDAO;
    private AssessResultDAO assessResultDAO;

    @Autowired
    public AssessServiceImpl(AssessmentDAO assessmentDAO, AssessResultDAO assessResultDAO) {
        this.assessmentDAO = assessmentDAO;
        this.assessResultDAO = assessResultDAO;
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
            if (assessId == 0)
                assessmentDAO.saveAssessment(assessment);
            else
                assessmentDAO.updateAssessment(assessment);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public List<ViewAssessment> viewAssessment(int department, int month) throws AssessServiceException {
        try {
            List<ViewAssessment> viewAssessmentList = new ArrayList<>();
            List<ViewAssessORM> ormList = assessmentDAO.getViewResultORMList(department, month);
            for (ViewAssessORM orm : ormList) {
                Assessment assessment = orm.getAssessment();
                Staff staff = orm.getStaff();
                ViewAssessment viewAssessment = new ViewAssessment();
                viewAssessment.setAssessment(assessment);
                viewAssessment.setName(staff.getName());
                viewAssessment.setDepartment(staff.getDepartment());
                viewAssessment.setPosition(staff.getPosition());
                viewAssessmentList.add(viewAssessment);
            }
            return viewAssessmentList;
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

    @Override
    public List<ViewResult> getViewResultList(int department, int month) throws AssessServiceException {
        try {
            List<ViewResult> viewResultList = new ArrayList<>();
            List<ViewAssessORM> ormList = assessmentDAO.getViewResultORMList(department, month);
            for (ViewAssessORM orm : ormList) {
                Assessment assessment = orm.getAssessment();
                Staff staff = orm.getStaff();
                ViewResult viewResult = new ViewResult();
                viewResult.setName(staff.getName());
                viewResult.setDepartment(staff.getDepartment());
                viewResult.setPosition(staff.getPosition());
                viewResult.setScore(ScoreUtil.getFinalScore(assessment.getAssessHeadScore(), assessment.getAssessDirectorScore()));
                viewResultList.add(viewResult);
            }
            return viewResultList;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public AssessResult getAssessResult(int department, int month) throws AssessServiceException {
        try {
            return assessResultDAO.getAssessResult(department, month);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }
}
