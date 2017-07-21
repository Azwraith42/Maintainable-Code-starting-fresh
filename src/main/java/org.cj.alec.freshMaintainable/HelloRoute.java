package org.cj.alec.freshMaintainable;

public class HelloRoute implements Route {
    @Override
    public String getFormattedString(String query) {
        String target = QueryParser.lookupFirstInstance("target", query);
        return String.format("Length: %d", target.length());
    }
}
