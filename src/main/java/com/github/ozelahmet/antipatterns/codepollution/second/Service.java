package com.github.ozelahmet.antipatterns.codepollution.second;

public class Service {

    private final Logger logger;

    public Service(Logger logger) {
        this.logger = logger;
    }

    public void doSomething() {
        logger.info("Doing something...");
        // do something
    }
}
