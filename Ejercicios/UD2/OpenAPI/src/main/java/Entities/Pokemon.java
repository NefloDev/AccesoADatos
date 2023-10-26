package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    private int height;
    private int id;
    private String name;
    private List<Stats> stats;
    private List<Types> types;
    private int weight;

    @Override
    public String toString() {
        return "Pokemon{" +
                "\n\theight=" + height +
                ",\n\tid=" + id +
                ",\n\tname='" + name + '\'' +
                ",\n\tstats=" + stats.stream().map(Stats::getStatName).toList() +
                ",\n\ttypes=" + types.stream().map(Types::getTypeName).toList() +
                ",\n\tweight=" + weight + "kg" +
                "\n}";
    }

    public boolean containsType(String type){
        boolean contains = false;
        int i = 0;

        while(i<types.size() && !contains){
            if(types.get(i).getTypeName().equals(type)){
                contains = true;
            }
            i++;
        }

        return contains;

    }
}
