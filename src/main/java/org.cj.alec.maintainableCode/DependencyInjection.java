package org.cj.alec.maintainableCode;


public class DependencyInjection {
    private final Handler dispatcher = new Dispatcher();
    public HttpServletRequestHandler httpServletRequestHandler = new TopLevelHttpServletRequestHandler(dispatcher);
}
