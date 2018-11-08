package com.yunlg.oa.auth.interceptor;

import com.yunlg.oa.domain.Result;
import com.yunlg.oa.exception.InterceptorException;
import com.yunlg.oa.utils.InterceptorUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        try {
            this.userLoginValidate(httpServletRequest, httpServletResponse, handler);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            InterceptorUtil.printMessage(httpServletResponse, new Result(Result.RESULT_NO_AUTHORITY));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws InterceptorException {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws InterceptorException {

    }

    private void userLoginValidate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            httpServletResponse.sendRedirect("/yunlg/oa/login");
            throw new Exception("not login");
        }
    }
}

//    private void userAuthValidate(HttpServletRequest request, Object handler) throws Exception {
//        AuthValidate validate = ((HandlerMethod) handler).getMethodAnnotation(AuthValidate.class);
//        if(validate == null){
//            throw new Exception("未配置自定义注解");
//        }
//        String funcCode = validate.value().getAuthCode();
//        if(funcCode.equals(AuthCode.Allow.getAuthCode())){
//            return;
//        }
//        String authId = validate.value().getAuthId();
//        List<String> authList = new ArrayList<>();//模拟从缓存或者从数据库中查询出对应用户的权限
//        if(!authList.contains(authId)){
//            throw new Exception("权限不足");
//        }
//    }

//if(handler instanceof HandlerMethod){
//        HandlerMethod handlerMethod = (HandlerMethod)handler;
//        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//        for(MethodParameter methodParameter : methodParameters){
//        System.out.println(methodParameter.getParameterName());
//        }
//        }
