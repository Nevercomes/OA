package com.yunlg.oa.utils;

import com.yunlg.oa.global.Department;
import com.yunlg.oa.global.Position;

public class RoleMapping {
    public static int getRole(int department, int position) {
        Position position1 = PositionMapping.getPosition(position);
        Department department1 = DepartmentMapping.getDepartment(department);
        if (department1 == Department.ALL) {
            if (position1 == Position.INSTRUCTOR)
                return 1;
            else if (position1 == Position.DIRECTOR)
                return 2;
        } else if (department1==Department.RD && position1==Position.HEAD) {
            return 3;
        } else if (department1==Department.NETWORKS && position1==Position.HEAD) {
            return 4;
        } else if (department1==Department.ART && position1==Position.HEAD) {
            return 5;
        }
        return -1;
    }
}
