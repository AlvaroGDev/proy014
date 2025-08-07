package es.cic.curso25.proy014.controller;

public class SecurityException extends RuntimeException {
    
 
    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable throable) {
        super(message, throable);
    }


}