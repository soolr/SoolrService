package com.soolr.api.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志自定义注解
 * @author Soolr
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
        String description() default "";
}