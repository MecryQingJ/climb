package com.project.pattern.proxy.dynamicProxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 3:43 PM 2019/4/18
 **/
public class CglibMeiPo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        //相当于Proxy
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o,objects);
        after();
        return result;
    }
    public void before(){
        System.out.println("物色条件");
    }
    public void after(){
        System.out.println("完成要求");
    }

}
