package com.suo.pagetest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.common.R;
import com.suo.pagetest.entity.Category;
import com.suo.pagetest.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 86131
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("新增成功");
    }
    @GetMapping("page")
    public R<Page> page(int page ,int pageSize){
        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }

    @DeleteMapping

    public R<String> delete(Long ids){
        categoryService.remove(ids);
        return R.success("分类信息删除成功");
    }

    @PutMapping    //以Json形式传递过来的 要加上@RequestBody
    public R<String> update1(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null ,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);

    }

}
