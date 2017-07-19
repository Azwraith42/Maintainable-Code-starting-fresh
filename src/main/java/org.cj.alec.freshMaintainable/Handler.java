package org.cj.alec.freshMaintainable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Handler {
    void handle(HttpServletRequest req, HttpServletResponse resp){
        UncheckedHttpServletResponse uncheckedResponse = new UncheckedHttpServletResponse(resp);
        uncheckedResponse.getOutputStream().print("Hello, world!");
    }
}
