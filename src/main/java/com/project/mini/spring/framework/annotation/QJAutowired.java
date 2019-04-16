package com.project.mini.spring.framework.annotation;

import java.lang.annotation.*;


/**
 * 自动注入
 * @author Tom
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QJAutowired {
	String value() default "";
}
