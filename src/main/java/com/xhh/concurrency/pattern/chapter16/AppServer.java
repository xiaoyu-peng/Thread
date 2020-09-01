package com.xhh.concurrency.pattern.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread {

    private final int port;

    private static final int DEFAULT_PORT = 8888;

    private volatile boolean start = true;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * 记录工作线程
     */
    private List<ClientHandler> clientHandlers = new ArrayList<>();

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
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.dispost();
        }
    }

    private void dispost() {
    }

    public void shutDown(){
        this.start = false;
    }
}
