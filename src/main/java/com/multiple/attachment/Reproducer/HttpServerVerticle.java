package com.multiple.attachment.Reproducer;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HttpServerVerticle extends AbstractVerticle {

    @Autowired
    private UploadListOfAttachment uploadListOfAttachment;

    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8010);

        router.get("/api/v1/test/multiple-upload").handler(routingContext -> {
            uploadListOfAttachment.freshdeskPostRequest(routingContext, vertx);
        });
    }
}
