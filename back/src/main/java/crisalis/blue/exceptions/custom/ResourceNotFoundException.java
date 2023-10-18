package crisalis.blue.exceptions.custom;

public class ResourceNotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "Element Not Found (404)";

    public ResourceNotFoundException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}