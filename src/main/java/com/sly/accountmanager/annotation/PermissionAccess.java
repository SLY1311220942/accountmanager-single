package com.sly.accountmanager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * _权限拦截注解
 * _被注解的方法会进行权限拦截验证
 * @author sly
 * @time 2019年3月9日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionAccess {

}
