package com.suo.pagetest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.entity.Student;
import com.suo.pagetest.mapper.StudentMapper;
import com.suo.pagetest.service.StudentService;
import com.suo.pagetest.service.ex.UsernameDupliate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86131
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired(required = false)
    private StudentMapper studentMapper;
    @Override
    public IPage<Student> getStudent(Integer page, Integer rows, Student student) {
        Page<Student> studentPage=new Page<>(page,rows);  //Page用于定义每页的规格
                                                            //IPage以规格和其他内容为参数，将记录进行分页
        IPage<Student> list = (IPage<Student>) studentMapper.getStudent(studentPage,student);
        return list;
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> studentList = studentMapper.getStudentList();
        return studentList;
    }

    @Override
    public int insertStudent(Student student) {
        List<Student> studentByName = studentMapper.getStudentByName(student.getUsername());
        if (studentByName.size()!=0){
            throw new UsernameDupliate();
        }
        return 1;
    }
}
