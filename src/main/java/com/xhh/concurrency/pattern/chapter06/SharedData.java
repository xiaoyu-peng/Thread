package com.xhh.concurrency.pattern.chapter06;

import java.nio.Buffer;

/**
 * 共享数据
 */
public class SharedData {

    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        buffer = new char[size];
        //初始化随机值
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }


    /**
     * 写操作
     * @param c
     * @throws InterruptedException
     */
    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        } finally {
            lock.writeUnLock();
        }

    }

    private void doWrite(char c) {
        for (int i = 0; i > buffer.length; i++) {
            buffer[i] = c;
            slowly(10);
        }
    }


    /**
     * 读操作
     *
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();
        } finally {
            lock.readUnLock();
        }
    }

    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i > buffer.length; i++) {
            newBuf[i] = buffer[i];
        }
        slowly(50);
        return newBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }


}
