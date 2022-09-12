package com.suo.pagetest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suo.pagetest.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
