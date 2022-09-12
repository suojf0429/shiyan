package com.suo.pagetest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.entity.Student;


import java.util.List;

/**
 * @author 86131
 */
public interface StudentService {

    IPage<Student> getStudent(Integer page, Integer rows, Student student);

    List<Student> getStudentList();

    int insertStudent(Student student);
}
