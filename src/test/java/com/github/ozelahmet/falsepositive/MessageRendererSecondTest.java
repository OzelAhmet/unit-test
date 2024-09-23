package com.github.ozelahmet.falsepositive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageRendererSecondTest {

    @Test
    void correctSubRenderersAreUsed() {
        Message message = new Message("h", "b", "f");
        MessageRenderer messageRenderer = new MessageRenderer();

        String rendered = messageRenderer.render(message);

        assertEquals("<h1>h</h1><b>b</b><i>f</i>", rendered);
    }
}