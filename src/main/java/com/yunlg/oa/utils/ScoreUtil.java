package com.yunlg.oa.utils;

import java.text.DecimalFormat;

public class ScoreUtil {
    public static float getFinalScore(float s1, float s2) {
        DecimalFormat format = new DecimalFormat( "0.00 ");
        float score = (s1 + s2) / 2;
        return Float.parseFloat(format.format(score));
    }
}
