package com.xhh.concurrency.pattern.chapter03;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if(state == this.state){
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    public void attch(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }
}
