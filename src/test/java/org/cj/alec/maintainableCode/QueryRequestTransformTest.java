package org.cj.alec.maintainableCode;

import org.junit.Test;
import org.junit.runner.Request;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class QueryRequestTransformTest {

    @Test
    public void requestTransformTest(){
        //given
        RequestValue expected = new RequestValue("GET", "/hello", "target=world");
        HttpServletRequest request = new HttpServletRequestNotImplemented(){
            @Override
            public String getMethod() {
                return "GET";
            }

            @Override
            public String getRequestURI() {
                return "/hello";
            }

            @Override
            public String getQueryString() {
                return "target=world";
            }
        };

        //when
        RequestValue actual = QueryRequestTransformer.transformRequest(request);

        //then
        assertThat(actual, equalTo(expected));
    }
}
