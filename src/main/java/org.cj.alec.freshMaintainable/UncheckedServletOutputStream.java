package org.cj.alec.freshMaintainable;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

class UncheckedServletOutputStream {
    private final ServletOutputStream delegateToMe;

    UncheckedServletOutputStream(ServletOutputStream delegateToMe) {
        this.delegateToMe = delegateToMe;
    }
    void print(String s)  {
        try {
            delegateToMe.print(s);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
