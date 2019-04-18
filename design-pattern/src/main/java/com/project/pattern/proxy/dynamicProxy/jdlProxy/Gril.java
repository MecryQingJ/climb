package com.project.pattern.proxy.dynamicProxy.jdlProxy;

import com.project.pattern.proxy.Person;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:35 PM 2019/4/18
 **/
public class Gril  implements Person {
    @Override
    public void findLove() {
        System.out.println("妹子要求");
    }
}
