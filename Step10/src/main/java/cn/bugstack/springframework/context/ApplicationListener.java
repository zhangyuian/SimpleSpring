package cn.bugstack.springframework.context;

public interface ApplicationListener<T extends ApplicationEvent> {

    void onApplicationEvent(T event);
}
