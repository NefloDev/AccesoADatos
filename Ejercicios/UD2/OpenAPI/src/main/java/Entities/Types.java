package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Types {

    private int slot;
    private Type type;

    public String getTypeName(){
        return type.getName();
    }

}
