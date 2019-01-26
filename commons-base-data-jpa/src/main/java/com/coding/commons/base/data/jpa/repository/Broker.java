package com.coding.commons.base.data.jpa.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broker {

    private static final Logger LOGGER = LoggerFactory.getLogger(Broker.class);

    private static final LinkedBlockingQueue<Action> QUEUE = new LinkedBlockingQueue<>(10000);

    private static final Dispatch DISPATCH = new Dispatch();

    static {
        DISPATCH.start();
    }

    public static void add(Action action) {
        try {
            QUEUE.put(action);
        } catch (Exception e) {
            LOGGER.error("queue add fail", e);
        }
    }

    private static class Dispatch extends Thread {

        private Map<String, LinkedBlockingQueue<Action>> queueMap = new HashMap<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Action action = QUEUE.take();
                    if (!queueMap.containsKey(action.group())) {
                        LinkedBlockingQueue<Action> queue = new LinkedBlockingQueue<>();
                        queueMap.put(action.group(), queue);
                        Consumer consumer = new Consumer(queue);
                        consumer.start();
                    } else {
                        queueMap.get(action.group()).put(action);
                    }
                } catch (Exception e) {
                    LOGGER.error("Dispatch run fail", e);
                }
            }
        }

    }

}
