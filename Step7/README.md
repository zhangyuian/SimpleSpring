## Step 7 初始化和销毁方法

目标：

*   实现在XML中配置的初始化和销毁方法

方案：

![image](https://bugstack.cn/assets/images/spring/spring-8-03.png)

初始化、销毁的两种调用方法：

*   xml配置：将init-method 和 destroy-method 两个注解配置定义到BeanDefinition 中，这样子就可以在invokeInitMethods 初始化调用中通过反射的方法调用指定的初始化方法了
*   实现接口：如果采用接口实现的方式，直接通过Bean 对象调用对应接口定义的方法即可

注册钩子：

*   在虚拟机中注册hook，在虚拟机关闭的时候会调用Bean 对象的销毁方法

在createBean 中添加初始化方法，初始化Bean： invokeInitMethods 中判断其是否实现接口，init-method是否为空，再依次执行初始化方法即可。

销毁方法则需要增加一个适配器类 DisposableBeanAdapter（接口和配置都集成在该类中）

*   DisposableBeanAdapter 实现销毁接口，重写 destroy 方法，将接口和方法的判断都放在其中。在createBean 的时候注册 DisposableBeanAdapter 对象

在 ConfigurableApplicationContext 接口中新增 close() 方法（即hook中调用的销毁方法）和 registerShutdownHook() 方法，并在AbstractApplicationContext 中实现 close() 方法，并实现 registerShutdownHook() 类暴露给外面注册虚拟机hook()，这样就实现了Bean 的销毁方法

UserDao 只配置初始化、销毁方法，不实现接口

```java
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
```



UserService 只实现初始化、销毁接口，不配置

```java
public class UserService implements InitializingBean, DisposableBean {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    // ...get/set
}
 
```

手动注册虚拟机钩子

```java
@Test
public void test_xml() {
    // 1.初始化 BeanFactory
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    applicationContext.registerShutdownHook();      

    // 2. 获取Bean对象调用方法
    UserService userService = applicationContext.getBean("userService", UserService.class);
    String result = userService.queryUserInfo();
    System.out.println("测试结果：" + result);
}
```

类图如下：

![image](https://bugstack.cn/assets/images/spring/spring-8-04.png)
