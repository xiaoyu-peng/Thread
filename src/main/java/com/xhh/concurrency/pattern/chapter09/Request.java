package com.xhh.concurrency.pattern.chapter09;

public class Request {

    final private String value;

    public String getValue() {
        return value;
    }

    public Request(String value) {
        this.value = value;
    }
}
