package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.entity.DishFlavor;
import com.suo.pagetest.mapper.DishFlavorMapper;
import com.suo.pagetest.service.DishFlavorService;
import com.suo.pagetest.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
