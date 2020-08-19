package com.xhh.concurrency.pattern.chapter07;


/**
 *
 * 定义不可变对象的策略
 *
 * 1. 不提供“setter”方法 - 修改字段引用的字段或对象的方法。
 * 2. 使用 final 和 private 域。
 * 3. 不允许子类覆盖方法。最简单的方法是将类声明为 final。更复杂的方法是 private 在工厂方法中使构造函数和构造实例。
 * 4. 如果实例字段包含对可变对象的引用，则不允许更改这些对象：
 *      1) 不提供修改可变对象的方法。
 *      2) 不要共享对可变对象的引用。不要存储对传递给构造函数的外部可变对象的引用; 如有必要，请创建副本，并存储对副本的引用。同样，在必要时创建内部可变对象的副本，以避免在方法中返回原件。
 *
 */
final public class Person {

    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
