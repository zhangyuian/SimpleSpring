## Step 2 实现 Bean 的定义、注册、获取

*   BeanDefinition：将Object bean替换为Class，这样子就可以将Bean 的实例化操作放到容器中处理了，而不需要先初始化Bean 在将Bean 传递给BeanDefinition 了
*   SingletonBeanRegistry：单例注册接口，DefaultSingletonBeanRegistry 是该接口的默认实现方法，其中有一个Map\<String, Object>，用于存放被实例化的Bean 对象（单例对象）
*   AbstractBeanFactory：实现了接口BeanFactory，并继承了DefaultSingletonBeanRegistry ，用于获取Bean，注册Bean，定义了创建Bean 、以及获取BeanDefinition 两个模板方法
*   AbstractAutowireCapableBeanFactory：继承AbstractBeanFactory，实现了Bean 对象的创建
*   DefaultListableBeanFactory：Spring的核心类，继承AbstractAutowireCapableBeanFactory，并实现了BeanDefinitionRegistry 接口，因此其具备Bean创建、单例的注册和获取的能力，其定义了一个 Map\<String, BeanDefinition> 用于存放已经注册的BeanDefinition ，并实现了BeanDefinition的注册和获取方法（接口定义了注册，抽象类定义了获取）

    ![image](https://bugstack.cn/assets/images/spring/spring-3-02.png)

- 不得不说，Spring Bean 容器类设计的很有层次感，每一个类都专注于某一种特定功能，后面再通过继承与实现将功能集成在一起。接口用于定义基础功能，抽象类用于扩展定义以及集成功能