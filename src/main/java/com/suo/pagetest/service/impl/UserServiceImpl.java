package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.entity.User;
import com.suo.pagetest.mapper.UserMapper;
import com.suo.pagetest.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
}
