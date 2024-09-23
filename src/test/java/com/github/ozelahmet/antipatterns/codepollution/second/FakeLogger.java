package com.github.ozelahmet.antipatterns.codepollution.second;

public class FakeLogger implements Logger {

    @Override
    public void info(String message) {
        // do nothing
    }
}
