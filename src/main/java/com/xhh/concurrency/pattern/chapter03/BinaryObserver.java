package com.xhh.concurrency.pattern.chapter03;

/**
 * 二进制
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String to : "+ Integer.toBinaryString(subject.getState()));
    }
}
