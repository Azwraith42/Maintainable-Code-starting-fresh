package org.cj.alec.maintainableCode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class QueryResponseTransformer {
    static void transformResponse(ResponseValue response, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(response.statusCode);
        httpServletResponse.getOutputStream().print(response.body);
    }
}