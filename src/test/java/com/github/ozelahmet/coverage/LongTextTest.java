package com.github.ozelahmet.coverage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongTextTest {

    @Test
    void stringIsLong() {
        boolean stringLong = LongText.isStringLong("asd");
        assertFalse(stringLong);
    }
}