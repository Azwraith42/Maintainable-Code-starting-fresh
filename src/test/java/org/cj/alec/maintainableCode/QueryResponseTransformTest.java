package org.cj.alec.maintainableCode;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class QueryResponseTransformTest {
    @Test
    public void responseTransformTest() throws IOException {
        //given
        ResponseValue response = new ResponseValue(HttpServletResponse.SC_OK, "Hello, world!");
        StubResponse stubResponse = new StubResponse();

        //when
        QueryResponseTransformer.transformResponse(response, stubResponse);

        //then
        assertThat(stubResponse.lastStatus, equalTo(HttpServletResponse.SC_OK));
        assertThat(new String(stubResponse.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Hello, world!"));
    }

    private class StubResponse extends HttpServletResponseNotImplemented {
        int lastStatus = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        @Override
        public void setStatus(int sc) {
            lastStatus = sc;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    byteArrayOutputStream.write(b);
                }
            };
        }
    }
}
