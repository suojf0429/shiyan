package com.suo.pagetest.dto;

import com.suo.pagetest.entity.Dish;
import com.suo.pagetest.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
