package com.xhh.concurrency.pattern.chapter09;

import java.util.Queue;

/**
 * @author PengHui
 */
public class SuspensionTest {

    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "java").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(10_000L);

        serverThread.close();
    }
}
