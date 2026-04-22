package com.programacion.taller;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.Shape;
import ai.djl.nn.core.Embedding;
import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class EmbeddingTest {
    public static final String PATH= "C:\\tallerIII\\taller\\01.embedding\\src\\main\\resources\\the-verdict.txt";

    static void main() throws Exception {
        String raw = Files.lines(Paths.get(PATH))
                .reduce(String::concat)
                .orElse("");

        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_003);

        var enc_text = tokenizer.encode(raw);
        var enc_text_boxed = enc_text.boxed();
        var enc_sample = enc_text_boxed.subList(50, enc_text_boxed.size());


        //-----------------------

        int contextSize =4;
        var x = enc_sample.subList(0,4);
        var y = enc_sample.subList(1,contextSize+1);

//        System.out.println(x);

        IntArrayList inputTokens = new IntArrayList();
        x.forEach(inputTokens::add);
        IntArrayList targetTokens = new IntArrayList();
        y.forEach(targetTokens::add);

//        System.out.println(tokenizer.decode(inputTokens));
//        System.out.println("    "+tokenizer.decode(targetTokens));


        List<DatasetItem> dataset = new ArrayList<>();
        List<Integer> tokenIds = tokenizer.encode(raw).boxed();

        int maxLength = 64;
        IntStream.range(0, tokenIds.size()-maxLength)
                .forEach(i -> {
                    var inputChunk= tokenIds.subList(i,i+maxLength);
                    var targetChunk= tokenIds.subList(i+1,i+maxLength+1);

                    dataset.add(new DatasetItem(inputChunk,targetChunk));
                });


        //embedding

        int vocabSize =50257;
        int outputDim = 256;

        try(NDManager manager = NDManager.newBaseManager()) {
            NDArray weights = manager.randomUniform(
                    -1.0f,1.0f,
                    new Shape(vocabSize, outputDim));


            AtomicInteger count = new AtomicInteger(0);
            dataset.stream()
//                    .takeWhile(it -> count.getAndIncrement()<2)
                    .limit(3)
                    .forEach(item -> {
                        var input = item.input().stream()
                                .mapToLong(Integer::longValue)
                                .toArray();

                        NDArray indices = manager.create(input);
                        var embedding = weights.get(indices);

                        System.out.println("Input indices: "+ Arrays.toString(input));
                        System.out.println("Embedding shape: " +embedding);
                    });

        }



        EmbeddingModel embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();
        var text= "hello";

        Response<Embedding> response = embeddingModel.embed(text);
        float[] vector = response.content().vector();

        System.out.println("Embedding Size: "+ vector.length);
        System.out.println(Arrays.toString(vector));

    }
}
