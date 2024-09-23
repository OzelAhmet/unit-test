package com.github.ozelahmet.antipatterns.codepollution.first;

public class Logger {

    private final boolean isTestEnvironment;

    public Logger(boolean isTestEnvironment) {
        this.isTestEnvironment = isTestEnvironment;
    }

    public void info(String message) {
        if (isTestEnvironment) {
            return;
        }
        // log the text into a file
    }
}
