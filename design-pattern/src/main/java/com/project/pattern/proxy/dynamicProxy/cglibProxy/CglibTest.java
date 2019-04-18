package com.project.pattern.proxy.dynamicProxy.cglibProxy;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 3:49 PM 2019/4/18
 **/
public class CglibTest {
    public static void main(String[] args) {
        Customer instance = (Customer) new CglibMeiPo().getInstance(Customer.class);
        instance.findLove();
    }
}
