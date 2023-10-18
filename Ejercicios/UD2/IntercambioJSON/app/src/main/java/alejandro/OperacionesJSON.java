package alejandro;

import alejandro.entities.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
    
    public static void main(String[] args) {
        Path bookJson = Path.of(".", "src", "main", "resources", "books.json");
        
        Book book1 = new Book("111-111-11111-1-1", "Libro1", "Autor1", 420, 2004);
        Book book2 = new Book("222-222-22222-2-2", "Libro2", "Autor2", 690, 2001);
        
        List<Book> books = List.of(book1, book2);
        
        System.out.println("Writing list of books...");
        writeObjectListJson(books, bookJson);
        
        System.out.println("############ BOOKS ############");
        readObjectListJson(bookJson).forEach(System.out::println);
    }
    
}
