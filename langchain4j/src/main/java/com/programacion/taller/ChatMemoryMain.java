package com.programacion.taller;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

public class ChatMemoryMain {
    interface Conversador{
        String chat(String mensaje);
    }
    static void main() {
        var model = ChatMain.chatModel();

        ChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        var bot =AiServices.builder(Conversador.class)
                .chatMemory(memory)
                .chatModel(model)
                .build();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("mensaje: ");
            String mensaje = scanner.nextLine();

            if ("/exit".equalsIgnoreCase(mensaje)) {
                break;
            }
        var respuesta = bot.chat(mensaje);
        System.out.println("respuesta: " + respuesta);
        }

    }
}
