package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.dto.DishDto;
import com.suo.pagetest.entity.Dish;
import com.suo.pagetest.entity.DishFlavor;
import com.suo.pagetest.mapper.DishMapper;
import com.suo.pagetest.service.DishFlavorService;
import com.suo.pagetest.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86131
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto){
        this.save(dishDto);
        Long dishId = dishDto.getId();//菜品id
        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());


        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);



    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息  dish表
        Dish dish = this.getById(id);  //这里的this是对应的那个对象（this.getById(id)  ===  dishservice.getById(id)    ）
        //如果用dishservice.getById(id) 的话，会报错！！！
        System.out.println("*******************"+dish+"***********");
        DishDto dishDto=new DishDto();
        BeanUtils.copyProperties(dish,dishDto); //拷贝  p65

        //查询当前菜品对应的口味信息  dish_flavor表
        LambdaQueryWrapper<DishFlavor> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }


    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);
        //更新口味表基本信息

        //1.（先清理当前菜品对应的口味数据，--dish_flavor的delete操作）
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //2/（添加当前提交过来的口味数据，--dish_flavor的insert操作）
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}
