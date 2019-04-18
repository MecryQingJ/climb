package com.project.pattern.proxy.dbroute;

import com.project.pattern.proxy.dbroute.proxy.OrderServiceStaticProxy;

import java.util.Date;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:22 PM 2019/4/18
 **/
public class DbRouteProxyTest {
    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date().getTime());

        IOrderService iOrderService = new OrderServiceStaticProxy(new OrderService());
        iOrderService.createOrder(order);
    }
}
