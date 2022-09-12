package com.suo.pagetest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.pagetest.common.R;
import com.suo.pagetest.entity.Employee;
import com.suo.pagetest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author 86131
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;




    @PostMapping("/login")   //界面F12知道传过来的用户名和密码是以Json格式传递过来的{username: "admin", password: "123456"}
                                //所以需要注解 @RequestBody
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee,HttpSession session){
        String password=employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp=employeeService.getOne(queryWrapper);

        if(emp==null){
            return R.error("登录失败");
        }
        if (!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        if(emp.getStatus()==0){
            return R.error("账户被禁用");
        }
        session.setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpSession session){
        session.removeAttribute("employee");
        return R.success("成功");
    }

    @PostMapping
    public R<String> save(@RequestBody Employee employee,HttpSession session){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());      采用了MP的公共字段填充（MyMetaObjecthandler）
//        employee.setUpdateTime(LocalDateTime.now());
//        Long empId=(Long) session.getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page ,int pageSize ,String name){
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpSession session,@RequestBody Employee employee){
        System.out.println(employee);
        Long empId= (Long) session.getAttribute("employee");
        employee.setUpdateUser(empId);
        employee.setUpdateTime(LocalDateTime.now());

        employeeService.updateById(employee);
        return  R.success("员工信息修改成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }



}
