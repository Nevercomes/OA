package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.AdminSignIn;

import javax.persistence.PersistenceException;

public interface AdminDAO {
    void saveAdmin(Admin admin, AdminSignIn adminSignIn) throws PersistenceException;

    Admin getAdmin(String userId) throws PersistenceException;

}
