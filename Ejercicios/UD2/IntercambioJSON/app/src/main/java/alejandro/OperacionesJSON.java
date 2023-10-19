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
        Stream<Book> stream = list.stream().filter(b -> b.getTitulo().equals(title));
        if(stream.toList().size()>0){
            stream.forEach(b -> System.out.println(b.toString()));
        }else{
            System.out.println("There isn't any book with that title");
        }
    }

    public static void showBooksByAuthor(List<Book> list, String author){
        Stream<Book> stream = list.stream().filter(b -> b.getAutor().equals(author));
        if(stream.toList().size()>0){
            stream.forEach(b -> System.out.println(b.toString()));
        }else{
            System.out.println("There isn't any book with that author");
        }
    }
    
    public static void main(String[] args) {
        Path bookJson = Path.of(".", "src", "main", "resources", "books.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Book> books = readObjectListJson(bookJson);
        boolean finish = false;
        boolean repeat;
        int search;
        String searchInput;

        do {
            switch (menu()){
                case 1:
                    books.forEach(b -> System.out.println(b.toString()));
                    break;
                case 2:
                    search = search();
                    do{
                        repeat = false;
                        try{
                            System.out.print("Value to search: ");
                            searchInput = reader.readLine();
                            if(search == 1){
                                showBooksByTitle(books, searchInput);
                            }else{
                                showBooksByAuthor(books, searchInput);
                            }
                        }catch (IOException e){
                            System.err.println("Input mismatch");
                            repeat = true;
                        }finally {
                            reader = new BufferedReader(new InputStreamReader(System.in));
                        }
                    }while(repeat);
                    break;
                case 3:
                    break;
                default:
                    finish = true;
            }
        }while(!finish);
    }

    public static int menu(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        boolean repeat;

        do{
            repeat = false;
            try{
                System.out.println("""
                
                ############### MENU ###############
                1.- See all books
                2.- Search book
                3.- Add book
                4.- Exit
                
                """);
                input = Integer.parseInt(reader.readLine());
            }catch (IOException e){
                System.err.println("Input mismatch");
                repeat = true;
            }finally {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }

        }while((input > 4 || input < 1) && repeat);

        return input;
    }

    public static int search(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean repeat;
        int input = 0;

        do{
            repeat = false;
            try{
                System.out.println("""
                
                ############### SEARCH BOOKS ###############
                1.- Search by author
                2.- Search by title
                3.- Exit
                
                """);
                input = Integer.parseInt(reader.readLine());
            }catch (IOException e){
                System.err.println("Input mismatch");
                repeat = true;
            }finally {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }

        }while((input > 3 || input < 1) && repeat);

        return input;
    }
    
}
