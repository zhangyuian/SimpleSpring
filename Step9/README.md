## Step 5 资源加载器解析文件注册对象

```java
@Test
public void test_xml() throws BeansException {
    // 1. 初始化BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2. 读取配置文件 & 注册 Bean
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    reader.loadBeanDefinitions("classpath:spring.xml");

    // 3. 获取Bean对象调用方法
    UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
    String result = userService.queryUserInfo();
    System.out.println("测试结果：" + result);
}
```

这里需要实现的是第二步，读取配置文件，将配置文件中的 Bean 注册到 BeanFactory 中。

需要实现两个大的部分：

*   资源加载的 Resource 接口
*   将资源注册到 BeanFactory 的 BeanDefinitionReader 接口

各类的关系如下图所示：

![image](https://bugstack.cn/assets/images/spring/spring-6-03.png)

*   Resource 接口有三种实现方法，分别是从 classpath、file、url 获取到配置文件
*   ResourceLoader 用来装载并获取到 Resource 资源
*   BeanDefinitionReader 用于加载 Bean 配置并将 Bean 注册到 BeanFactory 中，其依赖ResourceLoader 来获取配置文件内容，依赖BeanDefinitionRegistry 实现 Bean 的注册。XmlBeanDefinitionReader实现了解析xml配置文件的能力，将xml中的Bean注册到Bean工厂中