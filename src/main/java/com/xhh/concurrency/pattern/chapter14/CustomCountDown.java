package com.xhh.concurrency.pattern.chapter14;

public class CustomCountDown {

    private final int total;

    private int counter = 0;

    public CustomCountDown(int total) {
        this.total = total;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }

}
