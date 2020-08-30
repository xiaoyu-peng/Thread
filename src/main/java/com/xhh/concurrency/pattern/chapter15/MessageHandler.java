package com.xhh.concurrency.pattern.chapter15;

import java.util.Random;

public class MessageHandler {

    private static final Random random = new Random(System.currentTimeMillis());

    public void request(Message message) {
        new Thread(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by "+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
