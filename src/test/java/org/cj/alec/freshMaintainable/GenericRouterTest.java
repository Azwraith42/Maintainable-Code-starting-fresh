package org.cj.alec.freshMaintainable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GenericRouterTest {
    @Test
    public void matchingRoute(){
        // given
        String target = "some target";
        String path = "foo";
        Map<String, Route> routeMap = createRouteMap("foo", "foo route response");
        GenericRouter genericRouter = new GenericRouter(routeMap);
        // when
        String responseText = genericRouter.getResponseString(target, path);
        // then
        assertThat(responseText, is("foo route response with some target"));
    }

    @Test
    public void notMatchingRoute(){
        // given
        String target = "some target";
        String path = "bad route";
        Map<String, Route> routeMap = createRouteMap("foo", "foo route response");
        GenericRouter genericRouter = new GenericRouter(routeMap);
        // when
        String responseText = genericRouter.getResponseString(target, path);
        // then
        assertThat(responseText, is("unknown route 'bad route'"));
    }

    private Map<String, Route> createRouteMap(String key, String text){
        Map<String, Route> map = new HashMap<>();
        Route value = new SimpleRoute(text);
        map.put(key, value);
        return map;
    }

    class SimpleRoute implements Route{
        final String theText;

        SimpleRoute(String theText) {
            this.theText = theText;
        }

        @Override
        public String getFormattedString(String target) {
            return String.format("%s with %s", theText, target);
        }
    }
}
