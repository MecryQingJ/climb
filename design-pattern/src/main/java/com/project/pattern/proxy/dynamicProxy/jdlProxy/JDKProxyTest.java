package com.project.pattern.proxy.dynamicProxy.jdlProxy;

import com.project.pattern.proxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:45 PM 2019/4/18
 **/
public class JDKProxyTest {
    public static void main(String[] args)  {
        //被代理对象
        Gril gril = new Gril();
        JDKMeiPo jdkMeiPo = new JDKMeiPo(gril);
        Class<? extends Gril> clazz = gril.getClass();
        Person person = (Person) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(), jdkMeiPo);
        person.findLove();

        try {
            byte[] bytes = ProxyGenerator.generateProxyClass("$ProxyTest", new Class[]{Person.class});
            FileOutputStream outputStream = new FileOutputStream("design-pattern/$ProxyTest.class");
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
