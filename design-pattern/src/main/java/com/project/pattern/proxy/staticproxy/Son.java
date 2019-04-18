package com.project.pattern.proxy.staticproxy;

import com.project.pattern.proxy.Person;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 1:50 PM 2019/4/18
 **/
public class Son implements Person {
    @Override
    public void findLove(){
        System.out.println("儿子要求");
    }
}
