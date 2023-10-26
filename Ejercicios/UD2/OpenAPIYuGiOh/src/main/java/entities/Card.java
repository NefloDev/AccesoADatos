package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Card {

    private long id;
    private String name;
    private String type;
    private String race = "None";
    private String desc;
    private String archetype = "None";

    @Override
    public String toString(){
        return "Card{" +
                "\n\tid=" + id +
                ",\n\tname='" + name + '\'' +
                ",\n\ttype='" + type + '\'' +
                ",\n\trace='" + race + '\'' +
                ",\n\tdesc='" + desc + '\'' +
                ",\n\tarchetype='" + archetype + '\'' +
                "\n}";
    }

}
