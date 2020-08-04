package com.xhh.concurrency.basic.chapter13;

/**
 * 自定义线程池
 */
public class SimpleThreadPool {

    private final int size;

    private final static int DEFAULT_SIZE = 10;

    public SimpleThreadPool(){
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size){
        this.size = size;
        init();
    }


    private void init() {

    }

}
