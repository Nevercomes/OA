package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.model.SignIn;

import javax.persistence.PersistenceException;
import java.util.List;

public interface UserDAO {
    User getStaff(String userId) throws PersistenceException;

    void batchSaveStaff(List<User> userList, List<SignIn> signInList) throws PersistenceException;

    void saveAdmin(User user, SignIn signIn) throws PersistenceException;
}
