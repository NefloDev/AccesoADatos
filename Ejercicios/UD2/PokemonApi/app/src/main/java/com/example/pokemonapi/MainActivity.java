package com.example.pokemonapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView pokemonJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void load(View view){
        PokemonApi pokemonApi = new PokemonApi();
        pokemonApi.start();
        try {
            pokemonApi.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pokemonJson = findViewById(R.id.pokemonJson);
        pokemonJson.setText("");

        ArrayList<PokemonCustom> list = pokemonApi.getPokemons();

        list.forEach(pokemon -> {
            pokemonJson.append(pokemon.getName() + "\n");
        });
    }
}