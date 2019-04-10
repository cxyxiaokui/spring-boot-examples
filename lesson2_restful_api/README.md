## SpringBoot 实现RestFul 相关注解的介绍

如果说你会使用SpringMVC 那么下面的内容你阅读将会很轻松，我这里通过搭建一个用户管理 接口API 让你快速学会如何创建restful 风格的API 。我们没有对接数据库相关数据在代码中都是写死的。示例程序SpringBoot版本是**2.1.0.RELEASE** 对应SpringMVC版本是 **5.1.2RELEASE** 。

### 创建 restful 风格的API 的 需要使用到的注解

1 在我们接口Controller上声明 **@RestController()** 表明该类 RestFul 风格的Controller。

2 **@GetMapping()** 用于生命在 Get方式访问的接口 该方式主要用于数据查询。

3 **@PostMapping()** 用于声明在 Post方式访问的接口 该方式主要用于数据新增。

4 **@PutMapping()** 用于声明在 Put方式访问的接口 该方式主要用于全部更新。

5 **@PatchMapping()** 用于声明在 Patch方式访问的接口 该方式主要用于局部更新。

6 **@DeleteMapping()** 用于声明在 Delete 方式访问的接口 该方式主要用于数据删除。

上面这些注解在Spirng4.0 中也同样适用，但是在SpringMVC 3.0 中我们需要通过@Controller 和 @ResponseBody 来达到 @RestController()的效果。

对于@GetMapping() 和 @PostMapping() 等是通过  @RequestMapping()的method 属性来实现的例如： @RequestMapping(value = "",method = RequestMethod.POST)。



### put 方式接受不到参数问题解决

在进行开发是遇到啦问题就是把修改时参数接受不到然后百度查询需要配置HttpPutFormContentFilter才可以。但是springBoot没有xml所以我们需要通过注解的方式来配置HttpPutFormContentFilter过滤器。

```java

/**
 * 解决restFul put 参数无法接收的问题
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {
}
```

参考文献：HTTPS：//www.aliyun.com/jiaocheng/852091.html

## 示例程序代码介绍

### 用户Model的代码

下面是我们用户的Model类 用户类里面包含用户名称name和用户年龄年龄成员变量。

```java
package cn.lijunkui.springbootlearn.restful.model;

public class User {
    private String name;
    private Integer age;
    //省略get and set 方法
}
```

### 用户Controller类声明RestController

```java
@RestController()
@RequestMapping("/bootUser")
public class UserController {

}
```

### 查询用户接口
```java

@GetMapping("/{id}")
public User get(@PathVariable(name = "id") Long id){
	User user = new User();
	user.setName("lijunkui");
	user.setAge(18);
	log.info("springboot查询用户成功："+"id:{}",id);
	return user;
}
```

### 添加用户接口
```java

@PostMapping()
    public void add(User user){

​	log.info("springboot添加用户成功："+"name:{},age:{}",user.getName(),user.getAge());
​    }
```

### 全部修改用户接口

@PutMapping()
    public void updatePut(User user){

​	log.info("springboot Put 修改用户成功："+"name:{},age:{}",user.getName(),user.getAge());
​    }

### 局部修改用户接口
```java

@PatchMapping("/{name}")
public void updatePatch(@PathVariable("name") String name){
	log.info("springboot Patch 修改用户成功："+"name:{}",name);
}
```

### 删除用户的接口

```java
@DeleteMapping("/{id}")
public void delete(@PathVariable("id") Long id){
	User user = new User();
	user.setName("lijunkui");
	user.setAge(18);
	log.info("springboot 删除用户成功："+"id:{}",id);
}
```

## 用户管理API测试

### 查询测试

![img](https://img-blog.csdn.net/20180916151815887?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![img](https://img-blog.csdn.net/20180916151841126?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 添加测试

![img](https://img-blog.csdn.net/20180916151928799?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![img](https://img-blog.csdn.net/20180916151943587?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 全局修改测试

![img](https://img-blog.csdn.net/20180916152206866?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)



![img](https://img-blog.csdn.net/2018091615224414?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 局部修改测试

![img](https://img-blog.csdn.net/20180916152321303?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![img](https://img-blog.csdn.net/20180916152343660?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 删除测试

![img](https://img-blog.csdn.net/20180916152403830?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![img](https://img-blog.csdn.net/20180916152414144?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqazEyNnd5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)


