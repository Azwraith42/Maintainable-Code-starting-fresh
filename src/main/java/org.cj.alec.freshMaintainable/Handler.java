package org.cj.alec.freshMaintainable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Handler {
    void handle(HttpServletRequest req, HttpServletResponse resp){
        UncheckedHttpServletResponse uncheckedResponse = new UncheckedHttpServletResponse(resp);

        String target = getTargetFromRequest(req);

        uncheckedResponse.getOutputStream().print(String.format("Hello, %s!", target));
    }

    private String getTargetFromRequest(HttpServletRequest req) {

        String target = getTargetFromQuery(req.getQueryString());

        return target;
    }

    private String getTargetFromQuery(String queryString) {
        String[] queryParts = queryString.split("=");
        String target = queryParts[1];
        return target;
    }
}
