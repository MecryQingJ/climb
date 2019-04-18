package com.project.pattern.proxy.staticproxy;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 1:55 PM 2019/4/18
 **/
public class StaticProxyTest {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();

    }
}
