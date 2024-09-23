package com.github.ozelahmet.mockito.time;

import java.time.Duration;
import java.time.LocalDateTime;

public class Service {

    DateTimeServer dateTimeServer = new DateTimeServerImpl();

    /**
     * In the test of this method, <code>LocalDateTime</code> can be mocked statically.
     */
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    /**
     * If static mock is not possible, making time service an explicit dependency may be useful.
     */
    public Duration getDifference() throws InterruptedException {
        LocalDateTime before = dateTimeServer.getNow();

        // an operation
        Thread.sleep(1);

        LocalDateTime after = dateTimeServer.getNow();

        return Duration.between(before, after);
    }
}
