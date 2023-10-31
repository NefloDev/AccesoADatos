package Exceptions;

public class CSVFileReadException extends Exception{

    private String message;

    public CSVFileReadException(String message){
        this.message = message;
    }

    public CSVFileReadException(){
        message = "CSV File couldn't be read";
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
