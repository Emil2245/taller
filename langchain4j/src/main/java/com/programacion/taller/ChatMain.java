package com.programacion.taller;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatMain {
    public static ChatModel chatModel(){
        return OpenAiChatModel.builder()
                .apiKey("")
                .modelName("llama-3.2-1b-instruct-q8_0.gguf")
                .baseUrl("http://localhost:8080/")
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    static void main() {
        ChatModel chatModel = chatModel();
        var respuesta = chatModel.chat("What is the capital of France?");

        System.out.println(respuesta);
    }
}
