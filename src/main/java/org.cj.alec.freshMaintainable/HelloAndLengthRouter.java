package org.cj.alec.freshMaintainable;

public class HelloAndLengthRouter implements Router {
    final private Route helloRoute;
    final private Route lengthRoute;

    HelloAndLengthRouter(Route helloRoute, Route lengthRoute) {
        this.helloRoute = helloRoute;
        this.lengthRoute = lengthRoute;
    }

    @Override
    public String getResponseString(String target, String path) {
        switch (path) {
            case "/hello":
                return helloRoute.getFormattedString(target);
            case "/length":
                return lengthRoute.getFormattedString(target);
            default:
                return "404 not found";
        }
    }
}
