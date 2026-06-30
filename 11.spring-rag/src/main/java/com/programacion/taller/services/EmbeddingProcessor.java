package com.programacion.taller.services;


import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class EmbeddingProcessor {
    @Autowired
    VectorStore vectorStore;

    @Autowired
    EmbeddingModel embeddingModel;

    public void procesar(List<Document> documents) {
        System.out.println("EmbeddingProcessor::procesar documents: " + documents.size());

        System.out.println("EmbeddingProcessor::procesar embeddingModel: " + embeddingModel);
        System.out.println("EmbeddingProcessor::procesar vectorStore: " + vectorStore);

        vectorStore.add(documents);

    }
}
