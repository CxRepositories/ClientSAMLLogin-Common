package com.checkmarx.plugin.common.events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by eranb on 17/11/2016.
 */
public class EventBus {

    static Map<UUID, ISubscriber> subscribers = new HashMap<UUID, ISubscriber>();

    public static void subscribe(UUID subScriberId, ISubscriber subscriber) {
        if (!subscribers.containsKey(subScriberId))
            subscribers.put(subScriberId, subscriber);
    }

    public static void unsubsribe(UUID subscriberId) {
        if (subscribers.containsKey(subscriberId))
            subscribers.remove(subscriberId);
    }

    public static void publish(String eventName, UUID subScriberKey, String value) {
        if (!subscribers.containsKey(subScriberKey)) return;


        ISubscriber subscriber = subscribers.get(subScriberKey);
        subscriber.eventPublished(eventName, value);
    }
}
