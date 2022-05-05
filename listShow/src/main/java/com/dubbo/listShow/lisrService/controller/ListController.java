package com.dubbo.listShow.lisrService.controller;

import com.dubbo.listShow.lisrService.ListService;
import com.dubbo.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

//将当前类交给spring容器管理，并实现Controller接口
@Controller
public class ListController {
    //自动注入本地接口服务对象
    @Autowired
    private ListService listService;

    //访问该方法的uri
    @RequestMapping("/show")
    public String listShow(Model model){
        List<Product> all = listService.findAll();
        model.addAttribute("products",all);
        return "product_manage";
    }

    //访问该方法的uri
    @RequestMapping("/showAfterUpdateData")
    public String listShowAfterUpdateData(Model model){
        List<Product> all = listService.findAllAfterUpdateData();
        model.addAttribute("products",all);
        return "product_manage";
    }

    /*是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     @CacheEvict (cacheNames = "product",key = "'list'")表示清除redis中指定的key，其名称为：productList::list*/
    @CacheEvict(cacheNames = "product",key = "'list'")
    //restful风格从路径中获取参数，uri中表示参数的部分必须{page}格式
    @RequestMapping("/updateData/{pid}/{group2}")//访问该方法的uri
    //该注解表示当前方法返回值是数据，而不是页面跳转的uri
    @ResponseBody
    public String updateDate(@PathVariable int pid,@PathVariable String group2){
                            //@PathVariable表示从路径中获取的参数
        int n = listService.UpdateData(group2, pid);
        return "清除redis缓存成功";
    }
}
