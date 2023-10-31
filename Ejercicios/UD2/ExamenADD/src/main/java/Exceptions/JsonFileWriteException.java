package Exceptions;

public class JsonFileWriteException extends Exception{

    private String message;

    public JsonFileWriteException(String message){
        this.message = message;
    }

    public JsonFileWriteException(){
        message = "Json File couldn't be written";
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
