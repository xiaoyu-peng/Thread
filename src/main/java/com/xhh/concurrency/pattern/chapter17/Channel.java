package com.xhh.concurrency.pattern.chapter17;

import java.util.Arrays;

public class Channel {

    private static final int MAX_REQUEST = 30;

    private final Request[] requestQueue;
    private int head;
    private int tail;
    private int count;
    private final WorkThread[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request(MAX_REQUEST);
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkThread[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWorker() {
        Arrays.asList(workerPool).stream().forEach(WorkerThread::start);
    }


    public synchronized void put(Request request) {
        while (count >= workerPool.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 加入尾部
        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 从开始获取
        Request request = this.requestQueue[head];
        this.head = (head + 1) % requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
