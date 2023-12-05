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
        api.getPokemons(20, 10).enqueue(new Callback<>() {
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

    public List<Card> getCards() {
        return cards;
    }
}

