package com.project.mini.spring.framework.beans;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 1:36 PM 2019/4/11
 **/
public interface QJBeanFactory {

    /**
     * 根据bean的名字，获取在IOC容器中得到bean实例
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
