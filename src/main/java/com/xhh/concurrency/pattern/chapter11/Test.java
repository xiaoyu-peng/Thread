package com.xhh.concurrency.pattern.chapter11;

import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {

        final ExecutionTask executionTask = new ExecutionTask();
        IntStream.range(1, 5).forEach(i -> {
            new Thread(new ExecutionTask()).start();
        });
    }
}
