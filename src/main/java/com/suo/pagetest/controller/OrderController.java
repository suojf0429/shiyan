package com.suo.pagetest.controller;

import com.suo.pagetest.common.R;
import com.suo.pagetest.entity.OrderDetail;
import com.suo.pagetest.entity.Orders;
import com.suo.pagetest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);

        return R.success("下单成功");
    }

}
