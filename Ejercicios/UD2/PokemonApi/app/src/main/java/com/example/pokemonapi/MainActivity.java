package com.example.pokemonapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ListView pokemonJson;
    private List<Card> cards;
    private ListCardManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new ListCardManager(this);
        pokemonJson = findViewById(R.id.pokemonJson);

    }

    public void load(View view){
        manager.load();
    }

    public void showData(View view){
        ArrayAdapter<String> adapter;

        cards = manager.getCards();

        if(cards.isEmpty()){
            Toast.makeText(this, "Data hasn't been retrieved yet", Toast.LENGTH_SHORT).show();
        }else{
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Utils.getNames(cards));
            pokemonJson.setAdapter(adapter);
        }

    }
}