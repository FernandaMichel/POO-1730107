package edu.upv.poo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Implementación de la funcionalidad de leer desde un archivo de texto una 
 * matríz. Para esto el archivo los numeros deben estar separados por espacios.
 * @author TODO: Escriba aquí su nombre.
 */
public class MatrixReader {
    
    private String directorio;
    private File file;
    private List<String> contenidoDirectorio;
    
    /**
     * Inicializa una nueva instancia de MatrizReader especificando el 
     * directorio a trabajar (donde van a estrar los archivos).
     * @param directorio Directorio donde estan los archivos a leer y procesar.
     */
    public MatrixReader(String directorio) {
        
        this.directorio = directorio;
        file = new File(directorio);
    }    
      
    
    public void validar() throws MatrixReaderException {
        if (!file.exists()) {
            throw new MatrixReaderException("No existe el Archivo");
        }
    }     
       
        // TODO: Validación de que el directorio indicado exista.
        
    
    
    /**
     * Obtiene el directorio de donde se está trabajando y donde estan los 
     * archivos a procesar.
     * @return La ruta del directorio.
     */
    public String getDirectorio() { return directorio; }
    
    /**
     * Obtiene la matriz que representa un archivo, indicando el nombre del
     * archivo del cual se quiere obtener. 
     * @param fileName Nombre del archivo (debe estar dentro del directorio
     * indicado).
     * @param nullOnNoNumber Valor que indica si en caso de que se encunetre un 
     * valor que no es número, ese se ponga como null en la matríz.
     * @return Una matriz de Double de los valores del archivo.
     */
    public Double[][] getDoubleMatrix(
            String fileName, boolean nullOnNoNumber) throws IOException {
        
      try{
          contenidoDirectorio = Files.readAllLines(Paths.get(directorio));
          
      }
      catch(IOException ex){
      }
        // TODO: Funcionalidad de obtener la matriz desde el archivo indicado.
        
        return null;  // TODO: Eliminar el return null y regresar la matrix.
        
    }
    
    /**
     * 
     * @param fileName
     * @param nullOnNoNumber
     * @return  Una matriz de Integer de los valores del archivo.
     */
    public Integer[][] getIntegerMatriz(
            String fileName, boolean nullOnNoNumber) throws IOException {
        try{
          contenidoDirectorio = Files.readAllLines(Paths.get(directorio));
          
      }
      catch(IOException ex){
      }
        
        // TODO: Implementación de validación del archivo y su respectivo 
        // control de errores.
        
        // TODO: Funcionalidad de obtener la matriz desde el archivo indicado.
        
        return null;  // TODO: Eliminar el return null y regresar la matrix.
        
    }
    
}
