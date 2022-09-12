package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.common.CustomException;
import com.suo.pagetest.common.R;
import com.suo.pagetest.entity.Category;
import com.suo.pagetest.entity.Dish;
import com.suo.pagetest.entity.Setmeal;
import com.suo.pagetest.mapper.CategoryMapper;
import com.suo.pagetest.service.CategoryService;
import com.suo.pagetest.service.DishService;
import com.suo.pagetest.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author 86131
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;

    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(queryWrapper);
        //关联了数据，抛出异常
        if (count>0){
            throw new CustomException("当前分类关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> queryWrapper1=new LambdaQueryWrapper<>();
        queryWrapper1.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(queryWrapper1);
        //关联了套餐数据，抛出异常
        if(count1>0){
            throw new CustomException("当前分类关联了套餐，不能删除");
        }
        //正常删除
        super.removeById(id);
    }




}
