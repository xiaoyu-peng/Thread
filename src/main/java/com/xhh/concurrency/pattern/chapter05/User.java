package com.xhh.concurrency.pattern.chapter05;

/**
 * @author PengHui
 */
public class User extends Thread {

    private final String name;

    private final String address;

    private final Gate gate;


    public User(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println("The "+ name +" begin");
        while(true){
            this.gate.pass(name, address);
        }
    }
}
