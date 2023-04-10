package cn.bugstack.srpingframework.test;

import cn.bugstack.springframework.BeanDefinition;
import cn.bugstack.springframework.BeanFactory;
import cn.bugstack.srpingframework.test.bean.UserService;
import org.junit.Test;


public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1. initialize bean factory
        BeanFactory beanFactory = new BeanFactory();

        // 2. 定义BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        // 3. 注册bean
        beanFactory.reigisterBeanDefinition("Test", beanDefinition);
        UserService testUserService = (UserService) beanFactory.getBean("Test");

        testUserService.queryUserInfo();
    }
}
