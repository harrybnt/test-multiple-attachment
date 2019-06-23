package com.multiple.attachment.Reproducer;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.multipart.MultipartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UploadListOfAttachment {

    @Autowired
    private ResponseUtils responseUtils;

    public void freshdeskPostRequest(RoutingContext routingContext, Vertx vertx) {

        //put path of any local file

        WebClient webClient = WebClient.create(vertx);
        MultipartForm multipartForm = MultipartForm.create()
                .attribute("description", "test-ticket-description-50")
                .attribute("subject", "subject")
                .attribute("unique_external_id", "1")
                .attribute("status", "2")
                .attribute("priority", "2")
                .binaryFileUpload("attachments[]", "Screenshot1.png", "/your local path of image", "image/png")
                .binaryFileUpload("attachments[]", "Screenshot1.png", "/Another local path of image", "image/png")
                .attribute("cc_emails[]", "abc@gmail.com")
                .attribute("cc_emails[]", "cde@gmail.com");

        webClient.postAbs("https://swiggyaid227.freshdesk.com/api/v2/tickets")
                .putHeader("Content-type", "multipart/form-data")
                .putHeader("Authorization", "Basic aGFyc2l0Lmd1cHRhQHN3aWdneS5pbjpzd2lnZ3lAMTIz")
                .sendMultipartForm(multipartForm, httpResponseAsyncResult -> {
                    if (httpResponseAsyncResult.succeeded() && httpResponseAsyncResult.result() != null) {
                        responseUtils.buildAndSendResponse(routingContext, 200, "success", httpResponseAsyncResult.result().body().toJsonObject());

                    } else {
                        responseUtils.buildAndSendResponse(routingContext, -1, "Failed", httpResponseAsyncResult.cause().getMessage());
                    }
                });
    }

}


