package com.coding.commons.base.data.jpa.repository;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer extends Thread {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private LinkedBlockingQueue<Message> queue;

    public Consumer(LinkedBlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = queue.take();
                if (message instanceof ActionMessage) {
                    ((ActionMessage) message).exec();
                }
            } catch (Exception e) {
                logger.error("consumer fail", e);
            }
        }
    }

}
