package com.xhh.concurrency.pattern.chapter07;

import java.util.stream.IntStream;

public class ImmutableClient {

    public static void main(String[] args) {
        // share data
        Person person = new Person("xiaoyu", "BeiJing");

        IntStream.range(0,5).forEach(i -> {
            new UsePersonThread(person).start();
        });
    }
}
