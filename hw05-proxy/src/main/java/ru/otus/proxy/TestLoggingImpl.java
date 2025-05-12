package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLoggingImpl implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLoggingImpl.class);

    @Override
    @Log
    public void calculation(int param) {
        logger.info("Some calculation work1");
    }

    @Override
    @Log
    public void calculation(int param, int param2) {
        logger.info("Some calculation work2");
    }

    @Override
    @Log
    public void calculation(int param, int param2, int param3) {
        logger.info("Some calculation work3");
    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}
