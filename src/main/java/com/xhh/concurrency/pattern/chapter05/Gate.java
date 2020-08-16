package com.xhh.concurrency.pattern.chapter05;

/**
 *
 * SHARE RESOURCE 共享资源
 *
 * synchronized 解决线程安全问题，但是存在读的情况下加锁，效率比较低
 *
 * 大门类
 * @author PengHui
 */
public class Gate {

    private int count = 0 ;
    private String name = "nobody";
    private String address = "nowhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address){

        /**
         * 发生竞争
         */
        this.name = name;
        this.address = address;
        this.count ++;

        verify();

    }

    private void verify() {
        if(this.name.charAt(0) != this.address.charAt(0)){
            System.out.println("************ BROKEN ************"+ toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "Gate{" +
                "No=" + count +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
