package com.redisoperate.redisoperate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

//这个注解是基于springboot测试，需要依赖springboot启动类
@SpringBootTest(classes = RedisOperateApplication.class)
@RunWith(SpringRunner.class)
class RedisOperateApplicationTests {
    @Autowired//注入Java中用来操作redis的类，子类中封装了用来操作redis的方法
    RedisTemplate redisTemplate;

    /**
     * 操作String类型
     */
    @Test
    public void operateRedisString() {
        //添加数据
        redisTemplate.boundValueOps("name").set("丽丽");
        redisTemplate.boundValueOps("age").set(25);
        redisTemplate.boundValueOps("sex").set("male");
        //在redis中看到存入的name和age的key的前面被添加上了前缀，如下：
        //1) "\xac\xed\x00\x05t\x00\x04name"
        //2) "\xac\xed\x00\x05t\x00\x03age"
        //是因为RedisTemplate默认使用JdkSerializationRedisSerializer作为序列化工具,所以
        //多了前缀,但并不影响我们使用
        //序列化:需要通过io流读写数据,如果数据是对象,那么这个对象必须序列化

        //查看数据
        String name = (String)redisTemplate.boundValueOps("name").get();
        Integer age = (Integer) redisTemplate.boundValueOps("age").get();
        String sex = (String) redisTemplate.boundValueOps("sex").get();

        System.out.println(name+"---"+age+"---"+sex);
        //设置key的过期时间
        Boolean name1 = redisTemplate.boundValueOps("sex").expire(1, TimeUnit.MINUTES);//第二个参数是时间单位
        System.out.println(name1);

        //获取过期时间
        Long name2 = redisTemplate.boundValueOps("sex").getExpire();
        System.out.println(name2);

        //删除数据，根据key删除,删除redis中的指定key
        Boolean deleteName = redisTemplate.delete("name");
    }
    /*
    操作List集合
     */
    @Test
    public void operateRedisList(){
        //插入数据并返回数据的条数
        Long size1 = redisTemplate.boundListOps("number").leftPush("20");//从左边插入数据
        System.out.println("size1:"+size1);
        Long size2 = redisTemplate.boundListOps("number").leftPush("33");
        System.out.println("size2:"+size2);

        Long size3 = redisTemplate.boundListOps("number").rightPush("55");//从右边插入数据
        System.out.println("size3:"+size3);
        Long size4 = redisTemplate.boundListOps("number").rightPush("77");//从右边插入数据
        System.out.println("size3:"+size4);

        //遍历value， -1表示从最右边数据的，-2表示右边倒数第二条数据，......
        List numbers = redisTemplate.boundListOps("number").range(0, -1);//遍历指定key的value
        System.out.println("number:"+numbers);

        //查看指定位置的数据
        String number2 = (String)redisTemplate.boundListOps("number").index(2);//查看指定位置的数据
        System.out.println("index2:"+number2);

        //查看指定数据的索引
        /*Long indexOfahaha = redisTemplate.boundListOps("number").indexOf("ahaha");//查看指定数据的索引
        System.out.println(indexOfahaha);*/

        //修改指定位置的数据
        redisTemplate.boundListOps("number").set(2,"10");//修改指定位置的数据

        //移除元素并返回该元素
        String rightPop = (String)redisTemplate.boundListOps("number").rightPop();//移除最右边的元素
        String leftPop = (String)redisTemplate.boundListOps("number").leftPop();//移除最左边的元素
        System.out.println(rightPop+"---"+leftPop);
        System.out.println(redisTemplate.boundListOps("number").range(0,-1));

        //移除指定个数的value，并返回移除的个数
        Long remove = redisTemplate.boundListOps("number").remove(20, "suiian");
        System.out.println(remove+"--"+redisTemplate.boundListOps("number").range(0,-1));

        //直接给指定的键插入List集合，作为value为list中的一条数据，而不是多条数据
        List list = new ArrayList();
        list.add("上海");
        list.add("北京");
        list.add("深圳");
        //一次往list中插入多条数据，并返回list中数据条数
        Long sizec = redisTemplate.boundListOps("city").leftPushAll(list,"成都","杭州","武汉");
        System.out.println(sizec);

        //直接给指定键插入数组，数组中有多少条数据，则插入后的value的List中就会有多少条数据
        Integer[] ids = {5,6,10,25,10,22};
        Long goodList1 = redisTemplate.boundListOps("goodList").leftPushAll(ids);
        System.out.println(goodList1);
        Integer goodList = (Integer)redisTemplate.boundListOps("goodList").rightPop();
        System.out.println(goodList);

        //遍历value， -1表示从最右边数据的，-2表示右边倒数第二条数据，......
        List city = redisTemplate.boundListOps("city").range(0, -1);
        System.out.println(city);

        //移除元素并返回该元素
        String cityRightPop = (String)redisTemplate.boundListOps("city").rightPop();//移除最右边的元素
        Object cityLeftPop = redisTemplate.boundListOps("city").leftPop();//移除最左边的元素
        System.out.println(cityRightPop+"---"+cityLeftPop);
        System.out.println(redisTemplate.boundListOps("city").range(0,-1));

        //删除redis中指定的key
        Boolean number1 = redisTemplate.delete("number");

    }
    /**
     * 操作hash类型
     */
    @Test
    public void operateRedisHash() {
        //插入数据
        redisTemplate.boundHashOps("animal").put("rabbit","吃草");
        redisTemplate.boundHashOps("animal").put("bird","吃虫子");
        redisTemplate.boundHashOps("animal").put("dog","啥都吃，不挑食");
        redisTemplate.boundHashOps("animal").put("panda","吃竹子");

        //查看数据
        List animals = redisTemplate.boundHashOps("animal").values();//获取指定key的map的全部values
        System.out.println(animals);

        //先创建map再将map插入到redis的键的value中，直接插入key的map中是多条数据
        Map m =new HashMap();
        m.put("rabbit0","爱吃草");
        m.put("bird0","爱吃虫子");
        m.put("dog0","爱吃肉");
        m.put("panda0","爱好竹子");
        redisTemplate.boundHashOps("animal").putAll(m);//一次性直接插入map集合
        System.out.println(redisTemplate.boundHashOps("animal").entries());//获取指定key的map的全部field和value
        System.out.println(redisTemplate.boundHashOps("animal").values());//获取指定key的map的全部value
        Long delete = redisTemplate.boundHashOps("animal").delete("panda","panda0");
        System.out.println(delete);

    }

