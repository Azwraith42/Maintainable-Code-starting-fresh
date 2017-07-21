package org.cj.alec.freshMaintainable;

final class DependencyInjector {
    private final Route helloRoute = new HelloRoute();
    private final Route lengthRoute = new LengthRoute();
    private final Router router = new HelloAndLengthRouter(helloRoute, lengthRoute);
    Handler handler = new Handler(router);
}
