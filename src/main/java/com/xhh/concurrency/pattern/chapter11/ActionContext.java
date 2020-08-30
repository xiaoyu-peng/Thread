package com.xhh.concurrency.pattern.chapter11;

/**
 * @author PengHui
 */
public class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder{
        private final static ActionContext ACTION_CONTEXT = new ActionContext();
    }

    public static ActionContext getInstance(){
        return ContextHolder.ACTION_CONTEXT;
    }

    public Context getContext(){
        return threadLocal.get();
    }

}
