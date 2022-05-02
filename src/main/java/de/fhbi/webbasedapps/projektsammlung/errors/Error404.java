package de.fhbi.webbasedapps.projektsammlung.errors;

public class Error404 {

    private static Error404 instanc;
    public static Error404 getInstance(){
        if(instanc == null){
            instanc = new Error404();
        }
        return instanc;
    }

    public final String code = "404";
    public final String message = "Not Found";
    public final String description = "The requested resource could not be found.";

}
