package com.yunlg.oa.persistence;

import javax.persistence.PersistenceException;

public interface AdminSignInDAO {
    AdminSignIn getAdminSignIn(String userId, String numbering) throws PersistenceException;

    void updateAdminSignIn(AdminSignIn adminSignIn) throws PersistenceException;

    void forceUpdateAdminSignIn(AdminSignIn adminSignIn) throws PersistenceException;
}
