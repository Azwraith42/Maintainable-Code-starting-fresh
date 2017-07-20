package org.cj.alec.freshMaintainable;

public class LengthRoute implements Route {
    @Override
    public String getFormattedString(String target) {
        return String.format("Hello, %s!", target);
    }
}
