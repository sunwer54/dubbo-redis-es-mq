package com.dubbo.controller;

import com.dubbo.passport.UserService;
import com.dubbo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;

//将当前类交给spring容器管理，并实现Controller接口
@Controller
public class UserController {
    //自动注入本地服务接口对象
    @Autowired
    private UserService userService;

    //value = "/loginUser"：访问该方法的uri
    //produces = "text/html;charset=utf-8"：设置响应编码
    @RequestMapping(value = "/loginUser",produces = "text/html;charset=utf-8")
    public ModelAndView loginUser(String userName, String pwd, HttpSession session){
        User user = userService.loginUser(userName, pwd);
        if (user!=null){
            //登录成功，跳转到指定页面
            /*将登录者信息存入HttpSession中，SpringSession会自动接管这个HttpSession管理，
              把这个HttpSession自动往redis中写入。*/
            session.setAttribute("user",user);
            //ModelAndView实现跨域（跨服务器）访问："http://localhost:9092/product"
            return new ModelAndView(new RedirectView("http://localhost:9092/product"));
        }else {
            //登录失败，再跳回登录页面
            ModelAndView mv = new ModelAndView();
            mv.addObject("fail","用户名或密码错误");//设置响应信息
            mv.setViewName("login");//跳转到登录页面
            return mv;
        }
    }
}
