package org.cj.alec.freshMaintainable;

public class HelloRoute implements Route {
    @Override
    public String getFormattedString(String target) {
        return String.format("Length: %d", target.length());
    }
}
