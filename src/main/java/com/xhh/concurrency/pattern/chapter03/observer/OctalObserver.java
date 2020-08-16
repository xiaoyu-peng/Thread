package com.xhh.concurrency.pattern.chapter03.observer;

/**
 * 八进制
 * @author PengHui
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String to : "+ Integer.toOctalString(subject.getState()));
    }
}
