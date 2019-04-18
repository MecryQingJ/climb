package com.project.mini.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * 业务逻辑,注入接口
 * @author Tom
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QJService {
	String value() default "";
}
