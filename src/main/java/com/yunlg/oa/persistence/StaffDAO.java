package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.exception.AccountServiceException;

import javax.persistence.PersistenceException;
import java.util.List;

public interface StaffDAO {
    Staff getStaff(String userId) throws PersistenceException;

    void batchSaveStaff(List<Staff> staffList, List<StaffSignIn> staffSignInList) throws PersistenceException;
}
