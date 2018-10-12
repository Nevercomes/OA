package com.yunlg.oa.utils;

import com.yunlg.oa.global.Department;
import com.yunlg.oa.global.Position;

public class PositionMapping {
    public static Position getPosition(int code) {
        switch (code) {
            case 0:
                return Position.STAFF;
            case 1:
                return Position.INSTURCTOR;
            case 2:
                return Position.DIRECTOR;
            case 3:
                return Position.HEAD;
            case 4:
                return Position.VICEHEAD;
            case 5:
                return Position.GROUP;
            default:
                return Position.STAFF;
        }
    }

    public static int getPositionCode(Position position) {
        switch (position) {
            case STAFF:
                return 0;
            case INSTURCTOR:
                return 1;
            case DIRECTOR:
                return 2;
            case HEAD:
                return 3;
            case VICEHEAD:
                return 4;
            case GROUP:
                return 5;
            default:
                return 0;
        }
    }
}
