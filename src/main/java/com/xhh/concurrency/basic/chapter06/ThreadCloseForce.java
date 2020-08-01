package com.xhh.concurrency.basic.chapter06;

/**
 * @author PengHui
 */
public class ThreadCloseForce {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ThreadService threadService = new ThreadService();
        threadService.execute( () -> {
            // 第一种情况，加载超长时间 load a vary heavy resource
            /*while(true){

            }*/

            // 第二种情况： 很短时间内结束
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadService.shutdown(10000);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }

}
