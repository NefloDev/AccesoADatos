import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Card;

import java.net.URL;
import java.util.List;

public class ListCardManager {

    private List<Card> cards;

    public ListCardManager(){
        init();
    }

    private void init(){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;

        System.out.println("Loading cards...");
        try{
            node = mapper.readTree(new URL("https://db.ygoprodeck.com/api/v7/cardinfo.php"));
            cards = mapper.readValue(node.get("data").traverse(), new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Successfully loaded " + cards.size() + " cards.");

        Utils.waitToContinue();
    }

    public List<Card> getCards(){
        return cards;
    }

    public List<String> getNames(){
        return cards.stream().map(Card::getName).toList();
    }

    public List<Card> getCardByType(String type){
        return cards.stream().filter(c -> c.getType().equals(type)).toList();
    }

    public List<Card> getCardByArchetype(String archetype){
        return cards.stream().filter(c -> c.getArchetype().equals(archetype)).toList();
    }

    public List<Card> getCardByRace(String race){
        return cards.stream().filter(c -> c.getRace().equals(race)).toList();
    }

    public Card getCardByName(String name){
        return cards.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public List<String> getAllTypes(){
        return cards.stream().map(Card::getType).distinct().toList();
    }

    public List<String> getAllArchetypes(){
        return cards.stream().map(Card::getArchetype).distinct().toList();
    }

    public List<String> getAllRaces(){
        return cards.stream().map(Card::getRace).distinct().toList();
    }

}
