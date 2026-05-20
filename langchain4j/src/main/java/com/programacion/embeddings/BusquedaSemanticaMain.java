package com.programacion.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.util.List;

public class BusquedaSemanticaMain {
    static void main() {
        var documentos = List.of(
                "Java is an object-oriented programming language",
                "Python is popular for artificial intelligence",
                "Embeddings represent text as vectors",
                "The coffe machine is in the kitchen"
        );

        var model = new AllMiniLmL6V2EmbeddingModel();

        var embeddings = documentos.stream()
                .map(model::embed)
                .map(Response::content)
                .toList();

//        String consulta = "numerical vectors";
        String consulta = "Object Oriented Programming";
        Embedding query =model.embed(consulta).content();

        var index = 0;
        double mejorScore = -1.0;
        var mejorIndice = 0;

        for (var it : embeddings) {
            double score=CosineSimilarity.between(query, it);

            System.out.printf("Documento %d - Similitud: %.2f%n", index, score);

            if(mejorScore<score) {
                mejorScore=score;
                mejorIndice = index;
            }
            index++;
        }

        System.out.println("--------------------------------------------");
        System.out.println("Documento mas relevante: " + documentos.get(mejorIndice));
        System.out.println("Similitud: " + mejorScore);

    }
}
