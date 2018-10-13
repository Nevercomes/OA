package com.yunlg.oa.utils;

import java.util.Random;

public class NumberingRandom {
    public static String getAdminNumbering() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<6; i++) {
            int a = getRandom(65, 90);
            int b = getRandom(0,10);
            char ch = ' ';
            if(b <= 3)
                sb.append(getRandom(0,9));
            else if(b <= 6)
                sb.append((char)a);
            else
                sb.append(String.valueOf((char)a).toLowerCase());
        }
        return sb.toString();
    }

    public static int getRandom(int a, int b) {
        Random random = new Random();
        int s = random.nextInt(b) % (b-a+1) + a;
        return s;
    }
}
