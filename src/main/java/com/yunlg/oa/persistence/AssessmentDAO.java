package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.orm.AssessmentORM;

import javax.persistence.PersistenceException;
import java.util.List;

public interface AssessmentDAO {
    Assessment getAssessment(String userId, int month) throws PersistenceException;

    void updateAssessment(Assessment assessment) throws PersistenceException;

    void saveAssessment(Assessment assessment) throws PersistenceException;

    List<Assessment> getAssessmentList(int department, int month) throws PersistenceException;

    void evaluateAssessment(Assessment assessment) throws PersistenceException;

    List<AssessmentORM> getAssessmentORMList(int department, int month) throws PersistenceException;

    AssessmentORM getAssessmentORM(String userId, int month) throws PersistenceException;

    List<List<AssessmentORM>> getResultORMLists(int department, int month) throws PersistenceException;

    void updateDirector(Assessment assessment) throws PersistenceException;

    void updateHead(Assessment assessment) throws PersistenceException;
}
