package alejandro;

import alejandro.entities.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path bookJson = Path.of(".","app", "src", "main", "resources", "books.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Book> books = OperacionesJSON.readObjectListJson(bookJson);
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
                                OperacionesJSON.showBooksByAuthor(books, searchInput);
                            }else{
                                OperacionesJSON.showBooksByTitle(books, searchInput);
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
                    books.add(addBook());
                    OperacionesJSON.writeObjectListJson(books, bookJson);
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

    public static Book addBook(){
        Book book = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String isbn, title, author;
        int pages = 0, year = 0;
        boolean repeat;
        boolean repeatInteger;

        do{
            repeat = false;
            try {
                do{
                    System.out.print("ISBN (XXX-XXX-XXXXX-X-X): ");
                    isbn = reader.readLine();
                    if(!isbn.matches("\\d{3}-\\d{3}-\\d{5}-\\d-\\d") && !isbn.equalsIgnoreCase("exit")){
                        System.err.println("ISBN wrong format");
                    }
                }while(!isbn.matches("\\d{3}-\\d{3}-\\d{5}-\\d-\\d") && !isbn.equalsIgnoreCase("exit"));
                if(!isbn.equalsIgnoreCase("exit")){
                    System.out.print("Titulo: ");
                    title = reader.readLine();
                    System.out.print("Autor: ");
                    author = reader.readLine();
                    do {
                        repeatInteger = false;
                        try{
                            System.out.print("Paginas: ");
                            pages = Integer.parseInt(reader.readLine());
                        }catch (NumberFormatException e) {
                            System.err.println("Input mismatch (enter \"exit\" to exit)");
                            repeatInteger = true;
                        }finally {
                            reader = new BufferedReader(new InputStreamReader(System.in));
                        }
                    }while(repeatInteger);

                    do {
                        repeatInteger = false;
                        try{
                            System.out.print("Año publicación: ");
                            year = Integer.parseInt(reader.readLine());
                        }catch (NumberFormatException e) {
                            System.err.println("Input mismatch (enter \"exit\" to exit)");
                            repeatInteger = true;
                        }finally {
                            reader = new BufferedReader(new InputStreamReader(System.in));
                        }
                    }while (repeatInteger);
                    book = new Book(isbn, title, author, pages, year);
                }
            } catch (IOException e) {
                System.err.println("Input mismatch (enter \"exit\" to exit)");
                repeat = true;
            } finally {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
        }while(repeat);

        return book;
    }

}
