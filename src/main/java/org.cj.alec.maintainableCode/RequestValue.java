package org.cj.alec.maintainableCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RequestValue{
    final String method;
    final String path;
    final String query;

    public RequestValue(String method, String path, String query){
        this.method = method;
        this.path = path;
        this.query = query;
    }

    @Override
    public String toString(){
        return "RequestValue{" +
                "method=" + method + ", " +
                "path=" + path + ", " +
                "query=" + query +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestValue that = (RequestValue) o;
        return Objects.equals(method, that.method) &&
                Objects.equals(path, that.path) &&
                Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() { return Objects.hash(method, path, query); }

    Optional<String> singleQueryParameter(String name){
        String[] pairs = query.split("&");
        List<String> matches = new ArrayList<>();
        for( String pair : pairs){
            String [] keys = pair.split("=");
            String key = keys[0];
            if(name.equals(key)){
                String value = keys[1];
                matches.add(value);
            }
        }
        if(matches.size() == 1){
            return Optional.of(matches.get(0));
        }else{
            return Optional.empty();
        }
    }
}
