package com.xhh.concurrency.pattern.chapter11;

import javax.management.Query;

/**
 * context 上下文
 *
 * @author PengHui
 */
public class ExecutionTask implements Runnable {

    /**
     * 从数据库获取信息
     */
    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();

    /**
     * 从网络获取信息
     */
    private QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        queryFromDBAction.execute();
        System.out.println("=======日志：======= the name query successful");
        queryFromHttpAction.execute();

        System.out.println("=======日志：======= the card id query successful");

        Context context = ActionContext.getInstance().getContext();
        System.out.println(context.toString());
    }
}
