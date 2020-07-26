package com.xhh.concurrency.chapter07;

/**
 * synchronized 关键词测试
 * @author PengHui
 */
public class SynchronizedTest {

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Runnable runnable = () -> {
            synchronized (lock){
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }


    /**
     * 反编译
     *
     * javap -c TicketWindowRunnable.class
     *
     * Compiled from "TicketWindowRunnable.java"
     * public class com.xhh.concurrency.chapter07.TicketWindowRunnable implements java.lang.Runnable {
     *   public com.xhh.concurrency.chapter07.TicketWindowRunnable();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: aload_0
     *        5: iconst_1
     *        6: putfield      #2                  // Field index:I
     *        9: aload_0
     *       10: sipush        500
     *       13: putfield      #3                  // Field MAX:I
     *       16: aload_0
     *       17: new           #4                  // class java/lang/Object
     *       20: dup
     *       21: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *       24: putfield      #5                  // Field MONITOR:Ljava/lang/Object;
     *       27: return
     *
     *   public void run();
     *     Code:
     *        0: aload_0
     *        1: getfield      #5                  // Field MONITOR:Ljava/lang/Object;
     *        4: dup
     *        5: astore_1
     *        6: monitorenter
     *        7: aload_0
     *        8: getfield      #2                  // Field index:I
     *       11: sipush        500
     *       14: if_icmple     22
     *       17: aload_1
     *       18: monitorexit
     *       19: goto          96
     *       22: lconst_1
     *       23: invokestatic  #7                  // Method java/lang/Thread.sleep:(J)V
     *       26: goto          34
     *       29: astore_2
     *       30: aload_2
     *       31: invokevirtual #9                  // Method java/lang/InterruptedException.printStackTrace:()V
     *       34: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       37: new           #11                 // class java/lang/StringBuilder
     *       40: dup
     *       41: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       44: ldc           #13                 // String 当前柜台
     *       46: invokevirtual #14                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       49: invokestatic  #15                 // Method java/lang/Thread.currentThread:()Ljava/lang/Thread;
     *       52: invokevirtual #16                 // Method java/lang/Thread.getName:()Ljava/lang/String;
     *       55: invokevirtual #14                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       58: ldc           #17                 // String ，当前号码是：
     *       60: invokevirtual #14                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       63: aload_0
     *       64: dup
     *       65: getfield      #2                  // Field index:I
     *       68: dup_x1
     *       69: iconst_1
     *       70: iadd
     *       71: putfield      #2                  // Field index:I
     *       74: invokevirtual #18                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
     *       77: invokevirtual #19                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       80: invokevirtual #20                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       83: aload_1
     *       84: monitorexit
     *       85: goto          93
     *       88: astore_3
     *       89: aload_1
     *       90: monitorexit
     *       91: aload_3
     *       92: athrow
     *       93: goto          0
     *       96: return
     *     Exception table:
     *        from    to  target type
     *           22    26    29   Class java/lang/InterruptedException
     *            7    19    88   any
     *           22    85    88   any
     *           88    91    88   any
     * }
     */
}
