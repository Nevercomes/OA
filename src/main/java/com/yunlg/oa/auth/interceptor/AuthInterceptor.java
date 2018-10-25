package com.yunlg.oa.auth.interceptor;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
import com.yunlg.oa.exception.ExceptionMessage;
import com.yunlg.oa.exception.InterceptorException;
import com.yunlg.oa.global.Department;
import com.yunlg.oa.persistence.InterceptorDAO;
import com.yunlg.oa.persistence.impl.InterceptorDAOImpl;
import com.yunlg.oa.utils.DepartmentMapping;
import com.yunlg.oa.utils.InterceptorUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

public class AuthInterceptor implements HandlerInterceptor {

    private InterceptorDAO interceptorDAO = new InterceptorDAOImpl();

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
        String depInSession = (String) session.getAttribute("department");
        Department department = DepartmentMapping.getDepartment(depInSession);
        try {
            List<String> authCodeList = interceptorDAO.getAuthCodeList(userId);
            if(dep == -1) { // // without department restriction 单纯的检查是否有对应的AuthCode
                if(!authCodeList.contains(authCode)) {
                    throw new InterceptorException(ExceptionMessage.NOAUTHORITY);
                }
            } else { // with department restriction
                if(department == Department.ALL) {
                    if(!authCodeList.contains(authCode)) {
                        throw new InterceptorException(ExceptionMessage.NOAUTHORITY);
                    }
                } else { // 1.have auth code 2. check auth's dep whether is 0 3.if not 0 the judge equal
                    if(!authCodeList.contains(authCode)) {
                        throw new InterceptorException(ExceptionMessage.NOAUTHORITY);
                    } else { // 大致逻辑就是要判断那个auth的dep数量，如果就是一个0，那就表示是对全体开放的，不然的他就不会是1级的auth
                        List<Integer> depNum = interceptorDAO.getAuthDepNum(authCode);
                        if(depNum.size()==1 && depNum.get(0)==0) {
                            return;
                        } else {
                            if(dep != DepartmentMapping.getDepartmentCode(department))
                                throw new InterceptorException(ExceptionMessage.NOAUTHORITY);
                        }
                    }
                }
            }
        } catch (PersistenceException pe) {
            throw new InterceptorException(pe);
        }
    }
}

//    List<String> auths = new ArrayList<>(); // 模拟从缓存或者从数据库中查询出对应用户的权限
//        if (!auths.contains(authCode)) {
//            throw new InterceptorException(ExceptionMessage.NOAUTHORITY);
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//        for (MethodParameter methodParameter : methodParameters) {
//            System.out.println(methodParameter.getParameterName());
//        }
