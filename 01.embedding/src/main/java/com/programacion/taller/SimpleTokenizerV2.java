package com.programacion.taller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SimpleTokenizerV2 {
    public static final String regex = "(?=[,.:;?_!\"()']|\\s)|(?=[,.:;?_!\"()']|\\s)";

    private Map<String, Integer> strToInt;
    private Map<Integer, String> intToStr;

    public SimpleTokenizerV2(List<Pair> vocab) {
        strToInt = vocab.stream()
                .collect(Collectors.toMap(Pair::token, Pair::id));

        intToStr = vocab.stream()
                .collect(Collectors.toMap(Pair::id, Pair::token));
    }

    public List<Integer> encode(String text) {

        return Arrays.stream(text.split(regex))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(token-> strToInt.getOrDefault(token, strToInt.get("<|unk|>")))
                .filter(Objects::nonNull)
                .toList();
    }

    public String decode(List<Integer> ids) {

        return ids.stream()
                .map(id-> intToStr.getOrDefault(id,"UNK"))
                .collect(Collectors.joining(" "));
    }

}
