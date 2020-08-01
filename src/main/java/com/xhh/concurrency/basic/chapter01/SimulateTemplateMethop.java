package com.xhh.concurrency.basic.chapter01;

/**
 * 模拟模板设计模式
 */
public class SimulateTemplateMethop {

    public final void printMsg(String message){
        System.out.println("##########################");
        wrapPrintMsg(message);
        System.out.println("##########################");
    }

    protected void wrapPrintMsg(String message) {

    }

    public static void main(String[] args) {

        SimulateTemplateMethop t1 = new SimulateTemplateMethop(){
            @Override
            protected void wrapPrintMsg(String message) {
                System.out.println("*** " + message + " ***");
            }
        };
        t1.printMsg("Hello Thread");


        SimulateTemplateMethop t2 = new SimulateTemplateMethop(){
            @Override
            protected void wrapPrintMsg(String message) {
                System.out.println("@@@ " + message + " @@@");
            }
        };
        t2.printMsg("Hello Thread");

    }

}
