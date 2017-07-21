package org.cj.alec.freshMaintainable;

import java.util.Map;

public class GenericRouter implements Router {
    private final Map<String, Route> routeMap;

    GenericRouter(Map<String, Route> routeMap) {
        this.routeMap = routeMap;
    }

    @Override
    public String getResponseString(String query, String path) {
        Route defaultRoute = unused -> String.format("unknown route '%s'", path);
        return routeMap.getOrDefault(path, defaultRoute).getFormattedString(query);
    }
}
