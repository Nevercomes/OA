package com.yunlg.oa.persistence;

import com.yunlg.oa.persistence.impl.StaffSignInDAOImpl;
import org.junit.Test;

public class StaffSignInDAOImplTest {

    private StaffSignInDAO staffSignInDAO = new StaffSignInDAOImpl();

    @Test
    public void testStaffSignIn() {
       System.out.println(staffSignInDAO.getStaffSignIn("3901170505"));
    }

}
