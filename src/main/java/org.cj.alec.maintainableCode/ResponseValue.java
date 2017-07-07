package org.cj.alec.maintainableCode;

import java.util.Objects;

class ResponseValue{

    final int statusCode;
    final String body;

    ResponseValue(int statusCode, String body){
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseValue{" +
                "statusCode=" + statusCode + ", " +
                "body=" + body +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ResponseValue that = (ResponseValue) o;
        return statusCode == that.statusCode &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() { return Objects.hash(statusCode, body); }

    static ResponseValue plainText(int statusCode, String body){
        ResponseValue response = new ResponseValue(statusCode, body);
        return response;
    }
}
