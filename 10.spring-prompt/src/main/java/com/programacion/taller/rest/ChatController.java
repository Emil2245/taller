package com.programacion.taller.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Stream;

@RestController
public class ChatController {

    final ChatClient chatClient;
    @Value("classpath:/prompts/systemPrompt.st")
    Resource systemPrompt;

    @Value("classpath:/prompts/userPrompt.st")
    Resource userPrompt;



    public ChatController(ChatClient.Builder builder) {
      chatClient = builder
              .defaultAdvisors(new SimpleLoggerAdvisor())
              .build();
    }


    @PostMapping(value = "/chat", consumes = "application/json", produces = "application/json")
    public String chat (@RequestBody ChatRequest request){
        return chatClient.prompt()
                .system(systemSpec->
                        systemSpec.text(systemPrompt))
                .user(userSpec->
                        userSpec.text(userPrompt)
                        .param("question", request.message()))
                .call()
                .content();
    }

    @PostMapping(path = "/api/chat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ServerSentEvent<String>> streamChat(@RequestBody ChatRequest request) {
        var message = request.message();

        if (message==null ||message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }


        Flux<ServerSentEvent<String>> tokens = chatClient.prompt()
                .system(systemSpec->
                        systemSpec.text(systemPrompt))
        .user(userSpec->userSpec
                .text(userPrompt)
                .param("question", request.message())
                )
                .stream()
                .content()
                .map(
                        chunk -> ServerSentEvent.<String>builder()
                                .event("token")
                                .data(Base64.getEncoder().encodeToString(chunk.getBytes(StandardCharsets.UTF_8)))
                                .build()
                );

        Flux<ServerSentEvent<String>> done = Flux.just(
                ServerSentEvent.<String>builder()
                        .event("end")
                        .data("[DONE]")
                        .build()
        );

        return tokens.concatWith(done)
                .onErrorResume(
                        error -> Flux.just(
                                ServerSentEvent.<String>builder()
                                        .event("error")
                                        .data(error.getMessage())
                                        .build()
                        )
                );
    }

}
