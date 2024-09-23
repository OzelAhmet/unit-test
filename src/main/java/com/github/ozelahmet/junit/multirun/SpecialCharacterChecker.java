package com.github.ozelahmet.junit.multirun;

import java.util.regex.Pattern;

public class SpecialCharacterChecker {

    private static final Pattern forbiddenPattern = Pattern.compile("[!@#]");

    public boolean doesNotContainForbiddenCharacters(String input) {
        return !forbiddenPattern.matcher(input).find();
    }
}
