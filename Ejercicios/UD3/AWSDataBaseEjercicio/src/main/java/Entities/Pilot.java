package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Pilot {

    private String code;
    private String forename;
    private String surname;
    private Date dob;
    private String nationality;
    private int constructorid;

}
