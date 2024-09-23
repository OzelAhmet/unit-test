package com.github.ozelahmet.junit.multirun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomNumberGeneratorTest {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @BeforeEach
    void setUp() {
        System.out.println("Runs before each repetation");
    }

    @RepeatedTest(value = 10, failureThreshold = 1)
    void randomNumberInRange(RepetitionInfo repetitionInfo) {
        int min = 1;
        int max = 10;

        int result = randomNumberGenerator.generateBetween(min, max);

        System.out.println("Repetition " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        assertTrue(result >= min && result < max, "Generated number should be within the range");
    }
}