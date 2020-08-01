package com.xhh.concurrency.basic.chapter03;

/**
 * @author PengHui
 *
 * 测试 设置线程的栈大小
 */
public class CreateThread {

    private static int count = 1;

    public static void main(String[] args) {

        Thread t1 = new Thread(null, new Runnable() {
            public void run() {
                try{
                    add(1);
                } catch ( Error e){
                    System.out.println(count);
                }
            }

            private void add(int i){
                count++;
                add(i + 1);
            }
        }, "test-stack-size", 1<<24);

        t1.start();
    }
}
