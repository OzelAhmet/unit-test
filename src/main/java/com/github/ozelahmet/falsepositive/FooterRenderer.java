package com.github.ozelahmet.falsepositive;

public class FooterRenderer implements Renderer {
    @Override
    public String render(Message message) {
        return "<i>%s</i>".formatted(message.getFooter());
    }
}
