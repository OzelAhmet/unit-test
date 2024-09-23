package com.github.ozelahmet.junit.multirun;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SpecialCharacterCheckerTest {

    private final SpecialCharacterChecker checker = new SpecialCharacterChecker();


    @ParameterizedTest
    @ValueSource(strings = {
            "he!llo",
            "wo#rld",
            "123@45"
    })
    void stringDoesNotContainForbiddenCharactersValueSource(String input) {
        assertFalse(checker.doesNotContainForbiddenCharacters(input));
    }


    @ParameterizedTest
    @CsvSource({
            "hello, true",
            "he!llo, false",
            "world, true",
            "wo#rld, false",
            "12345, true",
            "123@45, false"
    })
    void stringDoesNotContainForbiddenCharactersCsvSource(String input, boolean expected) {
        assertEquals(expected, checker.doesNotContainForbiddenCharacters(input));
    }


    private static final String[] invalidStrings = {
            "he!llo",
            "wo#rld",
            "123@45"
    };

    @ParameterizedTest
    @FieldSource("invalidStrings")
    void stringDoesNotContainForbiddenCharactersFieldSource(String input) {
        assertFalse(checker.doesNotContainForbiddenCharacters(input));
    }


    @ParameterizedTest
    @MethodSource("provideInvalidStrings")
    void stringDoesNotContainForbiddenCharactersMethodSource(String input, boolean expected) {
        assertEquals(expected, checker.doesNotContainForbiddenCharacters(input));
    }

    private static Stream<Arguments> provideInvalidStrings() {
        return Stream.of(
                Arguments.of("hello", true),
                Arguments.of("he!llo", false),
                Arguments.of("world", true),
                Arguments.of("wo#rld", false),
                Arguments.of("12345", true),
                Arguments.of("123@45", false)
        );
    }

}