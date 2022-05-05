package com.dubbo.controller;

import com.dubbo.commons.file.FtpUtil;
import com.dubbo.pojo.Message;
import com.dubbo.pojo.Product;
import com.dubbo.productadd.ProductService;
import com.dubbo.send.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//将当前类交给spring容器管理，并实现Controller接口
@Controller
public class ProductController {

    //注入本地服务接口对象
    @Autowired
    private ProductService productService;

    //注入消息发送者对象Sender
    @Autowired
    private Sender sender;

    /*
    图片上传
     */
    @RequestMapping("/uploadFile")
    //该注解表示当前方法返回值是数据，而不是页面跳转的uri
    @ResponseBody
    //MultipartFile mf：接收的是文件，注意：变量名必须上传文件时的那个变量名一样
    public Map<String,Object> uploadPicture(MultipartFile mf){

        //该map集合用来封装文件上传结果，并将该结果响应到网页的kindEditer（富文本编辑器）中
        Map<String,Object> map = new HashMap<>();
        //对上传的文件的名称做唯一化处理，防止上传到服务器中的文件同名而造成文件覆盖
        String originalFilename = mf.getOriginalFilename();//获取原文件名
        int index = originalFilename.lastIndexOf(".");
        String pre = originalFilename.substring(0, index);//文件名前缀
        String suf = originalFilename.substring(index);//文件名后缀
        UUID uuid = UUID.randomUUID();//通用唯一标识符
        String newName = pre + uuid + suf;//新文件名
        InputStream in = null;
        boolean b = false;
        try {
            in = mf.getInputStream();
            //使用FtpUtil工具类上传文件到ftp文件服务器
            b = FtpUtil.uploadFile("192.168.175.129", 21, "ftpuser", "ftpuser", "/home/ftpuser", "/images", newName, in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //上传成功
        if (b){
            map.put("error",0);
            map.put("url","http://192.168.175.129:9996/"+newName);
        }else {
            //上传失败
            map.put("error", 1);
            map.put("message", "图片上传失败");
        }
        return map;
    }

    /*
    新增商品
     */
    //value = "/addProduct"：访问该方法的uri，
    //produces = "text/html;charset=utf-8"：设置响应编码
    @RequestMapping(value = "/addProduct",produces = "text/html;charset=utf-8")
    //该注解表示当前方法返回值是数据，而不是页面跳转的uri
    @ResponseBody
    public String addProduct(Product product){
        //从detail中获取图片路径，并将该路径赋值给imagepath
        String detail = product.getDetail();
        int index = detail.indexOf("src=");
        int index1 = detail.indexOf("alt=");
        String imaPath = detail.substring(index + 5, index1 - 2);
        product.setImagepath(imaPath);
        //将数据插入数据库product表中
        int n = productService.addProduct(product);
        if (n==1){
            /*上架成功，将上架成功的消息发送给索引库和redis，同步更新索引库和redis中的信息
            将添加成功的商品信息发送到队列中，由索引库和redis去获取该信息*/
            //查询上架成功后的商品的id
            int pid = productService.selectByImaPath(imaPath);
            //将查询到的该商品在上架后的pid再放入product中
            product.setPid(pid);
            System.out.println(product);
            //创建对象封装要发送的消息
            Message msg = new Message();
            msg.setObject(product);
            msg.setMsg("商品上架成功");
            //执行消息发送（指定交换机名称，设置路由键，传入消息）
            sender.sendMsg(msg);
            return "商品上架成功";
        }
        return "商品新增失败";
    }

    /*
    下架商品
     */
    @RequestMapping("/downPro/{pid}")
    @ResponseBody
    public String downPro(@PathVariable Integer pid){
        //获取到下架商品的pid，然后根据将对应pid的status从0改为1
        int n = productService.downProduct(1, pid);
        //下架成功，将消息发送给es索引库，通知es索引库更新数据
        if (n==1){
            //创建Message对象，封装要发送的信息
            Message msg = new Message();
            //将要下架的商品的pid放入product对象中，以便可以获取该pid，然后根据pid删除对应的数据
            Product product = new Product();
            product.setPid(pid);
            //将要发送信息放入Message对象中
            msg.setMsg("商品下架成功");
            msg.setObject(product);
            //调用消息发送者，执行消息发送
            sender.sendMsg(msg);

            return "商品下架成功";
        }
        return "商品下架失败";
    }

    /*
    上架商品
     */
    @RequestMapping("/upPro/{pid}")
    @ResponseBody
    public String upPro(@PathVariable Integer pid){
        //获取到下架商品的pid，然后根据将对应pid的status从1改为0
        int n = productService.upProduct(0, pid);
        //下架成功，将消息发送给es索引库，通知es索引库更新数据
        if (n==1){
            //利用该pid从数据库中查出该product（es需要把该数据添加到索引库中）
            Product product = productService.getDataByPid(pid);
            Message msg = new Message();
            msg.setObject(product);
            msg.setMsg("商品上架成功");
            sender.sendMsg(msg);
            return "商品上架成功";
        }
        return "商品上架失败";
    }
}
