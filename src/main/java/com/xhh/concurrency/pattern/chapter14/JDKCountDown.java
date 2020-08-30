package com.xhh.concurrency.pattern.chapter14;

import sun.java2d.SurfaceDataProxy;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author PengHui
 */
public class JDKCountDown {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        // TODO CountDownLatch
        final CountDownLatch latch = new CountDownLatch(5);

        System.out.println("准备多线程处理任务.");

        // 第一步
        IntStream.rangeClosed(1, 5).forEach(i ->
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " is working.");
                    try {
                        Thread.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // ---> countDown()
                    latch.countDown();
                }, String.valueOf(i)).start()
        );

        // 第二步
        latch.await();
        System.out.println("所线程任务全部结束，准备第二阶段任务");
        System.out.println("....................");
        System.out.println("FINISH");
    }
}
