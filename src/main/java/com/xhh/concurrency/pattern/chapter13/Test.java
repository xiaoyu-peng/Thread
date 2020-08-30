package com.xhh.concurrency.pattern.chapter13;

public class Test {

    public static void main(String[] args) {
        final MessageQueue queue = new MessageQueue();
        new ProducerThread(queue, 1).start();
        new ConsumerThread(queue, 1).start();
    }
}
