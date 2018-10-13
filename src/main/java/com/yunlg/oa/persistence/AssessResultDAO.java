package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.AssessResult;

import javax.persistence.PersistenceException;

public interface AssessResultDAO {
    AssessResult getAssessResult(int department, int month) throws PersistenceException;
}
