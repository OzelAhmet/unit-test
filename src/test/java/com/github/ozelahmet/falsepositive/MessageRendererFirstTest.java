package com.github.ozelahmet.falsepositive;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageRendererFirstTest {

    @Test
    void correctSubRenderersAreUsed() {
        MessageRenderer messageRenderer = new MessageRenderer();

        List<Renderer> subRenderers = messageRenderer.getSubRenderers();

        assertEquals(3, subRenderers.size());
        assertInstanceOf(HeaderRenderer.class, subRenderers.getFirst());
        assertInstanceOf(BodyRenderer.class, subRenderers.get(1));
        assertInstanceOf(FooterRenderer.class, subRenderers.get(2));
    }
}