package com.programacion.taller.routers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

@Component
public class FileReaderRouter extends RouteBuilder{

    @Value("${app.files.inbound:C:/tools/taller}")
    String inboundPath;

    @Override
    public void configure() throws Exception {
        String from = "file:%s?antInclude=*.pdf&delay=1000&move=procesados".formatted(inboundPath);

        from(from)
                .log("Archivo leído: ${header.CamelFileName}")
                .bean("fileProcessor")
                .bean("transformerProcessor")
                .bean("embeddingProcessor");
    }
}
