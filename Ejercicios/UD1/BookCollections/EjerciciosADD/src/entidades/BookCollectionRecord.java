package entidades;

import excepciones.CollectionIsEmptyException;
import excepciones.NoBooksFoundException;

import java.util.ArrayList;
import java.util.List;

public record BookCollectionRecord(List<Book> books) {
    public List<Book> find(String textToFind) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : this.books) {
            if (book.isbn().equals(textToFind) ||
                    book.title().contains(textToFind) ||
                    book.author().contains(textToFind)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findOrFail(String textToFind) throws CollectionIsEmptyException, NoBooksFoundException {
        if(books.isEmpty()){
            throw new CollectionIsEmptyException("La lista de libros está vacía");
        }else if(textToFind.trim().isBlank()){
            throw new NoBooksFoundException("No ha introducido ningún libro para buscar");
        }else{
            List<Book> foundBooks = new ArrayList<>();
            for (Book book : this.books) {
                if (book.isbn().equals(textToFind) ||
                        book.title().contains(textToFind) ||
                        book.author().contains(textToFind)) {
                    foundBooks.add(book);
                }
            }
            return foundBooks;
        }
    }
}
