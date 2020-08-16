package com.xhh.concurrency.pattern.chapter03.observer;

public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject){
        this.subject = subject;
        this.subject.attch(this);
    }

    public abstract void update();
}
