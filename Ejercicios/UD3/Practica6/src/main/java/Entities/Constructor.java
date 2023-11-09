package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Constructor {

    private int id;
    private String name = "None";
    private String nationality = "None";

}
