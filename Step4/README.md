## Step 4 注入属性和依赖对象

前面的BeanFactory 不能给Bean 注入属性，因此Step 4用于给Bean 注册属性和依赖对象。

实现的目标有2个：

*   Bean 注册时不仅要注册类，还需要注册属性值，因此需要修改BeanDefinition 类，新建PropertyValue 和PropertyValues（包装类）用于保存属性值，由于除了基础属性外，还可能会依赖其他的类，因此新建一个BeanReference 类用于其他类的引用。
*   在createBean 的操作中，新建了对象之后，再给对象注入属性和依赖对象。

![image](https://bugstack.cn/assets/images/spring/spring-5-02.png)

```java
@Test
public void test_BeanFactory() {
    // 1.初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();  

    // 2. UserDao 注册
    beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));   

    // 3. UserService 设置属性[uId、userDao]
    PropertyValues propertyValues = new PropertyValues();
    propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
    propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));  

    // 4. UserService 注入bean
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
    beanFactory.registerBeanDefinition("userService", beanDefinition);    

    // 5. UserService 获取bean
    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
}
```