package com.xhh.concurrency.pattern.chapter08;

/**
 *
 * FUTURE 模式
 *
 *  Future          -> 代表的是未来的一个逻辑
 *  FutureTask      -> 将你的调用逻辑进行隔离
 *  FutureService   -> 桥接 Future 和 FutureTask
 *
 *
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        /*
        // 阻塞10秒
        String result = get();
        System.out.println(result);*/

        FutureService futureService = new FutureService();
        futureService.submit( () -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        System.out.println("=========================");
        System.out.println(" do other thing. ");
        Thread.sleep(1000);
        System.out.println("=========================");

        // 进入等待状态
        /*System.out.println(future.get());*/
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
