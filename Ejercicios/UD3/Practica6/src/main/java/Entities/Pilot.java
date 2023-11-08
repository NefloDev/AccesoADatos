package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pilot {
    private int id;
    private String code;
    private String name;
    private String surname;
    private LocalDate dob;
    private String nationality;

    @Override
    public String toString(){
        return "ID: " + id +
                "\nCodigo: " + code +
                "\nNombre: " + name +
                "\nApellido: " + surname +
                "\nFecha de nacimiento: " + dob +
                "\nNacionalidad: " + nationality;
    }

}
