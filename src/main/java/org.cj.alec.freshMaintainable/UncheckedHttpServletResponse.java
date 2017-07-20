package org.cj.alec.freshMaintainable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class UncheckedHttpServletResponse {
    private final HttpServletResponse delegateToMe;

    UncheckedHttpServletResponse(HttpServletResponse delegateToMe) {
        this.delegateToMe = delegateToMe;
    }

    UncheckedServletOutputStream getOutputStream() {
        try {
            return new UncheckedServletOutputStream(delegateToMe.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
