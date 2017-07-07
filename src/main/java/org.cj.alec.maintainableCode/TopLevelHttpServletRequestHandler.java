package org.cj.alec.maintainableCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopLevelHttpServletRequestHandler implements HttpServletRequestHandler {
    private final Handler dispatcher;

    TopLevelHttpServletRequestHandler(Handler dispatcher){ this.dispatcher = dispatcher; }


    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // request
        RequestValue request = QueryRequestTransformer.transformRequest(httpServletRequest);

        // handle
        ResponseValue response = dispatcher.handle(request);

        // response
        QueryResponseTransformer.transformResponse(response, httpServletResponse);

    }
}
