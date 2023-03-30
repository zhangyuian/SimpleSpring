package cn.bugstack.springframework.beans.factory;

import cn.bugstack.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name, Object... args) throws BeansException;

}
