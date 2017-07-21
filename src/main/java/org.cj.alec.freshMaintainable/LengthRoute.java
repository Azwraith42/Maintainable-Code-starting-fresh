package org.cj.alec.freshMaintainable;

public class LengthRoute implements Route {
    @Override
    public String getFormattedString(String query) {
        String target = QueryParser.lookupFirstInstance("target", query);
        return String.format("Hello, %s!", target);
    }
}
