package com.yunlg.oa.persistence;

import com.yunlg.oa.persistence.impl.StaffDAOImpl;
import org.junit.Test;

public class StaffDAOImplTest {

    private StaffDAO staffDAO = new StaffDAOImpl();

    @Test
    public void testGetStaff() {
        System.out.println(staffDAO.getStaff("3901170505"));
    }
}
