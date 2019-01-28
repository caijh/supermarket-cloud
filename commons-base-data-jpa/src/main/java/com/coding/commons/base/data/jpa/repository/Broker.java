package com.coding.commons.base.data.jpa.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Broker {

    private static final Logger LOGGER = LoggerFactory.getLogger(Broker.class);

    private static final LinkedBlockingQueue<Message> QUEUE = new LinkedBlockingQueue<>(10000);

    private static final Dispatch DISPATCH = new Dispatch();

    static {
        DISPATCH.start();
    }

    private Broker() {
    }

    static void accept(ActionMessage actionMessage) {
        try {
            QUEUE.put(actionMessage);
        } catch (Exception e) {
            LOGGER.error("queue accept fail", e);
        }
    }

    private static class Dispatch extends Thread {

        private Map<String, LinkedBlockingQueue<Message>> groupMap = new HashMap<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Message message = QUEUE.take();
                    if (!groupMap.containsKey(message.group())) {
                        LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
                        groupMap.put(message.group(), queue);
                        Consumer consumer = new Consumer(queue);
                        consumer.start();
                    } else {
                        groupMap.get(message.group()).put(message);
                    }
                } catch (Exception e) {
                    LOGGER.error("Dispatch run fail", e);
                }
            }
        }
    }

}
