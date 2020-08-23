package com.xhh.concurrency.pattern.chapter10;

public class ThreadLocalTest {

    private static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        threadLocal.set("XHH");
        System.out.println(threadLocal.get());
    }
}
