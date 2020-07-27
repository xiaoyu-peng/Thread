package com.xhh.concurrency.chapter09;

import java.util.*;

/**
 * @author PH
 * @date 2020/7/27 18:58
 * @description 通知机制综合运用 模拟运行的线程最多为3个
 */
public class CaptureService {

    /**
     * 容器：当前允许运行的线程
     */
    final static private LinkedList<Control> CONTROLS = new LinkedList<>();

    private final static int WORKER_MAX = 3;

    public static void main(String[] args) {

        final List<Thread> worker = new ArrayList<Thread>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6").stream().map(CaptureService::createCaptureThread)
                .forEach( t -> {
                    t.start();
                    worker.add(t);
                });

        worker.stream().forEach( t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture work finished. ").ifPresent(System.out::println);
    }

    private static Thread createCaptureThread(String name){
        return new Thread( () -> {
            Optional.of("The worker [" + Thread.currentThread().getName() + "] begin capture data. ").ifPresent(System.out::println);
            synchronized (CONTROLS){
                while(CONTROLS.size()>WORKER_MAX){
                    try {
                        // 等待
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // synchronized 保护
                CONTROLS.add(new Control());
            }


            Optional.of("The worker [" + Thread.currentThread().getName() + "] is working...").ifPresent(System.out::println);
            try {
                // 数据采集时间为10s
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CONTROLS){
                Optional.of("The worker [" + Thread.currentThread().getName() + "] END capture data.").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }

        }, name);
    }

    private static class Control{

    }
}


