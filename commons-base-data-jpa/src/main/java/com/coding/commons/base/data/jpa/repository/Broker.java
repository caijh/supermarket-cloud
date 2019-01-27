package com.coding.commons.base.data.jpa.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broker {

    private static final Logger LOGGER = LoggerFactory.getLogger(Broker.class);

    private static final LinkedBlockingQueue<Message> QUEUE = new LinkedBlockingQueue<>(10000);

    private static final Dispatch DISPATCH = new Dispatch();

    static {
        DISPATCH.start();
    }

    public static void accept(ActionMessage actionMessage) {
        try {
            QUEUE.put(actionMessage);
        } catch (Exception e) {
            LOGGER.error("queue accept fail", e);
        }
    }

    private static class Dispatch extends Thread {

        private Map<String, LinkedBlockingQueue<Message>> topicMap = new HashMap<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Message actionMessage = QUEUE.take();
                    if (!topicMap.containsKey(actionMessage.topic())) {
                        LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
                        topicMap.put(actionMessage.topic(), queue);
                        Consumer consumer = new Consumer(queue);
                        consumer.start();
                    } else {
                        topicMap.get(actionMessage.topic()).put(actionMessage);
                    }
                } catch (Exception e) {
                    LOGGER.error("Dispatch run fail", e);
                }
            }
        }

    }

}
