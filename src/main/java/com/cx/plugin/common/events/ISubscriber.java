package com.cx.plugin.common.events;

/**
 * Created by eranb on 17/11/2016.
 */
public interface ISubscriber {
    void eventPublished(String eventName, String parameter);
}
