package com.suo.pagetest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.common.R;
import com.suo.pagetest.dto.DishDto;
import com.suo.pagetest.dto.SetmealDto;
import com.suo.pagetest.entity.Category;
import com.suo.pagetest.entity.DishFlavor;
import com.suo.pagetest.entity.Setmeal;
import com.suo.pagetest.entity.SetmealDish;
import com.suo.pagetest.service.CategoryService;
import com.suo.pagetest.service.SetmealDishService;
import com.suo.pagetest.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;


    //新增套餐
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息:{}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
    }

    @GetMapping("/page")
    public R<Page> list(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        Page<Setmeal> page1 = setmealService.page(pageInfo, queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo, setmealDtoPage, "records");

        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();

            BeanUtils.copyProperties(item, setmealDto);

            Long categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(list);

        return R.success(setmealDtoPage);
    }

    //删除套餐
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:[]", ids);
        setmealService.removeWithDish(ids);

        return R.success("套餐数据删除成功");

    }

    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable("status") int status, @RequestParam List<Long> ids){

        for (int i=0;i<ids.size();i++) {
            UpdateWrapper<Setmeal> updateWrapper=new UpdateWrapper<>();
            updateWrapper.set("status", status);
            updateWrapper.eq("id", ids.get(i));
            System.out.println(ids.get(i));
            setmealService.update(updateWrapper);
        }
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> list(long categoryId,int status){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Setmeal::getCategoryId,categoryId);
        queryWrapper.eq(Setmeal::getStatus,"1");
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(queryWrapper);
        return R.success(list);
    }

}
