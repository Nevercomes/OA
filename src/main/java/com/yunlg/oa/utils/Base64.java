package com.yunlg.oa.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Base64 {

    public static String decode(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(str));
    }
}
