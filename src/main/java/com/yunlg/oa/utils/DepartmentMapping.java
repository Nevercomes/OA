package com.yunlg.oa.utils;

import com.yunlg.oa.global.Department;

public class DepartmentMapping {
    public static Department getDepartment(int code) {
        switch (code) {
            case 0:
                return Department.ALL;
            case 1:
                return Department.RD;
            case 2:
                return Department.NETWORKS;
            case 3:
                return Department.ART;
            default:
                return Department.ALL;
        }
    }

    public static int getDepartmentCode(Department department) {
        switch (department) {
            case ALL:
                return 0;
            case RD:
                return 1;
            case NETWORKS:
                return 2;
            case ART:
                return 3;
            default:
                return 0;
        }
    }
}
