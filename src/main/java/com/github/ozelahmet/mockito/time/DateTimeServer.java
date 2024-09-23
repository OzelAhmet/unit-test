package com.github.ozelahmet.mockito.time;

import java.time.LocalDateTime;

public interface DateTimeServer {
    LocalDateTime getNow();
}

class DateTimeServerImpl implements DateTimeServer {

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
