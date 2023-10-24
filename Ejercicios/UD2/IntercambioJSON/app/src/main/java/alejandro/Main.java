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

        List<Book> books = OperacionesJSON.readObjectListJson(bookJson);
        boolean finish = false;
        int menu, search;
        String searchInput;

        do {
            menu = showMenu("""
                    ############### MENU ###############
                    1.- See all books
                    2.- Search book
                    3.- Add book
                    4.- Exit""", 1, 4);
            switch (menu){
                case 1:
                    books.forEach(b -> System.out.println(b.toString()));
                    break;
                case 2:
                    search = showMenu("""
                    
                    ############### SEARCH BOOKS ###############
                    1.- Search by author
                    2.- Search by title
                    3.- Exit
                    
                    """, 1, 3);

                    searchInput = getInput("Value to search: ");
                    if(search == 1){
                        OperacionesJSON.showBooksByAuthor(books, searchInput);
                    }else{
                        OperacionesJSON.showBooksByTitle(books, searchInput);
                    }
                    break;
                case 3:
                    Book temp = addBook();
                    if(temp != null){
                        books.add(temp);
                        OperacionesJSON.writeObjectListJson(books, bookJson);
                    }
                    break;
                default:
                    finish = true;
            }
        }while(!finish);
    }

    public static int showMenu(String message, int min, int max){
        int input;
        do {
            input = getInteger(message);
        }while (input < min || input > max);
        return input;
    }

    public static Book addBook(){
        Book book = null;
        String isbn, title, author;
        String isbnRegex = "\\d{3}-\\d{3}-\\d{5}-\\d-\\d";
        int pages, year;

        do{
            isbn = getInput("ISBN (XXX-XXX-XXXXX-X-X): ");
            if(!isbn.matches(isbnRegex) && !isbn.equalsIgnoreCase("exit")){
                System.err.println("ISBN wrong format (enter \"exit\" to exit)");
            }
        }while(!isbn.matches(isbnRegex) && !isbn.equalsIgnoreCase("exit"));

        if(!isbn.equalsIgnoreCase("exit")){
            title = getInput("Titulo: ");
            if(!title.equalsIgnoreCase("exit")){
                author = getInput("Autor: ");
                if(!author.equalsIgnoreCase("exit")){
                    pages = getInteger("Número de páginas: ");
                    if(pages != -1){
                        year = getInteger("Año publicación: ");
                        book = year != -1 ? new Book(isbn, title, author, pages, year) : null;
                    }
                }
            }
        }

        return book;
    }

    public static String getInput(String message){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        boolean repeat;
        do{
            repeat = false;
            System.out.println(message);
            try {
                input = reader.readLine();
                if (input.isEmpty()){
                    repeat = true;
                    System.err.println("Input can't be empty");
                }
            } catch (IOException e) {
                System.err.println("Input mismatch (enter \"exit\" to exit)");
                repeat = true;
            } finally {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
        }while (repeat);
        return input;
    }

    public static int getInteger(String message){
        boolean repeatInteger;
        String numberInput;
        int input = 0;
        do {
            repeatInteger = false;
            try{
                numberInput = getInput(message);
                if (!numberInput.equalsIgnoreCase("exit")){
                    input = Integer.parseInt(numberInput);
                }else{
                    input = -1;
                }
            }catch (NumberFormatException e) {
                System.err.println("Input mismatch (enter \"exit\" to exit)");
                repeatInteger = true;
            }
        }while (repeatInteger);

        return input;
    }
}