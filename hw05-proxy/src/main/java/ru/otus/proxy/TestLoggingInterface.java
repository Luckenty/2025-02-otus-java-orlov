package ru.otus.proxy;

public interface TestLoggingInterface {

    @Log
    void calculation(int param);

    @Log
    void calculation(int param, int param2);

    @Log
    void calculation(int param, int param2, int param3);
}
