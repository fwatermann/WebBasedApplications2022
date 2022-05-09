package de.fhbi.webbasedapps.projektsammlung.errors;

public class Error500 {

    private static Error500 instanc;
    public static Error500 getInstance(){
        if(instanc == null){
            instanc = new Error500();
        }
        return instanc;
    }

    public final String code = "500";
    public final String message = "Internal Server Error";
    public final String description = "Something went wrong while handling your request. Please try again later.";

}
