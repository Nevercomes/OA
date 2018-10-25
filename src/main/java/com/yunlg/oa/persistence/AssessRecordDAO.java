package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.AssessRecord;

import javax.persistence.PersistenceException;

public interface AssessRecordDAO {

    /**
     * return last one in the list
     * @return
     * @throws PersistenceException
     */
    AssessRecord getAssessRecord() throws PersistenceException;

    void updateAssessRecord(AssessRecord assessRecord) throws PersistenceException;
}
