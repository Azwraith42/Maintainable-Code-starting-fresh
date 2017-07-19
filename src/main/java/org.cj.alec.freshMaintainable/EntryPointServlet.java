package org.cj.alec.freshMaintainable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EntryPointServlet extends HttpServlet {
    Handler handler;

    @Override
    public void init() throws ServletException {
        handler = new Handler();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handler.handle(req,resp);
    }


}
