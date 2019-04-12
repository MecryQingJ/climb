package com.project.mini.spring.framework.beans.config;

import lombok.Data;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:04 PM 2019/4/11
 **/
@Data
public class QJBeanDefiniton {

    private boolean lazyInit = false;
    private volatile String beanClassName;
    private String factoryBeanName;

}
