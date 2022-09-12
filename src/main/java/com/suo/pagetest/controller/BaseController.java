package com.suo.pagetest.controller;

import com.suo.pagetest.service.ex.ServiceException;
import com.suo.pagetest.service.ex.UsernameDupliate;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86131
 */
public class BaseController {

    public static  final  int OK = 200;

    @ExceptionHandler(ServiceException.class)  //用于统一处理抛出的异常(接受的是：ServiceException.class 中的)，这个方法的返回值就是需要传递给前端的数据
    public Map<String,Object> handleException(Throwable e){
        //自动将异常对象传递给此方法的参数列表上
        Map<String,Object> map=new HashMap<>();
        if (e instanceof UsernameDupliate){
            map.put("4000","用户名已经被注册");
        }
        return map;
    }
}
