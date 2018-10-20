package com.yunlg.oa.persistence;

import javax.persistence.PersistenceException;

public interface AdminDAO {
    void saveAdmin(Admin admin, AdminSignIn adminSignIn) throws PersistenceException;

    Admin getAdmin(String userId) throws PersistenceException;

}
