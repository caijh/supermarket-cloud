package com.coding.commons.base.data.jpa.repository;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer extends Thread {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private LinkedBlockingQueue<Action> queue;

    public Consumer(LinkedBlockingQueue<Action> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Action action = queue.take();
                action.exec();
            } catch (Exception e) {
                logger.error("consumer fail", e);
            }
        }
    }

}
