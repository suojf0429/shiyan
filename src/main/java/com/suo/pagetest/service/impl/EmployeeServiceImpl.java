package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.pagetest.entity.Employee;
import com.suo.pagetest.mapper.EmployeeMapper;
import com.suo.pagetest.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author 86131
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}

