package com.programacion.taller;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.time.LocalDateTime;

class Herramientas {
    @Tool("Obtienela fecha y hora actual")
    String obtenerFechaHora() {
        return LocalDateTime.now().toString();
    }

    @Tool("Obtiene el area de un circulo, recibe un radio")
    double obtenerAreaCirculo(@P("radio") double radio) {
        return Math.PI * radio * radio;
    }
}

interface AsistenteConTools {
    @SystemMessage("Eres un asistente que puede utilizar herramientas para responder preguntas")
    String chat(String mensaje);
}

public class FunctionCallingMain {
    static void main() {
        var chatModel = ChatMain.chatModel();

        var service = AiServices.builder(AsistenteConTools.class)
                .chatModel(chatModel)
                .tools(new Herramientas())
                .build();

//        var respuesta = service.chat("que hora es?");
        var respuesta = service.chat("cual seria el area de un circulo de radio 8?");
        System.out.println(respuesta);
    }
}
