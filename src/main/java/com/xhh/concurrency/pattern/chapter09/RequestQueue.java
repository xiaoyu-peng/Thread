package com.xhh.concurrency.pattern.chapter09;

import com.sun.org.apache.regexp.internal.RE;

import java.util.LinkedList;

public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest(){
        synchronized (queue){
            while(queue.size()<0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }

            return queue.removeFirst();
        }
    }


    public void putRequest(Request request){
        synchronized (queue){
            queue.add(request);
            queue.notifyAll();
        }
    }
}
