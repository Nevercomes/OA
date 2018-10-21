package com.yunlg.oa.auth;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface AuthValidate {
    AuthCode value() default AuthCode.Allow;
}
