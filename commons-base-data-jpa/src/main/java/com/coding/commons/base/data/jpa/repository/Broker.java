package com.coding.commons.base.data.jpa.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Broker {

    private static final Logger LOGGER = LoggerFactory.getLogger(Broker.class);

    private static final BlockingQueue<Message> QUEUE = new LinkedBlockingQueue<>(10000);

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

        private Map<String, BlockingQueue<Message>> groupMap = new HashMap<>();

        @Override
        public void run() {
            try {
                while (true) {
                    Message message = QUEUE.take();
                    if (!groupMap.containsKey(message.group())) {
                        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
                        groupMap.put(message.group(), queue);
                        new Consumer(queue).start();
                    } else {
                        groupMap.get(message.group()).put(message);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
