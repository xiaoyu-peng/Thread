package com.xhh.concurrency.pattern.chapter03.observertest;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.List;

/**
 * @author PengHui
 */
public class LifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids){
        if(ids==null || ids.isEmpty()){
            return;
        }

        ids.stream().forEach( id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("Query for id " + id);
                    Thread.sleep(10_000L);
                    //int x = 1 / 0;
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());

    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("The runnable "+ Thread.currentThread().getName() +" , data changed and state is "+ event.getState());
            if(event.getState() != null){
                //失败
                System.out.println("The runnable "+ Thread.currentThread().getName() +" , data process field; cause is " + event.getCause());
            }

        }
    }
}
