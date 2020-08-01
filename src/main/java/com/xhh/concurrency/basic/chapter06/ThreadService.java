package com.xhh.concurrency.basic.chapter06;

/**
 * 小技巧一： 利用 守护线程结束
 * @author PengHui
 */
public class ThreadService {

    // 执行线程
    private Thread executeThread;
    private boolean finish = false;

    /**
     * 执行线程 生命周期结束，守护线程生命周期跟随着一起结束
     * @param task
     */
    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run(){
                Thread runner = new Thread(task);

                // 把runnerThread设置为执行线程executeThread的守护线程
                runner.setDaemon(true);
                runner.start();

                try {
                    // 确保runnerThread执行完成
                    runner.join();
                    // 执行结束后，finish标志位true
                    finish = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executeThread.start();
    }

    /**
     * 等待mils后，关闭进程
     * @param mils
     */
    public void shutdown(long mils){
        long currentTime = System.currentTimeMillis();

        // 如果没有执行结束
        while(!finish){
            // 任务超时，需要结束它
            if(System.currentTimeMillis() - currentTime >= mils){
                System.out.println("任务超时，需要结束次任务！");
                executeThread.interrupt();
                break;
            }

            // 如果既没有超时，也没有执行结束  则进行短暂的休眠
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断！");
                break;
            }
        }

        finish = false;
    }
}
