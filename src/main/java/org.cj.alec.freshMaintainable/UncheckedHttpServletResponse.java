package org.cj.alec.freshMaintainable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UncheckedHttpServletResponse {
    final HttpServletResponse delegateToMe;

    public UncheckedHttpServletResponse(HttpServletResponse delegateToMe) {
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
