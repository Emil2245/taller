package com.programacion.taller;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

public class TokkitTokenizer {
    static void main() {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_003);
        var text = "Hello, do you like tea? In the sunlit terraces of some";
//        var text = "Hello, do you like tea? <|endoftext|> In the sunlit terraces of some";

        IntArrayList ids = tokenizer.encodeOrdinary(text);

        System.out.println("Encoded: "+ids);
        System.out.println("tokens: "+ids.size());

        System.out.println("Decoded: "+ tokenizer.decode(ids));


    }
}
