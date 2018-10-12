package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.exception.AccountServiceException;

import javax.persistence.PersistenceException;

public interface StaffDAO {
    Staff getStaff(String userId) throws PersistenceException;
}
