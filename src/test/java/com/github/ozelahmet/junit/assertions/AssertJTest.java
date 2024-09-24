package com.github.ozelahmet.junit.assertions;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest extends Assertions {

    @Test
    void positiveNumberIsPositive() {
        MathUtils mathUtils = new MathUtils();

        boolean positive = mathUtils.isPositive(10);

        assertThat(positive).isTrue();
    }

    /**
     * Custom failure messages can be given before assertion.
     */
    @Test
    void zeroNumberIsNotPositive() {
        MathUtils mathUtils = new MathUtils();

        boolean positive = mathUtils.isPositive(0);

        assertThat(positive).as("Zero is also non-positive").isFalse();
    }

    @Test
    void divisionByZeroReturnsNull() {
        Double result = MathUtils.div(1.0, 0.0);

        assertThat(result).isNull();
    }

    @Test
    void divideTwoNumbers() {
        Double result = MathUtils.div(4.0, 2.0);

        assertThat(result).isEqualTo(2.0);
    }

    @Test
    void addTwoNumbers() {
        Double result = MathUtils.add(0.1, 0.2);

        assertThat(result).isCloseTo(0.3, within(0.0000000000000001));
        assertThat(result).isCloseTo(0.3, withinPercentage(0.0000000000001));
    }

    /**
     * <code>doesNotContain</code> can be used to check list elements.
     */
    @Test
    void positiveIntegerArrayDoesNotContainEndNumber() {
        int[] positiveDigits = MathUtils.getPositiveIntegerArrayUntil(10);

        assertThat(positiveDigits).isNotEqualTo(10);
        assertThat(positiveDigits).hasSize(9);
        assertThat(positiveDigits).doesNotContain(10);
        assertThat(positiveDigits).doesNotContain(10, atIndex(positiveDigits.length - 1));
    }

    /**
     * Commented out line causes failure. Order is important while using <code>assertArrayEquals</code>.
     */
    @Test
    void positiveIntegerArrayCorrect() {
        int[] positiveDigits = MathUtils.getPositiveIntegerArrayUntil(10);

        assertThat(positiveDigits).isNotEmpty();
        assertThat(positiveDigits).isEqualTo(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertThat(positiveDigits).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(positiveDigits).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    void positiveIntegerArrayWithNonPositiveParameterCausesError() {
        assertThatCode(() -> MathUtils.getPositiveIntegerArrayUntil(1)).doesNotThrowAnyException();

        assertThatCode(() -> MathUtils.getPositiveIntegerArrayUntil(0))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("exclusiveEnd must be greater than 0");

        assertThatThrownBy(() -> MathUtils.getPositiveIntegerArrayUntil(0))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("greater than 0");
    }


    @Test
    void regexMatches() {
        assertThat("123").matches("^123");
    }

    @Test
    void getSameZeroAlways() {
        String zeroAsString = MathUtils.getZeroAsString();

        assertThat(zeroAsString).isSameAs(MathUtils.getZeroAsString());
        assertThat(zeroAsString).isNotSameAs(new String("0"));
    }

    @Test
    void getSameZeroAlwaysWithAssertAll() {
        String zeroAsString = MathUtils.getZeroAsString();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(zeroAsString).isSameAs(MathUtils.getZeroAsString());
        softly.assertThat(zeroAsString).isNotSameAs(new String("0"));
        softly.assertAll();
    }

    @Test
    void enoughDurationNotTimeout() {
        Future<Void> future = CompletableFuture.runAsync(() -> {
            try {
                MathUtils.timeConsumingMathOperation();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assertThat(future).succeedsWithin(Duration.ofMillis(300));
    }

    /**
     * Works preemptively.
     */
    @Test
    void notEnoughDurationTimeoutPreemptively() {
        Future<Void> future = CompletableFuture.runAsync(() -> {
            try {
                MathUtils.timeConsumingMathOperation();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assertThat(future).failsWithin(100, TimeUnit.MILLISECONDS);
    }

    @Test
    void integerCollectionIsList() {
        Collection<Integer> integerList = MathUtils.getPositiveIntegerListUntil(1);

        assertThat(integerList).isInstanceOf(List.class);
    }

    @Test
    void additionalAssertions() {
        assertThat("Text").isEqualToIgnoringCase("tEXT");

        assertThat(List.of(1, 2))
                .hasSize(2)
                .contains(1)
                .doesNotContainNull()
                .containsSequence(1, 2);

        assertThat(List.of("", "a", "aa"))
                .filteredOn(s -> s.length() > 1)
                .doesNotContain("", "a");
    }
}
