package com.github.ozelahmet.falsepositive;

import java.util.List;
import java.util.stream.Collectors;

public class MessageRenderer implements Renderer {

    public List<Renderer> subRenderers;

    public MessageRenderer() {
        this.subRenderers = List.of(
                new HeaderRenderer(),
                new BodyRenderer(),
                new FooterRenderer()
        );
    }

    public List<Renderer> getSubRenderers() {
        return subRenderers;
    }

    @Override
    public String render(Message message) {
        return this.subRenderers.stream()
                .map(renderer -> renderer.render(message))
                .collect(Collectors.joining());
    }
}
