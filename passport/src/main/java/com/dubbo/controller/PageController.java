package com.dubbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//将当前类交给spring容器管理，并实现Controller接口
public class PageController {
    //restful风格从路径中获取参数，uri中表示参数的部分必须{page}格式
    @RequestMapping("/{page}")
    public String pageShow(@PathVariable String page){//@PathVariable表示从路径中获取的参数
        return page;
    }
}
