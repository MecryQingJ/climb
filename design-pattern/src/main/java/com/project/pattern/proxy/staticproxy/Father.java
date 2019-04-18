package com.project.pattern.proxy.staticproxy;

import com.project.pattern.proxy.Person;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 1:52 PM 2019/4/18
 **/
public class Father {

    private Person person;
    public Father(Son son){
        this.person = son;
    }

    public void findLove(){
        System.out.println("父亲物色对象");
        this.person.findLove();
        System.out.println("双方见面");
    }
}
