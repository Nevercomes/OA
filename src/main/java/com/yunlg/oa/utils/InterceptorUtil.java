package com.yunlg.oa.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class InterceptorUtil {
    public static void printMessage(HttpServletResponse response, Object res) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JsonUtil.toJson(res));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
