package cn.bugstack.springframework.context.event;

import cn.bugstack.springframework.context.ApplicationEvent;
import cn.bugstack.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events
     * @param listener
     */
    void addApplicaitonListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list
     * @param listener
     */
    void removeApplicaitonListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners
     * @param event the event to multicast
     */
    void muticastEvent(ApplicationEvent event);
}
