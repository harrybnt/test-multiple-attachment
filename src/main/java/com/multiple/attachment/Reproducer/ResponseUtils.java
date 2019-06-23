package com.multiple.attachment.Reproducer;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

    public void buildAndSendResponse(RoutingContext routingContext, Integer statusCode, String statusMessage, Object object) {
        routingContext
                .response()
                .putHeader("Content-type", "application/json")
                .end(Json.encodePrettily(new JsonObject()
                        .put("statusCode", statusCode)
                        .put("statusMessage", statusMessage)
                        .put("data", object)));
    }
}
