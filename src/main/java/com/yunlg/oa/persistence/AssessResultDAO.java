package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.AssessResult;
import com.yunlg.oa.domain.orm.AssessmentORM;

import javax.persistence.PersistenceException;
import java.util.List;

public interface AssessResultDAO {
    AssessResult getAssessResult(int department, int month) throws PersistenceException;

    void updateAssessResult(int department, int month) throws PersistenceException;
}
