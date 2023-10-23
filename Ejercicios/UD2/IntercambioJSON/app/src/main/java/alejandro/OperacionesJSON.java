package alejandro;

import alejandro.entities.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class OperacionesJSON {
    
    public static void writeObjectListJson(List<Book> list, Path path){
        try{
            Files.deleteIfExists(path);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(path.toFile(), list);
        }catch(IOException e){
            System.err.println("File not found");
        }
    }
    
    public static List<Book> readObjectListJson(Path path){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(path.toFile(), new TypeReference<>(){});
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void showBooksByTitle(List<Book> list, String title){
        List<Book> stream = list.stream().filter(b -> b.getTitulo().equals(title)).toList();
        if(!stream.isEmpty()){
            stream.forEach(b -> System.out.println(b.toString()));
        }else{
            System.out.println("There isn't any book with that title");
        }
    }

    public static void showBooksByAuthor(List<Book> list, String author){
        List<Book> stream = list.stream().filter(b -> b.getAutor().equals(author)).toList();
        if(!stream.isEmpty()){
            stream.forEach(b -> System.out.println(b.toString()));
        }else{
            System.out.println("There isn't any book with that author");
        }
    }
    
}
