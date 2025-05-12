package ru.otus.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        TestLoggingInterface myClass = Ioc.createMyClass();
        myClass.calculation(1);
        myClass.calculation(1, 3);
        myClass.calculation(5, 67, 22);
    }
}
