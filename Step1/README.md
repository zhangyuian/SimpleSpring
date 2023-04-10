## Step 1 创建简单的 Bean 容器

两个关键的类：

*   BeanDefinition：用于定义Bean，其中有一个 Object 用于存放 Bean 对象
*   BeanFactory：Bean工厂，用于注册和获取Bean。其带有一个Map\<String, BeanDefinition> 保存已经被注册的Bean

```java
@Test
public void test_BeanFactory(){
    // 1.初始化 BeanFactory
    BeanFactory beanFactory = new BeanFactory();
    
    // 2.注册 bean
    BeanDefinition beanDefinition = new BeanDefinition(new UserService());
    beanFactory.registerBeanDefinition("userService", beanDefinition);
    
    // 3.获取 bean
    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
}
 
```