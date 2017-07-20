package org.cj.alec.freshMaintainable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Handler {
    void handle(HttpServletRequest req, HttpServletResponse resp){
        UncheckedHttpServletResponse uncheckedResponse = new UncheckedHttpServletResponse(resp);

        String path = getPathFromRequest(req);
        String target = getTargetFromRequest(req);


        makeUncheckedResponse(uncheckedResponse, target, path);
    }

    private void makeUncheckedResponse(UncheckedHttpServletResponse uncheckedResponse, String target, String path) {
        if(path.equals("/length")){
            uncheckedResponse.getOutputStream().print(String.format("Length: %d", target.length()));
        }else{
            uncheckedResponse.getOutputStream().print(String.format("Hello, %s!", target));
        }
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
