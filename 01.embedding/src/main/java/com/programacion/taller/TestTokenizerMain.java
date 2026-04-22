package com.programacion.taller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TestTokenizerMain {

    public static final String PATH= "C:\\tallerIII\\taller\\01.embedding\\src\\main\\resources\\the-verdict.txt";

    public static List<Pair> vocabulary(String filename) throws Exception{

        String raw = Files.lines(Paths.get(filename)).reduce(String::concat).orElse("");

//        System.out.println("number of char"+ raw.length());
//        System.out.println(raw.substring(0,99));


        String regex = "(?=[,.:;?_!\"()']|\\s)|(?=[,.:;?_!\"()']|\\s)";
         var tokens = raw.split(regex);
         var preprocessed = Stream.of(tokens).map(String::trim)
                 .filter(s -> !s.isEmpty())
                 .toList();
//        System.out.println("*********************************");
//
//        System.out.println(preprocessed);
//        System.out.println(preprocessed.stream().map("'%s'"::formatted).toList().subList(0, 30));
//        System.out.println(preprocessed.size());


        //---------------
        var allWords =preprocessed.stream().distinct().sorted().toList();
        var vocabSize = allWords.size();
//        System.out.println("vocab Size" + vocabSize);


        //---

        AtomicInteger counter = new AtomicInteger(0);
        var vocab =allWords.stream().map(it-> new Pair(counter.getAndIncrement(),it))
                .toList();

        return vocab;


    }

    static void main(String[] args) throws Exception {
        var vocab = vocabulary(PATH);

        vocab.stream().takeWhile(it-> it.id()<51)
                .forEach(System.out::println);


        //--
        var text = "\"It's the last he painted, you know,\" Mrs. Gisburn said with pardonable pride.";
        SimpleTokenizerV1 tokenizerV1= new SimpleTokenizerV1(vocab);
        var ids = tokenizerV1.encode(text);

        System.out.println(ids);


        System.out.println(tokenizerV1.decode(ids));


        // ----

//        var vacabEx = vocabulary()
        
        // -----


//        var text1 =
        SimpleTokenizerV2 tokenizerV2 = new SimpleTokenizerV2(vocab);
        
        var ids2 = tokenizerV2.encode(text);
        System.out.println("ids2 = " + ids2);
    }
}
