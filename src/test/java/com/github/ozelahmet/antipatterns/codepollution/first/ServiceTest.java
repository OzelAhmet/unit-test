package com.github.ozelahmet.antipatterns.codepollution.first;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    /**
     * We changed the production code only for testing purposes.
     */
    @Test
    void doSomething() {
        Logger logger = new Logger(true);
        Service service = new Service(logger);

        service.doSomething();

        // assertions
    }
}