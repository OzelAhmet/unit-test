package com.github.ozelahmet.falsepositive;

public class HeaderRenderer implements Renderer {
    @Override
    public String render(Message message) {
        return "<h1>%s</h1>".formatted(message.getHeader());
    }
}
