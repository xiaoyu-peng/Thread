package com.xhh.concurrency.pattern.chapter01;


import com.xhh.concurrency.basic.chapter07.SynchronizedTest;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author PengHui
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    /**
     * 1. 所有的对象都会有一个wait set，用来存放调用了该对象wait方法之后进入block状态的线程
     * 2. 线程被notify之后，不一定立即得到执行
     * 3. 线程从wait set中，被唤醒的顺序不一定
     * 4. 线程被唤醒，必须重新获取锁
     * @param args
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach( i ->
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    synchronized (LOCK){
                        Optional.of(Thread.currentThread().getName() + " begin...").ifPresent(System.out::println);
                        try {
                            Optional.of(Thread.currentThread().getName() + " will come to wait set").ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + " will leave to wait set").ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Optional.of(Thread.currentThread().getName() + " end...").ifPresent(System.out::println);
                    }
                }
            }.start()
        );

        try {
            Optional.of("sleep begin...").ifPresent(System.out::println);
            Thread.sleep(3_000);
            Optional.of("sleep end...").ifPresent(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.rangeClosed(1, 10).forEach( i ->
                {
                    synchronized (LOCK){
                        LOCK.notify();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }


    /**
     * 运行结果：
     *
     * D:\Software\Java\jdk1.8.0_221\bin\java.exe -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:56373,suspend=y,server=n -javaagent:C:\Users\pengh\.IntelliJIdea2019.3\system\captureAgent\debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "D:\Software\Java\jdk1.8.0_221\jre\lib\charsets.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\deploy.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\access-bridge-64.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\cldrdata.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\dnsns.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\jaccess.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\jfxrt.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\localedata.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\nashorn.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunec.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunjce_provider.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunmscapi.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunpkcs11.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\zipfs.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\javaws.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jce.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jfr.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jfxswt.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jsse.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\management-agent.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\plugin.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\resources.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\rt.jar;D:\work\Udesk\Thread-learn\target\classes;D:\Software\JetBrains\IntelliJ IDEA 2019.3.2\lib\idea_rt.jar" com.xhh.concurrency.pattern.chapter01.WaitSet
     * Connected to the target VM, address: '127.0.0.1:56373', transport: 'socket'
     * 1 will come to wait set
     * 10 will come to wait set
     * 9 will come to wait set
     * 8 will come to wait set
     * 7 will come to wait set
     * 4 will come to wait set
     * 6 will come to wait set
     * 5 will come to wait set
     * 3 will come to wait set
     * 2 will come to wait set
     * 1 will leave to wait set
     * 9 will leave to wait set
     * 10 will leave to wait set
     * 8 will leave to wait set
     * 4 will leave to wait set
     * 7 will leave to wait set
     * 6 will leave to wait set
     * 3 will leave to wait set
     * 5 will leave to wait set
     * 2 will leave to wait set
     * Disconnected from the target VM, address: '127.0.0.1:56373', transport: 'socket'
     *
     * Process finished with exit code 0
     */
}
