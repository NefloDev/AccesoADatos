package com.example.pokemonapi;

import android.os.AsyncTask;
import android.widget.Toast;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class PokemonApi extends Thread {

    private String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
    private URL url;
    private HttpURLConnection conn;
    private String json;
    private ArrayList<PokemonCustom> list;

    private void connect() throws IOException {
        //En verdad hace falta el Retrofit para acceder a la API
        int i = 1;
        int length = pokemonUrl.length();

        url = new URL(pokemonUrl);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        do {
            pokemonUrl = pokemonUrl.substring(0, length) + i;

            url = new URL(pokemonUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            if(getData().getName() != null){
                list.add(getData());
                i++;
            }
        }while (i < 30);
    }

    private PokemonCustom getData() throws IOException {
        int responseCode = conn.getResponseCode();
        if(responseCode != 200){
            System.err.println("HttpResponseCode: " + responseCode);
        }else{
            StringBuilder informationString = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                informationString.append(line);
            }

            reader.close();

            json = informationString.toString();
        }

        return new PokemonCustom(jsonPokemonName(json), jsonPokemonType(json));
    }

    @Override
    public void run() {
        try {
            list = new ArrayList<>();
            connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<PokemonCustom> getPokemons(){
        return list;
    }

    private String jsonPokemonName(String json){
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            return object.getString("name");
        } catch (JSONException e) {
            return null;
        }
    }

    private String jsonPokemonType(String json){
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            return object.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");
        } catch (JSONException e) {
            return null;
        }
    }
}
