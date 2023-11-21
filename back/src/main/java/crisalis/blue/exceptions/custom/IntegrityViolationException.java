package crisalis.blue.exceptions.custom;

public class IntegrityViolationException extends RuntimeException{

    private static final String DESCRIPTION = "No se puede borrar, Violacion de Integridad (FK) (1402)";

    public IntegrityViolationException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
