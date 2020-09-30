package edu.upv.poo;

/**
 *
 * @author luisroberto
 */
public class GeneradorRandomsException extends Exception {
    
    private String nombreGenerador;
    
    public GeneradorRandomsException(String nombreGenerador, String message) {
        super(message);
        this.nombreGenerador = nombreGenerador;
    }
    
    public GeneradorRandomsException(
            String nombreGenerador, String message, Throwable cause) {
        super(message, cause);
        this.nombreGenerador = nombreGenerador;
    }
    
    public String getNombreGenerador() { return nombreGenerador; }
    
}
