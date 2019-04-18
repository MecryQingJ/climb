package com.project.pattern.proxy.dbroute.proxy;

import com.project.pattern.proxy.dbroute.IOrderService;
import com.project.pattern.proxy.dbroute.Order;
import com.project.pattern.proxy.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 2:16 PM 2019/4/18
 **/
public class OrderServiceStaticProxy implements IOrderService {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    //传入被代理对象
    private IOrderService iOrderService;
    public OrderServiceStaticProxy(IOrderService iOrderService){
        this.iOrderService = iOrderService;
    }

    @Override
    public int createOrder(Order order) {
        Long createTime = order.getCreateTime();
        Integer dbRoute = Integer.valueOf(yearFormat.format(new Date(createTime)));
        System.out.println("静态代理类自动分配到【DB_"+dbRoute+"】数据源处理");
        DynamicDataSourceEntity.set(dbRoute);
        this.iOrderService.createOrder(order);
        DynamicDataSourceEntity.restore();
        return 0;
    }
}
