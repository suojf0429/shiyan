package com.suo.pagetest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suo.pagetest.entity.Student;
import com.suo.pagetest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86131
 */
@RestController

@RequestMapping("/student")
public class StudentController extends BaseController{
    @Autowired
    private StudentService studentService;
    Map<String, Object> map = new HashMap<>();
    @RequestMapping("/query")
    public Map<String, Object> queryList(Integer page, Integer rows,String username,String sn) {
        Student s = new Student();
        s.setUsername(username);
        s.setSn(sn);
        IPage<Student> studentList = studentService.getStudent(page,rows,s);

        map.put("studentList",studentList);
        return map;

    }
    @RequestMapping("/queryList")
    public List<Student> queryList(){
        List<Student> studentList = studentService.getStudentList();
        return studentList;
    }

    @RequestMapping("/regist")
    public Map<String,Object> regist(String username ){
        Student s=new Student();
        s.setUsername(username);
        studentService.insertStudent(s);
        map.put("OK",OK);
        return map;

    }



}
