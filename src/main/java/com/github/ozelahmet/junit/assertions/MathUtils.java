package com.github.ozelahmet.junit.assertions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MathUtils {

    public boolean isPositive(int number) {
        return number > 0;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static Double div(Double a, Double b) {
        if (b == 0) {
            return null;
        }
        return a / b;
    }

    public static int[] getPositiveIntegerArrayUntil(int exclusiveEnd) {
        if (exclusiveEnd < 1) {
            throw new IllegalArgumentException("exclusiveEnd must be greater than 0");
        }
        return IntStream.range(1, exclusiveEnd).toArray();
    }

    public static Collection<Integer> getPositiveIntegerListUntil(int exclusiveEnd) {
        return Arrays.stream(getPositiveIntegerArrayUntil(exclusiveEnd)).boxed().toList();
    }

    // assertLinesMatch

    public static List<String> getPositiveIntegerStringsUntil(int exclusiveEnd) {
        String firstLine = "Ints";
        Collection<Integer> integerList = getPositiveIntegerListUntil(exclusiveEnd);
        List<String> stringList = integerList.stream().map(String::valueOf).toList();
        return Stream.concat(Stream.of(firstLine), stringList.stream()).toList();
    }

    // assertSame

    private static final String ZERO = "0";

    public static String getZeroAsString() {
        return ZERO;
    }

    public static String getOneAsString() {
        return String.valueOf(1);
    }

    // assertTimeout

    public static void timeConsumingMathOperation() throws InterruptedException {
        System.out.println("process started");
        Thread.sleep(200);
        System.out.println("process completed");
    }

}
