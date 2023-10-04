package crisalis.blue.exceptions.custom;

public class NotCreatedException extends RuntimeException{
    private static final String DESCRIPTION = "Error in create (400)";

    public NotCreatedException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
