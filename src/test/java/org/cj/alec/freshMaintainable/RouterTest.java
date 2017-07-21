package org.cj.alec.freshMaintainable;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RouterTest {
    @Test
    public void getResponseFromHelloRoute(){
        //given
        HelloAndLengthRouter router = new HelloAndLengthRouter(new TestHello("hello response"), new TestLength());
        String target = "world";
        String path = "/hello";

        //when
        String actual = router.getResponseString(target, path);

        //then
        assertThat(actual, is("hello response"));
    }

    @Test
    public void getResponseFromLengthRoute(){
        //given
        HelloAndLengthRouter router = new HelloAndLengthRouter(new TestHello("hello response"), new TestLength());
        String target = "world";
        String path = "/length";

        //when
        String actual = router.getResponseString(target, path);

        //then
        assertThat(actual, is(""+target.length()));
    }

    @Test
    public void badPathGives404(){
        //given
        HelloAndLengthRouter router = new HelloAndLengthRouter(new TestHello("hello response"), new TestLength());
        String target = "some target";
        String path = "bad path";

        //when
        String actual = router.getResponseString(target, path);

        //then
        assertThat(actual, is("404 not found"));
    }

    class TestHello implements Route{
        final String formattedString;

        public TestHello(String formattedString) {
            this.formattedString = formattedString;
        }

        @Override
        public String getFormattedString(String target) {
            return formattedString;
        }
    }

    class TestLength implements Route{
        @Override
        public String getFormattedString(String target) {
            return "" + target.length();
        }
    }
}
