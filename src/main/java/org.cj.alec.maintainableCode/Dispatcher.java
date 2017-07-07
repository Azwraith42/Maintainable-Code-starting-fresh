package org.cj.alec.maintainableCode;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Optional;

class Dispatcher implements Handler {
    private Map<String, Function<String, ResponseValue>> commandMap;

    Dispatcher(){
        commandMap = new HashMap<>();
        commandMap.put("/hello", this::sayHello);
        commandMap.put("/length", this::displayLength);
    }

    @Override
    public ResponseValue handle(RequestValue request){
        final Function<String, ResponseValue> badRequest = this::badRequest;

        Function<String, ResponseValue> command = commandMap.getOrDefault(request.path, badRequest);
        final ResponseValue result;
        if(command != badRequest && request.query != null){
            Optional<String> target = request.singleQueryParameter("target");
            if(target.isPresent()){
                result = command.apply(target.get());
            }else{
                command = badRequest;
                result = command.apply("exactly one target needed");
            }
        }else{
            command = badRequest;
            result = command.apply("query not found");
        }
        return result;
    }



    private ResponseValue displayLength(String target) {
        ResponseValue response = ResponseValue.plainText(HttpServletResponse.SC_OK, String.format("length: %d", target.length()));
        return response;
    }

    private ResponseValue sayHello(String target) {
        ResponseValue response = ResponseValue.plainText(HttpServletResponse.SC_OK, String.format("Hello, %s!", target));
        return response;
    }

    private ResponseValue badRequest(String target){
        ResponseValue response = ResponseValue.plainText(HttpServletResponse.SC_BAD_REQUEST, String.format("Bad Request, %s", target));
        return response;
    }
}