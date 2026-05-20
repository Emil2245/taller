package com.programacion.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

import java.nio.file.Paths;

public class EmbeddingModel {
    static void main() {
//        var model = ChatMain.chatModel();

        var pathToModel = Paths.get("C:/Users/fing.labcom/Downloads/model.onnx");
        var pathToTokenizer = Paths.get("C:/Users/fing.labcom/Downloads/tokenizer.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel,pathToTokenizer, PoolingMode.MEAN);

        Response<Embedding> response = model.embed("Hola, ¿cómo estás?");

        Embedding embedding = response.content();

        System.out.println(embedding);
        System.out.println("dimension: " + embedding.vector().length);
        System.out.println(embedding.vectorAsList());
    }
}
