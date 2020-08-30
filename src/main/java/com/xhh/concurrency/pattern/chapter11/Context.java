package com.xhh.concurrency.pattern.chapter11;

/**
 * @author PengHui
 */
public class Context {

    private String name;
    private String cardId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Context{" +
                "name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
