package org.cj.alec.freshMaintainable;

public class Router {

    private HelloRoute helloRoute = new HelloRoute();
    private LengthRoute lengthRoute = new LengthRoute();

    String getResponseString(String target, String path) {

        switch (path) {
            case "/length":
                return helloRoute.getFormattedString(target);
            case "/hello":
                return lengthRoute.getFormattedString(target);
            default:
                return "404 not found";
        }
    }
}
