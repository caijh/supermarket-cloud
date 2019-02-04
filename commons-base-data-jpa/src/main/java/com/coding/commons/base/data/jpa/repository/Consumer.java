package com.coding.commons.base.data.jpa.repository;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = queue.take();
                if (message instanceof ActionMessage) {
                    ((ActionMessage) message).exec();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
