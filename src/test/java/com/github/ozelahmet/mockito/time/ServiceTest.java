package com.github.ozelahmet.mockito.time;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServiceTest {

    @Test
    void getNow() {
        Service service = new Service();
        LocalDateTime constantNow = LocalDateTime.now();

        try (MockedStatic<LocalDateTime> localDateTime = mockStatic(LocalDateTime.class)) {
            localDateTime.when(LocalDateTime::now).thenReturn(constantNow);

            LocalDateTime now = service.getNow();

            assertEquals(constantNow, now);
        }
    }

    /**
     * Mocking the <code>LocalDateTime</code> directly is not possible because of the <<code>between</code> operaion.
     * Therefore, <code>DateTimeServer</code> can be mocked here.
     */
    @Test
    void getDifference() throws InterruptedException {
        DateTimeServer dateTimeServer = mock(DateTimeServer.class);
        Service service = new Service();
        service.dateTimeServer = dateTimeServer;

        LocalDateTime before = LocalDateTime.now();
        LocalDateTime after = before.plus(Duration.ofSeconds(2));
        when(dateTimeServer.getNow()).thenReturn(before, after);

        Duration difference = service.getDifference();

        assertEquals(Duration.ofSeconds(2), difference);
    }

    /**
     * Static mocking can still be used with some extra settings. Test is getting more complex.
     */
    @Test
    void getDifferenceStaticMock() throws InterruptedException {
        Service service = new Service();
        LocalDateTime before = LocalDateTime.now();
        LocalDateTime after = before.plus(Duration.ofSeconds(2));

        try (MockedStatic<LocalDateTime> localDateTime = mockStatic(LocalDateTime.class, Mockito.withSettings().defaultAnswer(CALLS_REAL_METHODS))) {
            localDateTime.when(LocalDateTime::now).thenReturn(before, after);

            Duration difference = service.getDifference();

            assertEquals(Duration.ofSeconds(2), difference);
        }
    }

}