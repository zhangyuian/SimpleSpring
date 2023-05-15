package cn.bugstack.springframework.context;

import java.util.EventObject;

public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a protoptypical Event
     * @param source The object on which the Event initially occurred
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}
