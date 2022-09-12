package com.suo.pagetest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suo.pagetest.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
