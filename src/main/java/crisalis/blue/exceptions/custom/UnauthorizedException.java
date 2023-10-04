package crisalis.blue.exceptions.custom;

public class UnauthorizedException extends RuntimeException{

    private static final String DESCRIPTION = "Invalid credentials (401)";

    public UnauthorizedException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
