package com.programacion.taller.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;

public class SimilitudMain {
    static void main() {
        AllMiniLmL6V2EmbeddingModel model = new AllMiniLmL6V2EmbeddingModel();

        Embedding e1 = model.embed("cual seria el area de un circulo de radio 8?").content();
        Embedding e2 = model.embed("Obtienela fecha y hora actual").content();
        Embedding e3 = model.embed("Obtiene el area de un circulo, recibe un radio").content();

        double sim12 = CosineSimilarity.between(e1, e2);
        double sim13 = CosineSimilarity.between(e1, e3);

        System.out.println("Similitud entre e1 y e2: " + sim12);
        System.out.println("Similitud entre e1 y e3: " + sim13);

    }
}
