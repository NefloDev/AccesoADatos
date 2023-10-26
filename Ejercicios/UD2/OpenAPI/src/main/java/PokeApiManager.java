import Entities.ShortPokemon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;


public class PokeApiManager {

    private static final String link = "https://pokeapi.co/api/v2/pokemon/";
    private ArrayList<ShortPokemon> pokemons;

    public PokeApiManager() {
        pokemons = new ArrayList<>();
        fillPokemons();
    }

    public void fillPokemons(){
        int count;
        String pokemonUrl;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;

        System.out.println("Loading...");

        //El funcionamiento de este API tiene un pequeño problema:
        //Para obtener datos de un pokemon hay que ir a su json específico, ej: https://pokeapi.co/api/v2/pokemon/32
        //Y si intentas obtener todos los pokemons del json "Pokemon" solo extrae su nombre y URL
        //Así que lo que hago es obtener el json de "Pokemon" con todos sus pokemons y lo paso a una lista de ShortPokemon
        //Esta clase es la que luego uso para recoger cada pokemon a partir de su url
        try{
            node = mapper.readTree(new URL(link));
            count = node.get("count").asInt();

            pokemonUrl = link + "?limit=" + count;

            node = mapper.readTree(new URL(pokemonUrl));
            pokemons = mapper.readValue(node.get("results").traverse(), new TypeReference<>() {});
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println("Loaded "+pokemons.size()+" pokemons");
    }

    public ArrayList<ShortPokemon> getPokemons() {
        return pokemons;
    }

}
