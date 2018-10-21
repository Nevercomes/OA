package com.yunlg.oa.auth.interceptor;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
import com.yunlg.oa.exception.ExceptionMessage;
import com.yunlg.oa.exception.InterceptorException;
import com.yunlg.oa.utils.InterceptorUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        try {
            this.userAuthValidate(httpServletRequest, httpServletResponse, handler);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            InterceptorUtil.printMessage(httpServletResponse, new Result(Result.RESULT_NO_AUTHORITY));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void userAuthValidate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws InterceptorException {
        AuthValidate validate = ((HandlerMethod) handler).getMethodAnnotation(AuthValidate.class);
        if (validate == null) {
            throw new InterceptorException(ExceptionMessage.NOANNOTATION);
        }
        String funcCode = validate.value().getAuthCode();
        if (funcCode.equals(AuthCode.Allow.getAuthCode()) || funcCode.equals(AuthCode.AU0000.getAuthCode())) {
            return;
        }
        Enumeration<String> keys = httpServletRequest.getParameterNames();
        int dep = -1;
        while(keys.hasMoreElements()) {
            String k = keys.nextElement();
            if(k.equals("department")) {
                dep = Integer.parseInt(httpServletRequest.getParameter(k));
            }
            System.out.println(k + " = " + httpServletRequest.getParameter(k));
        }
        String authCode = validate.value().getAuthCode();
        HttpSession session = httpServletRequest.getSession();
        String userId = (String) session.getAttribute("userId");
        if(dep == -1) {
            // no department restriction
        } else {
            // with department restriction
        }
        List<String> auths = new ArrayList<>(); // 模拟从缓存或者从数据库中查询出对应用户的权限
        if (!auths.contains(authCode)) {
            throw new InterceptorException(ExceptionMessage.NOAUTHORITY);
        }
    }
}

//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//        for (MethodParameter methodParameter : methodParameters) {
//            System.out.println(methodParameter.getParameterName());
//        }
