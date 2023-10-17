package excepciones;

public class NoBooksFoundException extends Exception{

    private String message;

    public NoBooksFoundException(){
        message = "No books where found in the collection";
    }

    public NoBooksFoundException(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
