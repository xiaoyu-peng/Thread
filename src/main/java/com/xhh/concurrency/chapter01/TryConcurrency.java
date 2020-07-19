package com.xhh.concurrency.chapter01;

/**
 * 多线程入门
 */
public class TryConcurrency {

    public static void main(String[] args) {


        System.out.println("当前线程：" + Thread.currentThread().getName());


        new Thread("Read-Thread"){
            @Override
            public void run(){
                println("当前线程：" + Thread.currentThread().getName());
                readFromDB();
            }
        }.start();


        new Thread("Write-Thread"){
            @Override
            public void run(){
                println("当前线程：" + Thread.currentThread().getName());
                writeFromDB();
            }
        }.start();

    }


    private static void readFromDB(){
        // read data from DB and handle it
        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 10L);
            println("Read data done and handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }

    private static void writeFromDB(){
        // write data from DB and handle it
        try {
            println("Begin write data to file.");
            Thread.sleep(2000 * 10L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }

    private static void println(String message){
        System.out.println(message);
    }
}
