package entidades;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class BookCollection {
    public ArrayList<Book> libros;

    public BookCollection(){
        libros = new ArrayList<>();
    }

    public void amountBooksWithMoreThan500Pages(){
        System.out.println(libros.stream().filter(p -> p.pages()>500).count());
    }

    public void amountBooksWithLessThan300Pages(){
        System.out.println(libros.stream().filter(p -> p.pages()<300).map(Book::title).count());
    }

    public void titlesOfBooksWithMoreThan500Pages(){
        libros.stream().filter(p -> p.pages()>500).forEach(b -> System.out.println(b.title()));
    }

    public void topThreeBooksWithHighestPages(){
        libros.stream().sorted(
                (p1,p2) -> p2.pages()-p1.pages()
                ).limit(3).forEach(b -> System.out.println(b.title()));
    }

    public void totalPagesSum(){
        System.out.println(libros.stream().mapToInt(Book::pages).sum());
    }

    public void booksWithMoreThanAveragePages(){
        libros.stream().filter(
                        p -> p.pages()>libros.stream().mapToInt(Book::pages).average().getAsDouble()
                ).forEach(b -> System.out.println(b.title()));
    }

    public void allAuthorNames(){
        libros.stream().forEach(b -> System.out.println(b.author()));
    }

    public void allRepeatedAuthors(){
        libros.stream().map(Book::author)
                .filter(
                        author -> Collections.frequency(libros.stream().map(Book::author).toList(), author) > 1
                ).distinct().forEach(System.out::println);

//        libros.stream().collect(
//                        Collectors
//                                .groupingBy(
//                                        Book::author, Collectors
//                                                .counting())
//                ).entrySet().stream().filter(b -> b.getValue() > 1).map(Map.Entry::getKey).forEach(System.out::println);
    }

    public void bookWithMostPages(){
        System.out.println(libros.stream().sorted((b1,b2) -> b2.pages() - b1.pages()).map(Book::title).findFirst().orElse("There isn't any book"));
    }

    public Collection<String> getCollectionOfAllBooksTitles(){
        return libros.stream().map(Book::title).toList();
    }

}
