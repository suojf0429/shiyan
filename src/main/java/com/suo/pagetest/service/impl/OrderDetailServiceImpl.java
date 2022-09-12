package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.entity.OrderDetail;
import com.suo.pagetest.mapper.OrderDetailMapper;
import com.suo.pagetest.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}