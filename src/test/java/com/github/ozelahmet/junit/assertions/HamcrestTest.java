package com.github.ozelahmet.junit.assertions;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestTest {

    @Test
    void assertions() {
        assertThat(5, greaterThan(4));
        assertThat(5.1, closeTo(5, 0.1));

        assertThat("Text", equalToIgnoringCase("tEXT"));
        assertThat("Text", allOf(
                startsWith("Te"),
                endsWith("xt")
        ));

        assertThat(List.class, typeCompatibleWith(Collection.class));

        assertThat(List.of(), empty());

        assertThat(List.of(1, 2), hasSize(2));
        assertThat(List.of(1, 2), containsInAnyOrder(2, 1));

        assertThat("a", isOneOf("a", "b"));

        assertThat(Map.of("key", "value"), hasKey("key"));
        assertThat(Map.of("key", "value"), hasEntry("key", "value"));

    }

}
