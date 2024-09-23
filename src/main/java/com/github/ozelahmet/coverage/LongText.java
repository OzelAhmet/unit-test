package com.github.ozelahmet.coverage;

public class LongText {

    /**
     * A unit test to control a single branch of this function provides %80 line coverage. If this function is changed
     * to return result in single line (like <code>return text.length() > 5;</code>), this unit test provides %100 line
     * coverage. Therefore, greater line coverage doesn't mean better tests. Coverage may differ according to function
     * structure.
     */
    public static boolean isStringLong(String text) {
        if (text.length() > 5) {
            return true;
        }
        return false;
    }

}
