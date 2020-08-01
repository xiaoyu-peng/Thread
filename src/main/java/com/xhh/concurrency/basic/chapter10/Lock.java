package com.xhh.concurrency.basic.chapter10;

import java.sql.Time;
import java.util.Collection;

/**
 * 自定义显示锁接口
 * @author Administrator
 */
public interface Lock {

    class TimeOutException extends Exception {
        public TimeOutException(String message){
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mils) throws InterruptedException, TimeOutException;

    void unLock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
