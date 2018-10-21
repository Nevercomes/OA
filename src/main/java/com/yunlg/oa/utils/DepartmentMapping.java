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

    public static String getDepartmentStr(Department department) {
        switch (department) {
            case ALL:
                return "中心";
            case RD:
                return "研发";
            case NETWORKS:
                return "网络";
            case ART:
                return "美工";
            default:
                return "中心";
        }
    }

    public static Department getDepartment(String str) {
        switch (str) {
            case "中心":
                return Department.ALL;
            case "研发":
                return Department.RD;
            case "网络":
                return Department.NETWORKS;
            case "美工":
                return Department.ART;
            default:
                return Department.ALL;
        }
    }
}

