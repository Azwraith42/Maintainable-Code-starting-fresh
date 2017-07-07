package org.cj.alec.maintainableCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpServletRequestHandler {
    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
