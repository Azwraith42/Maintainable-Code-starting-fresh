package org.cj.alec.maintainableCode;

import javax.servlet.http.HttpServletRequest;

class QueryRequestTransformer{
    static RequestValue transformRequest(HttpServletRequest httpServletRequest){
        String method = httpServletRequest.getMethod();
        String uriString = httpServletRequest.getRequestURI();
        String query = httpServletRequest.getQueryString();

        RequestValue request = new RequestValue(method, uriString, query);
        return request;
    }
}