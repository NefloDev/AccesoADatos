package org.ficheros;

import java.io.Serializable;
import java.time.LocalDate;

public class Funko implements Serializable {

    //POSICIONES ARRAY
    private final static int COD_POSITION = 0;
    private final static int NAME_POSITION = 1;
    private final static int MODEL_POSITION = 2;
    private final static int PRICE_POSITION = 3;
    private final static int RELEASE_DATE_POSITION = 4;

    //PARAMETROS
    private final String code;
    private final String name;
    private final String model;
    private final double price;
    private final LocalDate releaseDate;

    public Funko(String definition){
        String[] array = definition.split(",");

        this.code = array[COD_POSITION];
        this.name = array[NAME_POSITION];
        this.model = array[MODEL_POSITION];
        this.price = Double.parseDouble(array[PRICE_POSITION]);
        this.releaseDate = LocalDate.parse(array[RELEASE_DATE_POSITION]);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
