package com.xhh.concurrency.basic.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author PH
 * @date 2020/7/27 19:56
 * @description
 */
public class LockTest01 {

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();

        Stream.of("t1", "t2", "t3").forEach( name -> {
            new Thread( () -> {
                try {
                    booleanLock.lock(10);
                    Optional.of(Thread.currentThread().getName() + " have the lock monitor").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException | Lock.TimeOutException e) {
                    Optional.of(Thread.currentThread().getName() + " time out").ifPresent(System.out::println);
                    e.printStackTrace();
                } finally {
                    booleanLock.unLock();
                }
            }, name).start();
        });

    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
