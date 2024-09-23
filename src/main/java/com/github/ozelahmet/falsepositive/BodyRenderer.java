package com.github.ozelahmet.falsepositive;

public class BodyRenderer implements Renderer {
    @Override
    public String render(Message message) {
        return "<b>%s</b>".formatted(message.getBody());
    }
}
