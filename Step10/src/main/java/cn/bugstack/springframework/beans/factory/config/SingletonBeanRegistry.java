package cn.bugstack.springframework.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}
