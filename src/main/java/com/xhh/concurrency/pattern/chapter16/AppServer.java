package com.xhh.concurrency.pattern.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppServer extends Thread {

    private final int port;

    private static final int DEFAULT_PORT = 8888;

    private volatile boolean start = true;

    private List<Thread> clientHandlers = new ArrayList<>();

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket();
            while(start){
                Socket client = server.accept();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutDown(){
        this.start = false;
    }
}
