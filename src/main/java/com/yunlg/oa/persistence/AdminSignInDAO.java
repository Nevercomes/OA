package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Admin;

import javax.persistence.PersistenceException;

public interface AdminSignInDAO {
    Admin adminSignIn(String userId, String password, String numbering) throws PersistenceException;
}
