package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.entity.ShoppingCart;
import com.suo.pagetest.mapper.ShoppingCartMapper;
import com.suo.pagetest.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
