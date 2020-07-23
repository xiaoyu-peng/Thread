package com.xhh.concurrency.chapter06;

/**
 * @author PengHui
 *
 * 打断线程的方式---interrupt
 */
public class ThreadCloseGraceful2 {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // interrupt 打断
        worker.interrupt();
        System.out.println("exit");
    }

    private static class Worker extends Thread {
        private volatile boolean status = true;

        @Override
        public void run() {
            while(status){
                // do something.
                System.out.println("do something ...");

                if(Thread.interrupted()){
                    break;
                }


            }
        }

        public void shutDown(){
            this.status=false;
        }
    }
}
