package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Assessment;

import javax.persistence.PersistenceException;
import java.util.List;

public interface AssessmentDAO {
    Assessment getAssessment(String userId, int month) throws PersistenceException;

    void updateAssessment(Assessment assessment) throws PersistenceException;

    void saveAssessment(Assessment assessment) throws PersistenceException;

    List<Assessment> getAssessmentList(int department, int month) throws PersistenceException;

    void evaluateAssessment(Assessment assessment) throws PersistenceException;
}
