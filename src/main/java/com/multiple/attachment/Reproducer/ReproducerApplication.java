package com.multiple.attachment.Reproducer;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ReproducerApplication {


    @Autowired
    private HttpServerVerticle httpServerVerticle;

    public static void main(String[] args) {
        SpringApplication.run(ReproducerApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(httpServerVerticle);
    }
}
