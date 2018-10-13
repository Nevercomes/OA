package com.yunlg.oa.utils;

import com.yunlg.oa.domain.model.AdminSignIn;
import com.yunlg.oa.domain.model.StaffSignIn;

import java.util.List;

public class HashSalt {
    public static AdminSignIn addSalt(AdminSignIn adminSignIn) {
        String salt = CryptoUtils.getSalt();
        String hashedPassword = CryptoUtils.getHash(adminSignIn.getPassword(), salt);
        adminSignIn.setPassword(hashedPassword);
        adminSignIn.setSalt(salt);
        return adminSignIn;
    }

    public static StaffSignIn addSalt(StaffSignIn staffSignIn) {
        String salt = CryptoUtils.getSalt();
        String hashedPassword = CryptoUtils.getHash(staffSignIn.getPassword(), salt);
        staffSignIn.setPassword(hashedPassword);
        staffSignIn.setSalt(salt);
        return staffSignIn;
    }

    public static List<StaffSignIn> addSalt(List<StaffSignIn> staffSignInList) {
        for(StaffSignIn staffSignIn : staffSignInList) {
            String salt = CryptoUtils.getSalt();
            String hashedPassword = CryptoUtils.getHash(staffSignIn.getPassword(), salt);
            staffSignIn.setPassword(hashedPassword);
            staffSignIn.setSalt(salt);
        }
        return staffSignInList;
    }

    public static boolean verify(String hashedPassword, String password, String salt) {
        return CryptoUtils.verify(hashedPassword, password, salt);
    }
}
