package com.xhh.concurrency.pattern.chapter06;

/**
 * Read worker
 * @author PengHui
 */
public class ReadWorker extends Thread {

    private final SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() +" read "+ String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
