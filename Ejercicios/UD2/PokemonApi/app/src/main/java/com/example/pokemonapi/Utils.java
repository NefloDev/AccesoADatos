package com.example.pokemonapi;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<String> getNames(List<Card> cards){
        return cards.stream().map(Card::getName).collect(Collectors.toList());
    }

}
