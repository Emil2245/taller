package com.programacion.taller.services;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransformerProcessor {
    List<Document> procesar(List<Document> documents) {

        TokenTextSplitter splitter = TokenTextSplitter.builder().build();

        List<Document> splitted = splitter.split(documents);

        System.out.println("Documentos divididos: " + splitted.size());

        return splitted;
    }
}
