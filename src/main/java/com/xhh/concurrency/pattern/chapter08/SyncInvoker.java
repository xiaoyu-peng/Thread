package com.xhh.concurrency.pattern.chapter08;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        /*
        // 阻塞10秒
        String result = get();
        System.out.println(result);*/

        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit( () -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        });

        System.out.println("=========================");
        System.out.println(" do other thing. ");
        Thread.sleep(1000);
        System.out.println("=========================");
        System.out.println(future.get());
    }

    private static String get() {
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "FINISH";
    }
}
