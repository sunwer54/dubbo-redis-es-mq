package com.dubbo.listShow.lisrService.impl;

/**
 * 服务消费者，dubbo订阅zookeeper注册中心中的远程服务
 */
import com.dubbo.api.ProductApi;
import com.dubbo.listShow.lisrService.ListService;
import com.dubbo.pojo.Product;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Cacheable 可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，
 * 当标记在一个类上时则表示该类所有的方法都是支持缓存的。对于一个支持缓存的方法，Spring会在其被调用后将
 * 其返回值在redis中缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从redis缓存中获取结果，而不
 * 需要再次执行该方法。
 * @Cacheable (cacheNames = "product",key = "'list'")表示存入redis中的key的名称：productList::list
 *
 * @CachePut 也可以声明一个方法支持缓存功能。与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去
 * 检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
 * @CachePut (cacheNames = "product",key = "'list'")表示更新redis中指定的key，其名称为：productList::list
 */
@Service //将当前类交给spring容器管理
public class ListServiceImpl implements ListService {
    @Reference //dubbo订阅zookeeper注册中心中的远程服务
    private ProductApi productApi;
    @Override
    @Cacheable(cacheNames = "product",key = "'list'")
    /*该注解表示将当前方法的返回值缓存到redis中。在每次访问都会检查redis中是否存在对应的key,存
    在即从redis中拿出来用（只检查是否有对应的key，而不会检查key中的值是否已经发生改变，也就是
    说，当redis中有该返回值的缓存时，如果手动去数据库中修改了数据，当再执行该查询操作时也不会调
    用该方法，而是直接取出redis中已缓存的数据，这种情况下，数据中的数据和redis中缓存的数据是不一致的）
    @Cacheable (cacheNames = "product",key = "'list'")表示存入redis中的key的名称：productList::listr*/
    public List<Product> findAll() {
        System.out.println("查询操作结果缓存到redis中");
        return productApi.findAll();
    }

    @CachePut(cacheNames = "product",key = "'list'")
    /*也可以写在方法上支持缓存功能，将当前方法的返回值缓存到redis中。与@Cacheable不同的是使用
    @CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，
    并将执行结果以键值对的形式存入指定的缓存中。
    @CachePut(cacheNames = "product",key = "'list'")指定了操作redis中key为：productList::list*/
    @Override
    public List<Product> findAllAfterUpdateData() {
        System.out.println("更新了redis中的缓存");
        return productApi.findAllAfterUpdateData();
    }

    @Override
    public int UpdateData(String group2, int pid) {
        System.out.println("清除了redis缓存");
        return productApi.UpdateData(group2, pid);
    }
}
