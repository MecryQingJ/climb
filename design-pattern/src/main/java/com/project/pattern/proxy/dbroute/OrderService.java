package com.project.pattern.proxy.dbroute;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:03 PM 2019/4/18
 **/
public class OrderService implements IOrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService创建订单");
        return orderDao.createOrder(order);
    }
}
