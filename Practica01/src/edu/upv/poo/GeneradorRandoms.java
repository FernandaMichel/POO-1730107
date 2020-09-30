package edu.upv.poo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

/**
 *
 * @author luisroberto
 */
public class GeneradorRandoms {
    
    private String directorio;
    private String nombreGenerador;
    
    public GeneradorRandoms(String nombreGenerador, String directorio)
            throws IllegalArgumentException, GeneradorRandomsException {
        
        if (nombreGenerador == null || nombreGenerador.isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del generador de randoms debe ser especificado.");
        }
        this.nombreGenerador = nombreGenerador;
        
        if (directorio == null || directorio.isEmpty()) {
            throw new IllegalArgumentException(
                    "directorio debe ser especificado.");
        }        
        this.directorio = directorio;
        
        validarDirectorio();
        
    }
    
    public String getDirectorio() { return directorio; }
    
    public String getNombreGenerador() { return nombreGenerador; }
    
    private void validarDirectorio() throws GeneradorRandomsException {
        File dir = new File(directorio);
        if (dir.exists() == false) {
            throw new GeneradorRandomsException(
                    getNombreGenerador(), "No existe el directorio.");
        }
        if (dir.isDirectory() == false) {
            throw new GeneradorRandomsException(
                    getNombreGenerador(), 
                    "El directorio proporcionado corresponde a un archivo.");
        }
    }
    
    public float[] generar(int cantidad, String nombreArchivo) 
            throws GeneradorRandomsException {
        
        cantidad = Math.abs(cantidad);
        
        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar nombreArchivo.");
        }
        
        SecureRandom secureRandom = new SecureRandom();
        float[] randoms = new float[cantidad];  
        for (int i = 0; i < cantidad; i++) {
            randoms[i] = secureRandom.nextFloat();
        }
              
        Path pathArchivo = Paths.get(directorio, nombreArchivo);
        Charset encoding = Charset.forName("UTF-8");
        
        try (BufferedWriter writer = Files.newBufferedWriter(pathArchivo, encoding)) {
            for (int i = 0; i < cantidad; i++) {
                writer.write(String.format("%.9f\n", randoms[i]));
            }
        } 
        catch (IOException ex) {
            throw new GeneradorRandomsException(
                    getNombreGenerador(), "Error al escribir archivo.", ex);
        }
        
        return randoms;
        
    }
    
}
