package com.xhh.concurrency.basic.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author PH
 * @date 2020/7/27 19:33
 * @description
 */
public class BooleanLock implements Lock {

    // The initValue is true  indicated the lock have be get.
    // The initValue is false  indicated the lock is free (other thread can get this.)
    private boolean initValue;

    private Collection<Thread> blockedThreads = new ArrayList<>();

    // 记录当前所
    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue){
            blockedThreads.add(Thread.currentThread());
            this.wait();
        }

        // 获取到锁
        this.initValue = true;
        blockedThreads.remove(Thread.currentThread());

        // 记录当前锁
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mils) throws InterruptedException, TimeOutException {
        if(mils<0){
            lock();
        }

        long hasRemaining = mils;
        long endTime = System.currentTimeMillis() + mils;
        while (initValue){
            if(hasRemaining<=0) {
                throw new TimeOutException("Time out");
            }
            blockedThreads.add(Thread.currentThread());
            this.wait(mils);
            hasRemaining = endTime - System.currentTimeMillis();
        }

        // 抢到锁了
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unLock() {
       if(Thread.currentThread() == currentThread){
           this.initValue = false;
           Optional.of(Thread.currentThread().getName() + " release the lock monitor.").ifPresent(System.out::println);
           this.notifyAll();
       }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        // 禁止修改
        return Collections.unmodifiableCollection(blockedThreads);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreads.size();
    }
}
