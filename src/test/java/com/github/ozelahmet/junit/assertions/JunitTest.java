package com.github.ozelahmet.junit.assertions;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JunitTest {


    /**
     * A unit test usually consists of 3 sections: Arrange, Act & Assert. Given, When & Then can also be used to name
     * these sections. We don't need to write them as comments until we need to show them explicitly. Empty lines
     * between these sections are enough to separate them.
     */
    @Test
    void positiveNumberIsPositive() {
        // Arrange
        MathUtils mathUtils = new MathUtils();

        // Act
        boolean positive = mathUtils.isPositive(10);

        // Assert
        assertTrue(positive);
    }

    /**
     * Suppliers can also be used as <code>assertBoolean</code> parameters.
     */
    @Test
    void negativeNumberIsNotPositive() {
        MathUtils mathUtils = new MathUtils();

        assertFalse(() -> mathUtils.isPositive(-10));
    }

    /**
     * Custom failure messages can be given.
     */
    @Test
    void zeroNumberIsNotPositive() {
        MathUtils mathUtils = new MathUtils();

        boolean positive = mathUtils.isPositive(0);

        assertFalse(positive, "Zero is also non-positive");
    }

    @Test
    void divisionByZeroReturnsNull() {
        Double result = MathUtils.div(1.0, 0.0);

        assertNull(result);
    }

    @Test
    void divideTwoNumbers() {
        Double result = MathUtils.div(4.0, 2.0);

        assertEquals(2.0, result);
    }

    @Test
    void addTwoNumbers() {
        double result = MathUtils.add(0.1, 0.2);
        assertEquals(0.3, result, 0.0000000000000001);
    }

    @Test
    void positiveIntegerArrayDoesNotContainEndNumber() {
        int[] positiveDigits = MathUtils.getPositiveIntegerArrayUntil(10);

        assertNotEquals(10, positiveDigits.length);
        assertNotEquals(10, positiveDigits[positiveDigits.length - 1]);
    }

    /**
     * Commented out line causes failure. Order is important while using <code>assertArrayEquals</code>.
     */
    @Test
    void positiveIntegerArrayCorrect() {
        int[] positiveDigits = MathUtils.getPositiveIntegerArrayUntil(10);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, positiveDigits);
        // assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, positiveDigits);
    }

    /**
     * Commented out line causes failure. Order is important while using <code>assertIterableEquals</code>.
     */
    @Test
    void positiveIntegerListCorrect() {
        Collection<Integer> positiveDigits = MathUtils.getPositiveIntegerListUntil(10);

        assertIterableEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), positiveDigits);
        // assertIterableEquals(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1), positiveDigits);
    }

    @Test
    void positiveIntegerArrayWithNonPositiveParameterCausesError() {
        assertDoesNotThrow(() -> MathUtils.getPositiveIntegerArrayUntil(1));

        assertThrows(RuntimeException.class, () -> MathUtils.getPositiveIntegerArrayUntil(0));
        assertThrowsExactly(IllegalArgumentException.class, () -> MathUtils.getPositiveIntegerArrayUntil(0));
    }

    @Test
    void positiveIntegerStringsAreCorrect() {
        List<String> positiveDigits = MathUtils.getPositiveIntegerStringsUntil(3);

        assertLinesMatch(List.of("Ints", "^-?\\d+$", "2"), positiveDigits);
    }

    /**
     * <code>assertLinesMatch</code> method test strings for equality first. Match control fails in the first line but
     * not in the second line.
     */
    @Test
    void equalRegexPatternsNotFailWithAssertLinesMatch() {
        assertFalse("^123".matches("^123"));

        var actual = Stream.of("^123");
        assertLinesMatch(Stream.of("^123"), actual);
    }

    @Test
    void getSameZeroAlways() {
        String zeroAsString = MathUtils.getZeroAsString();

        assertSame(MathUtils.getZeroAsString(), zeroAsString);
        assertNotSame(new String("0"), zeroAsString);
    }

    /**
     * In above test, if first assertion is failed, second assertion is skipped. In this test, both of the assertions
     * are checked with <code>assertAll</code> and all failures are reported together.
     */
    @Test
    void getSameZeroAlwaysWithAssertAll() {
        String zeroAsString = MathUtils.getZeroAsString();

        assertAll("getSameZeroAlways Asserts",
                () -> assertSame(MathUtils.getZeroAsString(), zeroAsString),
                () -> assertNotSame(new String("0"), zeroAsString)
        );
    }

    @Test
    void getDifferentOneAlways() {
        assertNotSame(MathUtils.getOneAsString(), MathUtils.getOneAsString());
        assertNotSame(new String("0"), MathUtils.getZeroAsString());
    }

    @Test
    void enoughDurationNotTimeout() {
        assertTimeout(Duration.ofMillis(300), MathUtils::timeConsumingMathOperation);
    }

    /**
     * With <code>assertTimeout</code>, we can see the execution duration.
     */
    @Test
    void notEnoughDurationTimeout() {
        assertTimeout(Duration.ofMillis(100), MathUtils::timeConsumingMathOperation);
    }

    /**
     * When <code>assertTimeoutPreemptively</code> is used, execution of the method under test is aborted when timeout
     * is exceeded.
     */
    @Test
    void notEnoughDurationTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofMillis(100), MathUtils::timeConsumingMathOperation);
    }

    @Test
    void integerCollectionIsList() {
        Collection<Integer> integerList = MathUtils.getPositiveIntegerListUntil(1);

        assertInstanceOf(List.class, integerList);
    }
}
