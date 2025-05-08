package ru.otus.framework;

import java.util.List;

public record Report(int testsQuantity, int passed, int failed, List<String> passedMethods, List<String> failedMethods) {
}
