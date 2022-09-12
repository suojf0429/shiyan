package com.suo.pagetest.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86131
 */
public interface StudentMapper {

    IPage<Student> getStudent(Page<Student> page, @Param("student") Student student);

    List<Student> getStudentList();

    List<Student> getStudentByName(String username);

}
