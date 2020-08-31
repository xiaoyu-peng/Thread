package com.xhh.concurrency.pattern.chapter16;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        ConterIncrement conterIncrement = new ConterIncrement();
        conterIncrement.start();

        Thread.sleep(10_000L);

        conterIncrement.close();
    }
}
