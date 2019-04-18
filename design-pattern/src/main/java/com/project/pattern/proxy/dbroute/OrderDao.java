package com.project.pattern.proxy.dbroute;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:02 PM 2019/4/18
 **/
public  class OrderDao implements IOrderService {
    @Override
    public int createOrder(Order order){
        System.out.println("OrderDao 插入 order 订单");
        return 1;
    }
}
