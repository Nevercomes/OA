package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.SignIn;

import javax.persistence.PersistenceException;

public interface SignInDAO {
    SignIn getSignIn(String userId) throws PersistenceException;

    void updateStaffSignIn(SignIn signIn) throws PersistenceException;
}
