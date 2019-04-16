package com.project.mini.spring.framework.beans.support;

import com.project.mini.spring.framework.beans.config.QJBeanDefinition;
import com.project.mini.spring.framework.context.support.QJAbatractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:04 PM 2019/4/11
 **/
public class QJDefaultListableBeanFactory extends QJAbatractApplicationContext {
    //存储注册信息的BeanDefinition
    protected final Map<String, QJBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, QJBeanDefinition>(256);

}
