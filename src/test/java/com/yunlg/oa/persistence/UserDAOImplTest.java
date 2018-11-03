package com.yunlg.oa.persistence;

import com.yunlg.oa.persistence.impl.UserDAOImpl;
import org.junit.Test;

public class UserDAOImplTest {

    private UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testGetStaff() {
        System.out.println(userDAO.getStaff("3901170505"));
    }
}
