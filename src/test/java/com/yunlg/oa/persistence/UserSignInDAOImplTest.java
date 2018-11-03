package com.yunlg.oa.persistence;

import com.yunlg.oa.persistence.impl.SignInDAOImpl;
import org.junit.Test;

public class UserSignInDAOImplTest {

    private SignInDAO signInDAO = new SignInDAOImpl();

    @Test
    public void testStaffSignIn() {
       System.out.println(signInDAO.getSignIn("3901170505"));
    }

}
