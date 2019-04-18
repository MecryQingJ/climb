package com.project.mini.spring.framework.context;

import com.project.mini.spring.framework.annotation.QJAutowired;
import com.project.mini.spring.framework.annotation.QJController;
import com.project.mini.spring.framework.annotation.QJService;
import com.project.mini.spring.framework.beans.QJBeanFactory;
import com.project.mini.spring.framework.beans.config.QJBeanDefinition;
import com.project.mini.spring.framework.beans.config.QJBeanWrapper;
import com.project.mini.spring.framework.beans.support.QJBeanDefinitionReader;
import com.project.mini.spring.framework.beans.support.QJDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: Created in 1:44 PM 2019/4/11
 **/
public class QJApplicationContext extends QJDefaultListableBeanFactory implements QJBeanFactory {

    private String[] configLocations;
    private QJBeanDefinitionReader reader;
    //单例的ioc容器缓存
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<String,Object>();
    //同用的ioc容器
    private Map<String,QJBeanWrapper> factoryBeanInstanceCache =  new ConcurrentHashMap<String, QJBeanWrapper>();

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
        List<QJBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        //3、注册 将配置信息注册到ioc容器中
        doRegisterBeanDefinition(beanDefinitions);
        //4、把不是延迟加载的类 提前初始化
        doAutowrited();
    }

    private void doRegisterBeanDefinition(List<QJBeanDefinition> beanDefinitions) throws Exception{
        for(QJBeanDefinition beanDefinition : beanDefinitions){
            if(super.beanDefinitionMap.containsKey(beanDefinition.getBeanClassName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
        }
    }


    private void doAutowrited() {
        for(Map.Entry<String, QJBeanDefinition> entrySet:super.beanDefinitionMap.entrySet()){
            String className = entrySet.getKey();
            if(!entrySet.getValue().isLazyInit()){
                try {
                    getBean(className);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        //1初始化
        QJBeanWrapper beanWrapper = instantiateBean(beanName, new QJBeanDefinition());

        //拿到BeanWrapper后放到ioc中
//        if(this.factoryBeanInstanceCache.containsKey(beanName)){
//            throw new Exception("The"+ beanName+" is instance");
//        }
        this.factoryBeanInstanceCache.put(beanName,beanWrapper);

        //2注入
        populateBean(beanName, new QJBeanDefinition(),beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private void populateBean(String beanName, QJBeanDefinition beanDefinition, QJBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();
        //判断只有加了注解的类，才执行依赖注入
        if(!(clazz.isAnnotationPresent(QJController.class) || clazz.isAnnotationPresent(QJService.class))){
            return;
        }
        //获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(!field.isAnnotationPresent(QJAutowired.class)){
                continue;
            }
            QJAutowired autowired = field.getAnnotation(QJAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(instance,this.factoryBeanInstanceCache.get(beanName).getWrappedClass());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private QJBeanWrapper instantiateBean(String beanName, QJBeanDefinition beanDefiniton) {
        //1、拿到要实例化的对象的类名
        String className = beanDefiniton.getBeanClassName();
        //2、反射实例化，得到一个对象
        Object instance =null;
        try {
            if(this.singletonObjects.containsKey(className)){
                instance=this.singletonObjects.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance=clazz.newInstance();
                this.singletonObjects.put(className,instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、把这个对象封装到QjBeanWrapper
        QJBeanWrapper beanWrapper = new QJBeanWrapper(instance);
        //4、把beanWrapper存到ioc容器中
        return beanWrapper;
    }
}
