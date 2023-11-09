package Entities;

import java.util.List;

public enum Columns {

    PILOT(List.of("driverid", "code", "forename", "surname", "dob", "nationality", "constructorid")),
    CONSTRUCTOR(List.of("constructorid", "name", "nationality"));

    private List<String> value;

    private Columns(List<String> value){
        this.value = value;
    }

    public String get(int index){
        return value.get(index);
    }

}
