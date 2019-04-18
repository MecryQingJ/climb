package com.project.pattern.proxy.dynamicProxy.jdlProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:38 PM 2019/4/18
 **/
public class JDKMeiPo implements InvocationHandler {

    private Object target;
    public JDKMeiPo(Object source){
        this.target=source;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.target, args);
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
