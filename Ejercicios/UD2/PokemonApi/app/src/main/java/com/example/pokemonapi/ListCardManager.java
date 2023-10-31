package com.example.pokemonapi;

import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCardManager{

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiClient api;

    private final ArrayList<Card> cards;
    private Context c;

    public ListCardManager(Context c){
        this.c = c;
        cards = new ArrayList<>();
    }

    public void load() {
        api = retrofit.create(ApiClient.class);
        api.getCards().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200 && response.body()!=null){
                    cards.addAll(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(c, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Card> getCards(){
        return cards;
    }

    public List<String> getNames(){
        return cards.stream().map(Card::getName).collect(Collectors.toList());
    }

    public List<Card> getCardByType(String type){
        return cards.stream().filter(c -> c.getType().equals(type)).collect(Collectors.toList());
    }

    public List<Card> getCardByArchetype(String archetype){
        return cards.stream().filter(c -> c.getArchetype().equals(archetype)).collect(Collectors.toList());
    }

    public List<Card> getCardByRace(String race){
        return cards.stream().filter(c -> c.getRace().equals(race)).collect(Collectors.toList());
    }

    public Card getCardByName(String name){
        return cards.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public List<String> getAllTypes(){
        return cards.stream().map(Card::getType).distinct().collect(Collectors.toList());
    }

    public List<String> getAllArchetypes(){
        return cards.stream().map(Card::getArchetype).distinct().collect(Collectors.toList());
    }

    public List<String> getAllRaces(){
        return cards.stream().map(Card::getRace).distinct().collect(Collectors.toList());
    }
}

