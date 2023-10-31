package Exceptions;

public class WrongSexInputException extends Exception{

    private String message;

    public WrongSexInputException(String message){
        this.message = message;
    }

    public WrongSexInputException(){
        message = "Sex input error. Valid values are - \"H\" and \"M\"";
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
