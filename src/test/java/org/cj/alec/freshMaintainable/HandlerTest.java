package org.cj.alec.freshMaintainable;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandlerTest {
    @Test
    public void sayHello(){
        //given
        Handler handler = new Handler();
        HttpServletRequest request = null;
        HttpServletResponseStub response = new HttpServletResponseStub();

        //when
        handler.handle(request, response);

        //then
        assertThat(response.textThatWasWritten(), is("Hello, world!"));
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
}
