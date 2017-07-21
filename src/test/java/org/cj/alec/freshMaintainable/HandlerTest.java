package org.cj.alec.freshMaintainable;

import org.junit.Test;

import javax.servlet.ServletOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandlerTest {

    @Test
    public void someQueryAndSomePathMakeAResponse(){
        //given
        RouterStub routerStub = new RouterStub();
        String path = "some path";
        String query = "target=someQuery";
        HttpServletRequestStub requestStub = new HttpServletRequestStub(path, query);
        HttpServletResponseStub responseStub = new HttpServletResponseStub();
        Handler handler = new Handler(routerStub);

        //when
        handler.handle(requestStub, responseStub);

        assertThat(responseStub.textThatWasWritten(), is("some path, someQuery!"));
    }

    class HttpServletResponseStub extends HttpServletResponseNotImplemented {
        ServletOutputStreamStub servletOutputStreamStub = new ServletOutputStreamStub();

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return servletOutputStreamStub;
        }

        String textThatWasWritten(){
            return servletOutputStreamStub.toTextUtf8();
        }
    }

    class ServletOutputStreamStub extends ServletOutputStream{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        @Override
        public void write(int b) throws IOException {
            byteArrayOutputStream.write(b);
        }

        String toTextUtf8(){
            byte[] bytes = byteArrayOutputStream.toByteArray();
            Charset charset = StandardCharsets.UTF_8;
            String text = new String(bytes, charset);
            return text;
        }
    }

    class HttpServletRequestStub extends HttpServletRequestNotImplemented {
        final String queryString;
        final String uri;

        HttpServletRequestStub(String uri, String queryString) {
            this.queryString = queryString;
            this.uri = uri;
        }

        @Override
        public String getQueryString() {
            return queryString;
        }

        @Override
        public String getRequestURI() {
            return uri;
        }
    }

    class RouterStub implements Router {
        @Override
        public String getResponseString(String target, String path) {
            return String.format("%s, %s!",path, target);
        }
    }

}
