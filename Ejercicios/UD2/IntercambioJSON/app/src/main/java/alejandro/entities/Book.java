package alejandro.entities;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int pages;
    private int publicationYear;

    public Book(String isbn, String title, String author, int pages, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publicationYear = publicationYear;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    public String getAutor() {
        return author;
    }

    public void setAutor(String autor) {
        this.author = autor;
    }

    public int getPaginas() {
        return pages;
    }

    public void setPaginas(int paginas) {
        this.pages = paginas;
    }

    public int getAnyoPublicacion() {
        return publicationYear;
    }

    public void setAnyoPublicacion(int anyoPublicacion) {
        this.publicationYear = anyoPublicacion;
    }
    
    @Override
    public String toString(){
        return "Libro{" +
                "\n\tisbn='" + isbn + "\'" +
                "\n\ttitulo='" + title + "\'" + 
                "\n\tautor='" + author + "\'" +
                "\n\tpaginas='" + pages + "\'" +
                "\n\taño_publicacion='" + publicationYear + "\'" +
                "\n}";
    }
    
}
