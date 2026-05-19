package com.programacion.taller;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class ChatMain {
    public static Dotenv dotenv = Dotenv.load();

    // Leer variables
    public static final String API_KEY = dotenv.get("API_KEY");
    public static ChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName("llama-3.3-70b-versatile") // Modelo disponible en Groq
                .baseUrl("https://api.groq.com/openai/v1/")  // Base URL de Groq
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    public static void main(String[] args) {
        ChatModel chatModel = chatModel();
        var respuesta = chatModel.chat("What is the capital of France?");
        System.out.println(respuesta);
    }
}