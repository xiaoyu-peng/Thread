package com.xhh.concurrency.pattern.chapter11;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author PengHui
 */
public class QueryFromHttpAction {

    public void execute() {
        //从网络接口中查询，赋值给context
        Context context = ActionContext.getInstance().getContext();
        String name = context.getName();
        String cardId = Thread.currentThread().getName() +" GET FROM HTTP CARD ID: TOM"+ getCardId(name);
        context.setCardId(cardId);
        System.out.println(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "4112241929429489228641X";
    }
}
