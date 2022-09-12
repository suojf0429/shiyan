package com.suo.pagetest.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
    @Autowired(required = false)
    private StudentMapper studentMapper;


    @Test
    public void Test(){
        Page<Student> page=new Page<>(2,2);
        Student s=new Student();
        s.setUsername("1");
        s.setSn("");
        IPage<Student> student = studentMapper.getStudent(page,s);
        System.err.println(student);
    }

























}
