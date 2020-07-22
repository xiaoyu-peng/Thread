package com.xhh.concurrency.chapter04;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class JoinThread {

    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();

        Thread T1 = new Thread(new CaptureRunnable("M1", 10000L));
        Thread T2 = new Thread(new CaptureRunnable("M2", 30000L));
        Thread T3 = new Thread(new CaptureRunnable("M3", 15000L));

        T1.start();
        T2.start();
        T3.start();

        T1.join();
        T2.join();
        T3.join();


        long endTime = System.currentTimeMillis();

        System.out.println("Save data finish at endTime is " + endTime);
    }
}

class CaptureRunnable implements Runnable{

    private String machineName;
    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime){
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName + " completed capture");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult(){
        return machineName + " finish.";
    }
}
