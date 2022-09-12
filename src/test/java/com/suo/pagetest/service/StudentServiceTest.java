package com.suo.pagetest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void Test(){
        Student s=new Student();
        s.setUsername("1");
        s.setSn("");
        IPage<Student> student = studentService.getStudent(2,2,s);
        System.out.println(student);

    }
}
