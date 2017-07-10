package org.cj.alec.maintainableCode;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Optional;

class Dispatcher implements Handler {
    private Map<String, Function<Optional<String>, ResponseValue>> commandMap;

    Dispatcher(){
        commandMap = new HashMap<>();
        commandMap.put("/hello", this::sayHello);
        commandMap.put("/length", this::displayLength);
    }

    private Optional<Function<Optional<String>, ResponseValue>> lookupRouteHandler(String path) {
        return Optional.ofNullable(commandMap.get(path));
    }

    @Override
    public ResponseValue handle(RequestValue request){
        return lookupRouteHandler(request.path)
            .map(f -> f.apply(request.singleQueryParameter("target")))
            .orElse(notFound("URI not found"));
    }

    private ResponseValue displayLength(Optional<String> maybeTarget) {
        return maybeTarget
                .map(target -> ResponseValue.plainText(HttpServletResponse.SC_OK, String.format("length: %d", target.length())))
                .orElse(badRequest("exactly one target param expected"));
    }

    private ResponseValue sayHello(Optional<String> maybeTarget) {
        return maybeTarget
                .map(target -> ResponseValue.plainText(HttpServletResponse.SC_OK, String.format("Hello, %s!", target)))
                .orElse(badRequest("exactly one target param expected"));
    }

    private ResponseValue badRequest(String e){
        return ResponseValue.plainText(HttpServletResponse.SC_BAD_REQUEST, String.format("Bad Request, %s", e));
    }

    private ResponseValue notFound(String e){
        ResponseValue response = ResponseValue.plainText(HttpServletResponse.SC_NOT_FOUND, String.format("Not found: %s", e));
        return response;
    }
}


