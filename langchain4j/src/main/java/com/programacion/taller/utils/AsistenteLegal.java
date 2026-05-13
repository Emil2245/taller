package com.programacion.taller.utils;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

//@SystemMessage("Eres un asistente legal en Ecuador")
public interface AsistenteLegal {
    String responder(String pregunta);

//    @SystemMessage("Eres un asistente legal en Ecuador")
//    @UserMessage("Responde a la pregunta utilizando argumentos legales {{pregunta}}")
//    String consultar(@V("pregunta") String pregunta);
    @SystemMessage("Eres un asistente legal en temas de educacion superior")
    @UserMessage("""
            Responde a la pregunta: {{pregunta}}
            si no tienes una respuesta utiliza la seccion de DATOS que se incluye a continuacion
            
            DATOS:
            ----------------------
            **Requisitos espec�ficos para la visa de trabajo:**
            
            1. **Contrato de empleo:** Debes tener un contrato de empleo con tu empleador en Estados Unidos, que lo vincule a la ciudad de habitation.
            2. **Tasa de empleo:** Debes cumplir con la tasa de empleo para tu posici�n de trabajo.
            3. **Rango de empleo:** Debes tener un rango de empleo que lo vincule a una posici�n de trabajo.
            4. **Estancia:** Debes tener una estancia de tiempo determinado en Estados Unidos.
            5. **Salario:** Debes demostrar que tienes un salario adecuado para tu posici�n de trabajo.
            6. **Contrato de trabajo:** Debes tener un contrato de trabajo que lo vincule a la ciudad de habici�n y a una posici�n de trabajo.
            7. **Certificado de no violaci�n de la ley:** Debes obtener un certificado de no violaci�n de la ley de la immigration de Estados Unidos.
            
            {{TEXTO}}
            ---------------
            """)
    String consultar(@V("pregunta") String pregunta);
}
