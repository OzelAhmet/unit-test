package com.github.ozelahmet.antipatterns.codepollution.second;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    /**
     * We created a test double for <code>Logger</code>. Not changed the production code
     */
    @Test
    void doSomething() {
        Logger logger = new FakeLogger();
        Service service = new Service(logger);

        service.doSomething();

        // assertions
    }

}