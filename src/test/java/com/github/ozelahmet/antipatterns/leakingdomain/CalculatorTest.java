package com.github.ozelahmet.antipatterns.leakingdomain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    /**
     * Finding expected value with the same way of actual system is an antipattern. With complex algorithms, this test
     * would be a little bit complex. Algorithm would be copy-pasted here to find expected value. When there is a
     * change, it would also be copy-pasted. Therefore, we couldn't find if there is an error in new code.
     */
    @Test
    void addingTwoNumbers() {
        int value1 = 1;
        int value2 = 3;
        int expected = value1 + value2;

        int actual = Calculator.add(value1, value2);

        assertEquals(expected, actual);
    }

    /**
     * Expected value should be written directly.
     */
    @Test
    void addingTwoNumbersWithDirectExpectedValue() {
        int value1 = 1;
        int value2 = 3;

        int actual = Calculator.add(value1, value2);

        assertEquals(4, actual);
    }

    /**
     * Laeking domain knowledge could be easier with parametrized tests.
     */
    @ParameterizedTest
    @CsvSource({
            "1, 3",
            "11, 33",
    })
    void addingTwoNumbersParametrized(int value1, int value2) {
        int expected = value1 + value2;

        int actual = Calculator.add(value1, value2);

        assertEquals(expected, actual);
    }

    /**
     * Laeking domain knowledge could be easier with parametrized tests.
     */
    @ParameterizedTest
    @CsvSource({
            "1, 3, 4",
            "11, 33, 44",
    })
    void addingTwoNumbersParametrizedWithDirectExpectedValue(int value1, int value2, int expected) {
        int actual = Calculator.add(value1, value2);

        assertEquals(expected, actual);
    }
}