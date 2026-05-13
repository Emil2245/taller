package com.programacion.taller;

import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public class ChatStructuredOutputMain {
    record Persona(String nombre, int edad,String ciudad)
    {
    }
    interface Extractor {
        @UserMessage("Extrae la infromacion de: {{texto}} ")
        Persona extraerPersona(@V("texto") String texto);

    }
    static void main() {
        var chatModel = ChatMain.chatModel();

        Extractor extractor = AiServices.builder(Extractor.class)
                .chatModel(chatModel)
                .build();

       Persona p = extractor.extraerPersona("Soy Maria, tengo 30 años y vivo en Quito");
       System.out.println(p);

    }
}
