package com.xhh.concurrency.pattern.chapter05;

public class Client {

    public static void main(String[] args) {

        Gate gate = new Gate();

        User bjUser = new User("BAO", "Bei Jing", gate);
        User shUser = new User("SHANG", "Shang Hai", gate);
        User szUser = new User("Guang", "Guang Zhou", gate);

        bjUser.start();
        shUser.start();
        szUser.start();
    }
}
