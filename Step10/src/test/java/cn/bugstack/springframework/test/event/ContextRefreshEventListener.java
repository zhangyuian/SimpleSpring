package cn.bugstack.springframework.test.event;

import cn.bugstack.springframework.context.ApplicationListener;
import cn.bugstack.springframework.context.event.ContextRefreshedEvent;

import java.util.Date;

public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新事件：" + event.getSource());
    }
}
