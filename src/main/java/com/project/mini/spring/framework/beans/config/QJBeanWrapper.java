package com.project.mini.spring.framework.beans.config;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 1:58 PM 2019/4/16
 **/
public class QJBeanWrapper {
    private Class<?> wrappedClass;
    private Object wrappedInstance;

    public QJBeanWrapper(Object wrappedInstance){
        this.wrappedInstance=wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return this.wrappedInstance.getClass();
    }

    public Object getWrappedInstance() {
        return this.wrappedInstance;
    }

}
