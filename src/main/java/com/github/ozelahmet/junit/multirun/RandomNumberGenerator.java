package com.github.ozelahmet.junit.multirun;

import java.util.Random;

public class RandomNumberGenerator {
    private final Random random = new Random();

    public int generateBetween(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return random.nextInt(max - min) + min;
    }
}
