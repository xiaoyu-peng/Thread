package com.xhh.concurrency.pattern.chapter06;

/**
 * 读写锁的两种名称：
 * 1. ReadWriteLock design pattern
 * 2. Reader Writer design pattern
 *
 * @author PengHui
 */
public class ReadWriterLockClient {
    public static void main(String[] args) {
        final SharedData data = new SharedData(10);
        System.out.println("开始测试");

        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();

        new WriterWorker(data, "dokdjfalqeqeqlafod").start();
        new WriterWorker(data, "DOKDJFALQEQEQLAFOD").start();
    }
}
