package org.cj.alec.freshMaintainable;

import java.util.HashMap;
import java.util.Map;

public class HelloAndLengthRouter implements Router {
    final private GenericRouter genericRouter;

    HelloAndLengthRouter(Route helloRoute, Route lengthRoute) {
        Map<String, Route> routeMap = new HashMap<>();
        routeMap.put("/hello", helloRoute);
        routeMap.put("/length", lengthRoute);
        genericRouter = new GenericRouter(routeMap);
    }

    @Override
    public String getResponseString(String query, String path) {
        return genericRouter.getResponseString(query, path);
    }
}
