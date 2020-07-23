package com.xhh.concurrency.chapter06;

/**
 * @author PengHui
 * 打断线程的方式---volatile
 */
public class ThreadCloseGraceful {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutDown();
        System.out.println("exit");
    }

    private static class Worker extends Thread {
        private volatile boolean status = true;

        @Override
        public void run() {
            while(status){
                // do something.
                System.out.println("do something ...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutDown(){
            this.status=false;
        }
    }
}
