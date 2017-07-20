package org.cj.alec.freshMaintainable;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryParserTest {
    @Test
    public void singleTargetReturnsValue(){
        //given
        String query = "target=world";
        String expected = "world";

        //when
        String actual = QueryParser.lookupFirstInstance("target", query);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void singleTargetMissing(){
        //given
        String query = "foo=world";
        String expected = null;

        //when
        String actual = QueryParser.lookupFirstInstance("target", query);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void handleManyThings(){
        //given
        String query = "foo=bar&target=world";
        String expected = "world";

        //when
        String actual = QueryParser.lookupFirstInstance("target", query);

        //then
        assertThat(actual, is(expected));
    }


}
