package com.yunlg.oa.service.impl;

import com.yunlg.oa.domain.model.AssessRecord;
import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.orm.AssessmentORM;
import com.yunlg.oa.domain.wrapper.AssessmentWrapper;
import com.yunlg.oa.domain.wrapper.ResultWrapper;
import com.yunlg.oa.exception.AssessServiceException;
import com.yunlg.oa.global.Department;
import com.yunlg.oa.persistence.AssessRecordDAO;
import com.yunlg.oa.persistence.AssessResultDAO;
import com.yunlg.oa.persistence.AssessmentDAO;
import com.yunlg.oa.service.AssessService;
import com.yunlg.oa.utils.DepartmentMapping;
import com.yunlg.oa.utils.PositionMapping;
import com.yunlg.oa.utils.ScoreUtil;
import com.yunlg.oa.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssessServiceImpl implements AssessService {

    private AssessmentDAO assessmentDAO;
    private AssessResultDAO assessResultDAO;
    private AssessRecordDAO assessRecordDAO;

    @Autowired
    public AssessServiceImpl(AssessmentDAO assessmentDAO, AssessResultDAO assessResultDAO, AssessRecordDAO assessRecordDAO) {
        this.assessmentDAO = assessmentDAO;
        this.assessResultDAO = assessResultDAO;
        this.assessRecordDAO = assessRecordDAO;
    }

    @Override
    public Assessment getAssessment(String userId, int month) throws AssessServiceException {
        try {
            Assessment assessment = assessmentDAO.getAssessment(userId, month);
            if (assessment == null) {
                return null;
            }
            return assessment;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public void submitAssessment(Assessment assessment) throws AssessServiceException {
        try {
            long assessId = assessment.getAssessId();
            assessment.setWorkModifyTime(TimeUtil.getCurrentDate());
            if (assessId == 0) {
                assessment.setAssessHeadEva(null);
                assessment.setAssessHeadScore(-1);
                assessment.setAssessDirectorEva(null);
                assessment.setAssessDirectorScore(-1);
                AssessRecord assessRecord = assessRecordDAO.getAssessRecord();
                assessment.setMonth(assessRecord.getMonth());
                assessmentDAO.saveAssessment(assessment);
            }
            else
                assessmentDAO.updateAssessment(assessment);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public List<AssessmentWrapper> viewAssessment(int department, int month) throws AssessServiceException {
        try {
            List<AssessmentWrapper> assessmentWrapperList = new ArrayList<>();
            List<AssessmentORM> ormList = assessmentDAO.getAssessmentORMList(department, month);
            if(ormList == null) {
                return new ArrayList<>();
            }
            for (AssessmentORM orm : ormList) {
                Assessment assessment = orm.getAssessment();
                User user = orm.getUser();
                AssessmentWrapper assessmentWrapper = new AssessmentWrapper();
                assessmentWrapper.setAssessment(assessment);
                assessmentWrapper.setName(user.getName());
                assessmentWrapper.setDepartment(user.getDepartment());
                assessmentWrapper.setPosition(user.getPosition());
                assessmentWrapperList.add(assessmentWrapper);
            }
            return assessmentWrapperList;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public AssessmentWrapper viewAssessment(String userId, int month) throws AssessServiceException {
        try {
            AssessmentORM orm = assessmentDAO.getAssessmentORM(userId, month);
            if(orm == null) {
                return new AssessmentWrapper();
            }
            Assessment assessment = orm.getAssessment();
            User user = orm.getUser();
            AssessmentWrapper assessmentWrapper = new AssessmentWrapper();
            assessmentWrapper.setAssessment(assessment);
            assessmentWrapper.setName(user.getName());
            assessmentWrapper.setDepartment(user.getDepartment());
            assessmentWrapper.setPosition(user.getPosition());
            return assessmentWrapper;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public void evaluateAssessment(Assessment assessment, String department) throws AssessServiceException {
        try {
            Department department1 = DepartmentMapping.getDepartment(department);
            assessment.setAssessModifyTime(TimeUtil.getCurrentDate());
            if(department1 == Department.ALL) {
                assessmentDAO.updateDirector(assessment);
            } else {
                assessmentDAO.updateHead(assessment);
            }
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public List<ResultWrapper> getResultList(int department, int month) throws AssessServiceException {
        try {
            AssessResult assessResult = assessResultDAO.getAssessResult(department, month);
            if(assessResult == null) {
                return null;
            }
            List<ResultWrapper> resultWrapperList = new ArrayList<>();
            List<AssessmentORM> ormList = assessmentDAO.getAssessmentORMList(department, month);
            int i = 1;
            for (AssessmentORM orm : ormList) {
                Assessment assessment = orm.getAssessment();
                User user = orm.getUser();
                ResultWrapper resultWrapper = new ResultWrapper();
                resultWrapper.setStaffName(user.getName());
                resultWrapper.setDepartment(DepartmentMapping.getDepartmentStr(DepartmentMapping.getDepartment(user.getDepartment())));
                resultWrapper.setPosition(PositionMapping.getPositionStr(PositionMapping.getPosition(user.getPosition())));
                resultWrapper.setScore(ScoreUtil.getFinalScore(assessment.getAssessHeadScore(), assessment.getAssessDirectorScore()));
                resultWrapper.setRank(i++);
                resultWrapperList.add(resultWrapper);
            }
            return resultWrapperList;
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

    @Override
    public void saveAssessResult(int department, int month) throws AssessServiceException {
        try {
            assessResultDAO.updateAssessResult(department, month);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public List<List<ResultWrapper>> getResultListsByStaff(int department, int month) throws AssessServiceException {
        AssessResult assessResult = assessResultDAO.getAssessResult(department, month);
        if(assessResult == null) {
            return new ArrayList<>();
        } else {
            return getResultLists(department, month);
        }
    }

    @Override
    public List<List<ResultWrapper>> getResultLists(int department, int month) throws AssessServiceException {
        try {
            List<List<ResultWrapper>> lists = new ArrayList<>();
            List<List<AssessmentORM>> ormLists = assessmentDAO.getResultORMLists(department, month);
            if(ormLists == null) {
                return new ArrayList<>();
            }
            for(int i=0; i<ormLists.size(); i++) {
                System.out.println(ormLists.size());
                List<AssessmentORM> ormList = ormLists.get(i);
                int k = 0;
                /**
                 * 花了近两个小时找出来的bug
                 */
                List<ResultWrapper> list = new ArrayList<>();
                for(AssessmentORM orm : ormList) {
                    System.out.println(ormList.size());
                    Assessment assessment = orm.getAssessment();
                    User user = orm.getUser();
                    ResultWrapper resultWrapper = new ResultWrapper();
                    resultWrapper.setStaffName(user.getName());
                    resultWrapper.setDepartment(DepartmentMapping.getDepartmentStr(DepartmentMapping.getDepartment(user.getDepartment())));
                    resultWrapper.setPosition(PositionMapping.getPositionStr(PositionMapping.getPosition(user.getPosition())));
                    resultWrapper.setScore(ScoreUtil.getFinalScore(assessment.getAssessHeadScore(), assessment.getAssessDirectorScore()));
                    resultWrapper.setRank(++k);
                    list.add(resultWrapper);
                }
                lists.add(list);
            }
            return lists;
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public AssessRecord getAssessTime() throws AssessServiceException {
        try {
            return assessRecordDAO.getAssessRecord();
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }

    @Override
    public void setAssessTime(AssessRecord assessRecord) throws AssessServiceException {
        try {
            assessRecord.setModifyTime(TimeUtil.getCurrentDate());
            assessRecordDAO.updateAssessRecord(assessRecord);
        } catch (PersistenceException pe) {
            throw new AssessServiceException(pe);
        }
    }
}
