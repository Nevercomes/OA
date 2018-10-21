package com.yunlg.oa.utils;

public class PositionAdmin {
    public static int getAdminInPosition(int pos) {
        switch (pos) {
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 1;
            default:
                return 0;
        }
    }
}
