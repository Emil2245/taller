package com.programacion.taller.utils;

import com.programacion.taller.ChatStreamMain;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;

public class MyStreamingResponseHandler implements StreamingChatResponseHandler {
    @Override
    public void onPartialResponse(String partialResponse) {
//        StreamingChatResponseHandler.super.onPartialResponse(partialResponse);
        System.out.print(partialResponse);
        System.out.flush();
    }


    @Override
    public void onCompleteResponse(ChatResponse completeResponse) {
        System.out.println();
        System.out.println("[GENERACION COMPLETA]");
        ChatStreamMain.count.getAndDecrement();
    }

    @Override
    public void onError(Throwable error) {
        error.printStackTrace();

    }
}
