package Utilities;

import Entities.Actor;
import Entities.PeliculaOscarizada;
import Entities.Pelicula;
import Exceptions.CSVFileReadException;
import Exceptions.JsonFileWriteException;
import Exceptions.WrongSexInputException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Utilidades {
    public static final String CSVSEPARATOR = ";";
    private static final String OSCARMALEFILENAME = "oscar_age_male.csv";
    private static final String OSCARFEMALEFILENAME = "oscar_age_female.csv";
    private static final Path ACTORESJSON = Path.of(".", "src", "main", "resources", "salida");

    public static List<PeliculaOscarizada> leerPeliculasOscarizadasCsv(String sexo) throws CSVFileReadException, WrongSexInputException {
        if(sexo.equals("H") || sexo.equals("M")){

            Path finalPath = Path.of(".", "src", "main", "resources", (sexo.equals("H") ? OSCARMALEFILENAME : OSCARFEMALEFILENAME));

            try(Stream<String> actors = Files.lines(finalPath).skip(1)){
                List<PeliculaOscarizada> list = actors.distinct().map(PeliculaOscarizada::new).toList();
                list.forEach(p -> {
                    if(sexo.equals("H")){
                        p.setSexo("H");
                    }else{
                        p.setSexo("M");
                    }
                });
                return list;
            }catch (IOException e){
                throw new CSVFileReadException();
            }
        }else{
            throw new WrongSexInputException();
        }
    }

    public static List<Actor> convertirPeliculasOscarizadasEnActores(List<PeliculaOscarizada> lista){
        List<Actor> actores = lista.stream().map(Actor::new).distinct().sorted(Comparator.comparing(Actor::getNombre)).toList();

        actores.forEach(a -> a.setPeliculas(lista.stream().filter(p -> p.getActor().equals(a.getNombre())).map(Pelicula::new).toList()));

        return actores;
    }

    public static void escribirActoresEnJson(List<Actor> actores) throws JsonFileWriteException {
        try{
            ACTORESJSON.toFile().mkdirs();
            Path jsonPath = Path.of(ACTORESJSON.toFile().getAbsolutePath(), "actores.json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(jsonPath.toFile(), actores);
        }catch (IOException e){
            throw new JsonFileWriteException();
        }
    }

    //Bonus

    public static List<Actor> actoresConMasDeUnOscar(List<PeliculaOscarizada> pelis){
        List<Actor> actores = Utilidades.convertirPeliculasOscarizadasEnActores(pelis);
        return actores.stream().filter(a -> a.getPeliculas().size() > 1).toList();
    }

    public static List<Actor> actoresMasJovenesEnGanarUnOscar(List<Actor> actores){
        return List.of(
                actores.stream().filter(a -> a.getSexo().equals("H"))
                        .sorted(Comparator.comparingInt(Utilidades::getEdadJoven))
                        .limit(1).toList().get(0),
                actores.stream().filter(a -> a.getSexo().equals("M"))
                        .sorted(Comparator.comparingInt(Utilidades::getEdadJoven))
                        .limit(1).toList().get(0)
        );
    }

    public static int getEdadJoven(Actor a){
        return getAnyoPeliculaMasVieja(a.getPeliculas()) - a.getAnyoNacimiento();
    }

    private static int getAnyoPeliculaMasVieja(List<Pelicula> p){
        return p.stream()
                .sorted(Comparator.comparingInt(Pelicula::getAnyo))
                .map(Pelicula::getAnyo)
                .limit(1).toList().get(0);
    }

}
