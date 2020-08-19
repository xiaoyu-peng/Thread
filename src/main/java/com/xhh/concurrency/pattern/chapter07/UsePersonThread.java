package com.xhh.concurrency.pattern.chapter07;

/**
 * @author PengHui
 */
public class UsePersonThread extends Thread {

    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
       while(true){
           System.out.println(Thread.currentThread().getName() +"print"+ person.toString());
       }
    }
}
