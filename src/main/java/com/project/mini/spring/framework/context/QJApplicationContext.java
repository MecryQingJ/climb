package com.project.mini.spring.framework.context;

import com.project.mini.spring.framework.beans.QJBeanFactory;
import com.project.mini.spring.framework.beans.config.QJBeanDefiniton;
import com.project.mini.spring.framework.beans.support.QJBeanDefinitionReader;
import com.project.mini.spring.framework.beans.support.QJDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 1:44 PM 2019/4/11
 **/
public class QJApplicationContext extends QJDefaultListableBeanFactory implements QJBeanFactory {

    private String[] configLocations;
    private QJBeanDefinitionReader reader;

    public QJApplicationContext(String... configLocations){
        this.configLocations=configLocations;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1、定位 定位配置文件
        reader = new QJBeanDefinitionReader(this.configLocations);
        //2、加载 加载配置文件，扫描相关类，将他们封装成BeanDefinition
        List<QJBeanDefiniton> beanDefinitions = reader.loadBeanDefinitions();
        //3、注册 将配置信息注册到ioc容器中
        doRegisterBeanDefinition(beanDefinitions);
        //4、把不是延迟加载的类 提前初始化
        doAutowrited();
    }

    private void doRegisterBeanDefinition(List<QJBeanDefiniton> beanDefinitions) throws Exception{
        for(QJBeanDefiniton beanDefinition : beanDefinitions){
            if(super.beanDefinitionMap.containsKey(beanDefinition.getBeanClassName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
        }
    }


    private void doAutowrited() {
        for(Map.Entry<String, QJBeanDefiniton> entrySet:super.beanDefinitionMap.entrySet()){
            String className = entrySet.getKey();
            if(!entrySet.getValue().isLazyInit()){
                getBean(className);
            }
        }
    }

    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础）
    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
