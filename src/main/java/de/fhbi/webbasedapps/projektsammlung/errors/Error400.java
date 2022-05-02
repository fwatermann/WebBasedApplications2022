package de.fhbi.webbasedapps.projektsammlung.errors;

public class Error400 {

    private static Error400 instance;
    public static Error400 getInstance(){
        if(instance == null){
            instance = new Error400();
        }
        return instance;
    }

    public final String code = "400";
    public final String message = "Bad Request";
    public final String description = "The request could not be understood by the server due to malformed syntax. The client SHOULD NOT repeat the request without modifications.";
}
