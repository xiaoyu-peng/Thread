package com.xhh.concurrency.pattern.chapter11;

public class QueryFromDBAction {

    public void execute(){
        //从数据库中查询，赋值给context
        try {
            Context context = ActionContext.getInstance().getContext();
            Thread.sleep(10000L);
            String name = Thread.currentThread().getName() +  " GET FROM DB NAME: TOM";
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}
