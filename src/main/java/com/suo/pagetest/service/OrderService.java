package com.suo.pagetest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suo.pagetest.entity.Orders;

public interface OrderService extends IService<Orders> {

    public void submit(Orders orders);
}
