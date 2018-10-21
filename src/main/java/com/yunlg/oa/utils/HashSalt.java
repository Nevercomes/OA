package com.yunlg.oa.utils;

import com.yunlg.oa.domain.model.SignIn;

import java.util.List;

public class HashSalt {

    public static SignIn addSalt(SignIn signIn) {
        String salt = CryptoUtils.getSalt();
        String hashedPassword = CryptoUtils.getHash(signIn.getPassword(), salt);
        signIn.setPassword(hashedPassword);
        signIn.setSalt(salt);
        return signIn;
    }

    public static List<SignIn> addSalt(List<SignIn> signInList) {
        for(SignIn signIn : signInList) {
            String salt = CryptoUtils.getSalt();
            String hashedPassword = CryptoUtils.getHash(signIn.getPassword(), salt);
            signIn.setPassword(hashedPassword);
            signIn.setSalt(salt);
        }
        return signInList;
    }

    public static boolean verify(String hashedPassword, String password, String salt) {
        return CryptoUtils.verify(hashedPassword, password, salt);
    }
}
