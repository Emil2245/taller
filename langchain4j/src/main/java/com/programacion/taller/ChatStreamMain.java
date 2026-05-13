package com.programacion.taller;

import com.programacion.taller.utils.MyStreamingResponseHandler;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

public class ChatStreamMain {
    public static AtomicInteger count = new AtomicInteger(1);

    static void main() {

        var chatModel = OpenAiStreamingChatModel.builder()
                .apiKey("")
                .modelName("llama-3.2-1b-instruct-q8_0.gguf")
                .baseUrl("http://localhost:8080/")
                .logRequests(true)
                .logResponses(true)
                .build();
        chatModel.chat("What is the capital of France?", new MyStreamingResponseHandler());

        while (count.get() != 0) {

        }
    }
}
