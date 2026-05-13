package com.programacion.taller;

import com.programacion.taller.utils.AsistenteLegal;
import dev.langchain4j.service.AiServices;

public class ChatAiServiceMain {
    static void main() {
        var model = ChatMain.chatModel();
        var asistente = AiServices.create(AsistenteLegal.class, model);

        var respuesta = asistente
                .consultar("¿Cuáles son los requisitos para solicitar una visa de trabajo en Estados Unidos?");
        System.out.println(respuesta);

        System.out.println("--------------------------------------------");

        var respuesta2 = asistente.responder("¿Cuáles son los requisitos para solicitar una visa de trabajo en Estados Unidos?");
        System.out.println(respuesta2);
    }
}
