package org.cj.alec.freshMaintainable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EntryPointServlet extends HttpServlet {
    private DependencyInjector dependencyInjector;

    @Override
    public void init() throws ServletException {
        dependencyInjector = new DependencyInjector();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dependencyInjector.handler.handle(req,resp);
    }

}
