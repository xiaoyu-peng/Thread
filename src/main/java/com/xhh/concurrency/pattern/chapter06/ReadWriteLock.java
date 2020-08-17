package com.xhh.concurrency.pattern.chapter06;

import java.io.Reader;

/**
 * 读写锁
 *
 * @author PengHui
 */
public class ReadWriteLock {

    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 读锁
     */
    public synchronized void readLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (waitingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                // 存在写操作，则读操作wait
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnLock() {
        this.readingReaders--;
        this.notifyAll();
    }

    /**
     * 写锁，最多只有一个
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;

        try {
            while (readingReaders > 0 || waitingWriters > 0) {
                this.wait();
            }
            this.waitingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }


    public synchronized void writeUnLock() {
        this.waitingWriters--;
        this.notifyAll();
    }

}
