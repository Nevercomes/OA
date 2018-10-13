package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;

import javax.persistence.PersistenceException;

public interface StaffSignInDAO {
    StaffSignIn getStaffSignIn(String userId) throws PersistenceException;

    void updateStaffSign(StaffSignIn staffSignIn) throws PersistenceException;
}
