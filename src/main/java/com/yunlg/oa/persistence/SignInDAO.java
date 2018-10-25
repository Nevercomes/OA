package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.SignIn;

import javax.persistence.PersistenceException;

public interface SignInDAO {
    SignIn getSignIn(String userId) throws PersistenceException;

    void updateSignIn(SignIn signIn) throws PersistenceException;
}
