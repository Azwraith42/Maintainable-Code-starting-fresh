package org.cj.alec.freshMaintainable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Handler {
    private final Router router;

    Handler(Router router) {
        this.router = router;
    }

    void handle(HttpServletRequest req, HttpServletResponse resp){
        UncheckedHttpServletResponse uncheckedResponse = new UncheckedHttpServletResponse(resp);
        String path = getPathFromRequest(req);
        String target = getTargetFromRequest(req);
        String responseString = router.getResponseString(target, path);
        setUncheckedResponse(uncheckedResponse, responseString);
    }

    private void setUncheckedResponse(UncheckedHttpServletResponse uncheckedResponse, String responseString) {
        uncheckedResponse.getOutputStream().print(responseString);
    }

    private String getPathFromRequest(HttpServletRequest req) {
        String path = req.getRequestURI();
        return path;
    }


    private String getTargetFromRequest(HttpServletRequest req) {
        String target = getTargetFromQuery(req.getQueryString());
        return target;
    }

    private String getTargetFromQuery(String queryString) {
        return QueryParser.lookupFirstInstance("target", queryString);
    }
}
