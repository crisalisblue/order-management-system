package crisalis.blue.exceptions.custom;

public class EmptyElementException extends RuntimeException{

    private static final String DESCRIPTION = "Empty element (404)";

    public EmptyElementException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
