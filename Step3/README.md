## Step 3 基于Cglib实现含构造函数的类的实例化策略

Step 2 中只是简单地将Bean 的实例化操作交给了容器，没有考虑构造器，因此在Bean 含有自定义的构造器时会报错。

这里的设计需要考虑两个问题：

1.  从哪里将构造函数的入参信息传递到实例化操作里
2.  怎么去实例化含有构造函数的对象

第一个问题在BeanFactory 中新建Object getBean(String name, Object.. arg) 接口，这样子可以在获取Bean 时把构造函数的入参信息传递进去了

第二个问题通过Cglib / Java反射来解决

*   新建实例化策略接口 InstantiationStrategy，两个实例化策略：

    *   SimpleInstantiationStrategy：通过JDK自带的反射解决
    *   CglibSubclassingInstantiationStrategy：通过Cglib进行实例化

这里改写AbstractAutowireCapableBeanFactory，调用InstantiationStrategy ，new 上面两个实例化策略中的一个就好了

![image](https://bugstack.cn/assets/images/spring/spring-4-02.png)

```java
@Test
public void test_BeanFactory() throws BeansException {
    // 1. 初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2. 注入 bean
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
    beanFactory.registerBeanDefinition("userService", beanDefinition);

    // 3. 获取bean
    UserService userService = (UserService) beanFactory.getBean("userService", "张宇"); // 传入构造函数的入参
    userService.queryUserInfo();
}
```