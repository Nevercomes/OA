package com.yunlg.oa.exception;

import com.yunlg.oa.exception.ExceptionCause;

public class ExceptionMapping {
    public static int getExceptionCode(ExceptionCause cause) {
        switch (cause) {
            case NOACCOUNT:
                return 1;
            case FALSEPASSWORD:
                return 2;
            case NOPRIVILEGE:
                return 3;
            case ALREADYHAVEACCOUNT:
                return 4;
            case NORECORD:
                return 5;
            default:
                return 0;
        }
    }
}
