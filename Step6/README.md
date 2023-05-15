## Step 6 实现应用上下文

在Step 5 中，Bean 工厂需要自己创建，配置文件也需要自行读取。Step 6 就要将Bean 工厂的创建以及配置文件的读取都集成起来。如果需要在Bean 的生命周期之中完成对Bean 对象的拓展操作，还需要添加拓展机制。

目标：

*   实现应用上下文：自动完成加载、修改、注册、实例化等操作
*   实现容器扩展机制，可以在Bean 的生命周期内完成一些自定义操作

实现方案：

![image](https://bugstack.cn/assets/images/spring/spring-7-02.png)

拓展机制的类：

*   BeanPostProcessor 用于完成Bean 对象实例化之前和之后的处理
*   BeanFactoryPostProcessor 用于完成对Bean 的定义信息 BeanDefiniton 的修改

上下文接口：

*   ApplicationContext 继承ListableBeanFactory 接口，可以使用BeanFactory 中的getBean 等方法
*   ConfigurableApplicationContext 核心接口，其中定义的核心方法 refresh( ) 可以说是定义了Bean 的整个生命周期
*   AbstractApplicationContext 继承 DefaultResourceLoader 、实现ConfigurableApplicationContext，将资源加载和容器刷新两个功能整合在一起，实现核心的 refresh( ) 方法
*   AbstractRefreshableApplicationContext 继承AbstractApplicationContext，实现获取Bean 工厂和加载资源
*   AbstractXmlApplicationContext 继承AbstractRefreshableApplicationContext，完成资源的加载操作，这里留下一个 抽象类getConfigLocations() 方法，为的是从入口上下文类，拿到配置信息的地址
*   ClassPathXmlApplicationContext 继承AbstractXmlApplicationContext，入口类，具体对外给用户提供的应用上下文方法

完成Refresh() 中对Bean 的定义BeanDefinition 的修改（BeanFactoryPostProcessor），注册BeanPostProcessor，并在Bean 的实例化中应用BeanPostProcessor

```java
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

	//...
}
```

后面实现了BeanFactoryPostProcessor 以及 BeanPostProcessor 的Bean 对象就可以完成对容器管理的所有的Bean 进行自定义操作了

类图：

![image](https://bugstack.cn/assets/images/spring/spring-7-03.png)
