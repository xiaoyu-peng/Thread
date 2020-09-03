package com.xhh.concurrency.pattern.chapter16;

import java.io.IOException;

public class AppServerClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer appServer = new AppServer(18888);
        appServer.start();

        Thread.sleep(45_000L);
        appServer.shutDown();
    }
}
