package com.xhh.concurrency.basic.chapter13;

/**
 * @author PengHui
 */
public class WorkerTaskThread extends Thread {

    private volatile TaskState taskState = TaskState.FREE;

}
