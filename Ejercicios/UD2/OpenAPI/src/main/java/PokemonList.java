import Entities.Pokemon;
import Entities.ShortPokemon;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.net.URL;
import java.util.List;

public class PokemonList {

    private PokeApiManager manager;
    @Getter
    private List<ShortPokemon> pokemonsShort;

    public PokemonList() {
        manager = new PokeApiManager();
        pokemonsShort = manager.getPokemons();
    }

    public Pokemon getPokemonByName(String name){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        String url;

        try{
            url = pokemonsShort.stream()
                    .filter(pokemon -> pokemon.getName().equals(name))
                    .findFirst().orElse(null)
                    .getUrl();
            node = mapper.readTree(new URL(url));
            return mapper.readValue(node.traverse(), Pokemon.class);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
