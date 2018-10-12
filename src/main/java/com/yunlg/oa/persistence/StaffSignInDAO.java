package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Staff;

import javax.persistence.PersistenceException;

public interface StaffSignInDAO {
    Staff staffSignIn(String userId, String password) throws PersistenceException;
}
