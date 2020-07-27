package com.xhh.concurrency.chapter10;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
                    booleanLock.lock();
                    Optional.of(Thread.currentThread().getName() + " have the lock monitor");
                    work();
                } catch (InterruptedException e) {
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
