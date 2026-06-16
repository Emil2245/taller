package com.programacion.taller.routers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

@Component
public class FileReaderRouter extends RouteBuilder{

    @Value("${app.files.inbound:/tallerIII/taller/11.spring-rag/src/main/resources}")
    String inboundPath;

    @Override
    public void configure() throws Exception {
        String from = "file:%s?antInclude=*.pdf&delay=1000&move=procesados".formatted(inboundPath);

        from("file:input?noop=true")
                .log("Archivo leído: ${header.CamelFileName}")
                .bean("fileProcessor")
                .bean("transformerProcessor")
                .bean("embeddingProcessor");
    }
}
