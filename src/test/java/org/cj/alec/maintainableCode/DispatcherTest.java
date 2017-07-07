package org.cj.alec.maintainableCode;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DispatcherTest {
    @Test
    public void sayHelloWorld(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/hello", "target=world");
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_OK, "Hello, world!");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void lengthOfAlec(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/length", "target=Alec");
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_OK, "length: 4");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void badRequestIsHandled(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/xxx", "");
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_BAD_REQUEST, "Bad Request, query not found");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void doNotAllowMultipleTargets(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/hello", "target=world&target=Alec");
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_BAD_REQUEST, "Bad Request, exactly one target needed");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void emptyQueryIsABadRequest(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/hello", null);
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_BAD_REQUEST, "Bad Request, query not found");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void emptyTargetBadRequest(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/hello", "target");
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_BAD_REQUEST, "Bad Request, exactly one target needed");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void emptyTargetBadRequest2(){
        // given
        final Handler dispatcher = new Dispatcher();
        final RequestValue request = new RequestValue("GET", "/hello", "target=");
        final ResponseValue expected = new ResponseValue(HttpServletResponse.SC_BAD_REQUEST, "Bad Request, exactly one target needed");

        //when
        final ResponseValue actual = dispatcher.handle(request);

        //then
        assertThat(actual, equalTo(expected));
    }
}
