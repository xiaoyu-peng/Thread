package com.xhh.concurrency.chapter09;

import java.util.stream.Stream;

/**
 *
 * 生产者和消费者 版本二 多线程情况下, notify() 不清楚唤醒的线程，程序进入"假死"状态
 *
 * p1、p2、c1、c2 线程都进入等待状态，即“假死”状态
 *
 * @author xhhui
 */
public class ProductConsumer02 {

    private int i = 1;

    final Object lock = new Object();

    private volatile boolean isProduced = true;


    // 生产者
    public void produce(){
        synchronized (lock){
            if(isProduced){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                i++;
                System.out.println(Thread.currentThread().getName() +" p-> "+ i);
                lock.notify();
                isProduced = true;
            }
        }
    }

    // 消费者
    public void consume(){
        synchronized (lock){
            if(isProduced){
                System.out.println(Thread.currentThread().getName() +" c-> "+ i);
                lock.notify();
                isProduced = false;
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        ProductConsumer02 pc = new ProductConsumer02();

        Stream.of("p1", "p2").forEach(n -> {
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                    }
                }
            }.start();
        });


        Stream.of("c1", "c2").forEach(n -> {
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        pc.consume();
                    }
                }
            }.start();
        });
    }

    /**
     * 运行结果：
     *
     * D:\Software\Java\jdk1.8.0_221\bin\java.exe -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:52509,suspend=y,server=n -javaagent:C:\Users\xhh\.IntelliJIdea2019.3\system\captureAgent\debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "D:\Software\Java\jdk1.8.0_221\jre\lib\charsets.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\deploy.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\access-bridge-64.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\cldrdata.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\dnsns.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\jaccess.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\jfxrt.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\localedata.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\nashorn.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunec.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunjce_provider.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunmscapi.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\sunpkcs11.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\ext\zipfs.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\javaws.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jce.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jfr.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jfxswt.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\jsse.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\management-agent.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\plugin.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\resources.jar;D:\Software\Java\jdk1.8.0_221\jre\lib\rt.jar;D:\work\Udesk\Thread-learn\target\classes;D:\Software\JetBrains\IntelliJ IDEA 2019.3.2\lib\idea_rt.jar" com.xhh.concurrency.chapter09.ProductConsumer02
     * Connected to the target VM, address: '127.0.0.1:52509', transport: 'socket'
     * c1 c-> 1
     * p1 p-> 2
     * c2 c-> 2
     * p2 p-> 3
     *
     *
     */

    /**
     * jstack 分析
     *C:\Users\xhh>jps
     * 11060 KotlinCompileDaemon
     * 14628
     * 4036 Launcher
     * 1368 Jps
     * 10348 ProductConsumer02
     *
     * C:\Users\xhh>jstack 10348
     * 2020-07-26 15:15:25
     * Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.221-b11 mixed mode):
     *
     * "DestroyJavaVM" #18 prio=5 os_prio=0 tid=0x0000000003653800 nid=0x508 waiting on condition [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "c2" #17 prio=5 os_prio=0 tid=0x0000000024bbf000 nid=0x34cc in Object.wait() [0x000000002577e000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x0000000740dd48a0> (a java.lang.Object)
     *         at java.lang.Object.wait(Object.java:502)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02.consume(ProductConsumer02.java:46)
     *         - locked <0x0000000740dd48a0> (a java.lang.Object)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02$2.run(ProductConsumer02.java:76)
     *
     * "c1" #16 prio=5 os_prio=0 tid=0x0000000024bba000 nid=0x3fb0 in Object.wait() [0x000000002567f000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x0000000740dd48a0> (a java.lang.Object)
     *         at java.lang.Object.wait(Object.java:502)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02.consume(ProductConsumer02.java:46)
     *         - locked <0x0000000740dd48a0> (a java.lang.Object)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02$2.run(ProductConsumer02.java:76)
     *
     * "p2" #15 prio=5 os_prio=0 tid=0x0000000024bf7800 nid=0x7f0 in Object.wait() [0x000000002557e000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x0000000740dd48a0> (a java.lang.Object)
     *         at java.lang.Object.wait(Object.java:502)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02.produce(ProductConsumer02.java:24)
     *         - locked <0x0000000740dd48a0> (a java.lang.Object)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02$1.run(ProductConsumer02.java:64)
     *
     * "p1" #14 prio=5 os_prio=0 tid=0x0000000024bf7000 nid=0x31b4 in Object.wait() [0x000000002547f000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x0000000740dd48a0> (a java.lang.Object)
     *         at java.lang.Object.wait(Object.java:502)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02.produce(ProductConsumer02.java:24)
     *         - locked <0x0000000740dd48a0> (a java.lang.Object)
     *         at com.xhh.concurrency.chapter09.ProductConsumer02$1.run(ProductConsumer02.java:64)
     *
     * "Service Thread" #13 daemon prio=9 os_prio=0 tid=0x0000000023bec800 nid=0x2384 runnable [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "C1 CompilerThread3" #12 daemon prio=9 os_prio=2 tid=0x0000000023b52800 nid=0x2418 waiting on condition [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "C2 CompilerThread2" #11 daemon prio=9 os_prio=2 tid=0x0000000023b52000 nid=0x1f1c waiting on condition [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "C2 CompilerThread1" #10 daemon prio=9 os_prio=2 tid=0x0000000023b4f000 nid=0x346c waiting on condition [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "C2 CompilerThread0" #9 daemon prio=9 os_prio=2 tid=0x0000000023b4a000 nid=0x3ccc waiting on condition [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "JDWP Command Reader" #8 daemon prio=10 os_prio=0 tid=0x0000000023ac7000 nid=0x36fc runnable [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "JDWP Event Helper Thread" #7 daemon prio=10 os_prio=0 tid=0x0000000023ac3800 nid=0x3140 runnable [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "JDWP Transport Listener: dt_socket" #6 daemon prio=10 os_prio=0 tid=0x0000000023ab9800 nid=0xf54 runnable [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x0000000023a5e000 nid=0x20bc waiting on condition [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x0000000023a5d000 nid=0x45d4 runnable [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     *
     * "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000023a40800 nid=0x3254 in Object.wait() [0x000000002401e000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x0000000740c08ed8> (a java.lang.ref.ReferenceQueue$Lock)
     *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
     *         - locked <0x0000000740c08ed8> (a java.lang.ref.ReferenceQueue$Lock)
     *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
     *         at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)
     *
     * "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000021c3d000 nid=0x2770 in Object.wait() [0x0000000023f1f000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x0000000740c06c00> (a java.lang.ref.Reference$Lock)
     *         at java.lang.Object.wait(Object.java:502)
     *         at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
     *         - locked <0x0000000740c06c00> (a java.lang.ref.Reference$Lock)
     *         at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)
     *
     * "VM Thread" os_prio=2 tid=0x0000000021c38800 nid=0xf20 runnable
     *
     * "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000003669000 nid=0x3230 runnable
     *
     * "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x000000000366a800 nid=0x1104 runnable
     *
     * "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x000000000366c000 nid=0x3864 runnable
     *
     * "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x000000000366e800 nid=0x2560 runnable
     *
     * "GC task thread#4 (ParallelGC)" os_prio=0 tid=0x0000000003670800 nid=0xa2c runnable
     *
     * "GC task thread#5 (ParallelGC)" os_prio=0 tid=0x0000000003672000 nid=0x29bc runnable
     *
     * "GC task thread#6 (ParallelGC)" os_prio=0 tid=0x0000000003675000 nid=0x3680 runnable
     *
     * "GC task thread#7 (ParallelGC)" os_prio=0 tid=0x0000000003676000 nid=0x10f4 runnable
     *
     * "VM Periodic Task Thread" os_prio=2 tid=0x0000000023c5e000 nid=0x14a0 waiting on condition
     *
     * JNI global references: 2394
     */
}