    /**
     * 操作set类型
     */
    @Test
    public void operateRedisSet() {
        redisTemplate.boundSetOps("food").add("米饭");//插入数据
        redisTemplate.boundSetOps("food").add("肌肉");
        redisTemplate.boundSetOps("food").add("鸭肉");
        redisTemplate.boundSetOps("food").add("鱼肉","羊肉","牛肉","毛肚");//一次性插入多个数据
        //查看数据
        Set foods = redisTemplate.boundSetOps("food").members();//获取指定key的全部value
        System.out.println(foods);
        String food1 = (String)redisTemplate.boundSetOps("food").pop();//移除最右边并返回被移除的数据
        System.out.println(food1);
        System.out.println(redisTemplate.boundSetOps("food").members());//获取指定key的全部value
        Long remove = redisTemplate.boundSetOps("food").remove("肌肉");//移除指定的value，并返回移除的个数
        System.out.println(redisTemplate.boundSetOps("food").members());//获取指定key的全部value
    }
    /**
     * 操作有序set类型
     */
    @Test
    public void operateRedisZSet() {
        redisTemplate.boundZSetOps("weather").add("阴天",2);//插入数据，并标记
        redisTemplate.boundZSetOps("weather").add("晴天",10);
        redisTemplate.boundZSetOps("weather").add("多云",12);
        redisTemplate.boundZSetOps("weather").add("雨天",17);
        Set weather = redisTemplate.boundZSetOps("weather").range(0, -1);//遍历values
        System.out.println(weather);
        Set weather1 = redisTemplate.boundZSetOps("weather").rangeByScoreWithScores(0, 25);//根据标记遍历values和对应的标记
        System.out.println(weather1);
    }

}
