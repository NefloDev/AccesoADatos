package test;

import entidades.Book;
import entidades.BookCollectionRecord;
import excepciones.CollectionIsEmptyException;
import excepciones.NoBooksFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookCollectionRecordTest {

    private BookCollectionRecord col;
    private List<Book> bookList;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.out.println("Testing complete.");
    }

    @Test
    void shouldNotFindIfCollectionIsEmpty() {
        bookList = new ArrayList<>();
        col = new BookCollectionRecord(bookList);
        String bookToFind = "MyBook";
        List<Book> expected = new ArrayList<>();

        assertEquals(expected, col.find(bookToFind));
    }

    @Test
    void shouldGetAnEmptyListIfNoMatchesAreFound(){
        bookList = new ArrayList<>();
        bookList.add(new Book("1234", "Book1", "Me", 10));
        bookList.add(new Book("4321", "Book2", "You", 20));
        col = new BookCollectionRecord(bookList);
        String bookToFind = "Book3";
        List<Book> expected = new ArrayList<>();

        assertEquals(expected, col.find(bookToFind));
    }

    @Test
    void shouldGetExceptionWhenUsingFindOrFailWithAnEmptyCollection(){
        bookList = new ArrayList<>();
        col = new BookCollectionRecord(bookList);
        String bookToFind = "MyBook";
        List<Book> expected = new ArrayList<>();

        assertThrows(CollectionIsEmptyException.class, () -> col.findOrFail(bookToFind));
    }

    @Test
    void shouldGetExceptionWhenUsingFindOrFailWithANonExistentEntry(){
        bookList = new ArrayList<>();
        bookList.add(new Book("1234", "Book1", "Me", 10));
        bookList.add(new Book("4321", "Book2", "You", 20));
        col = new BookCollectionRecord(bookList);
        String bookToFind = "    ";
        List<Book> expected = new ArrayList<>();

        assertThrows(NoBooksFoundException.class, () -> col.findOrFail(bookToFind));
    }
}