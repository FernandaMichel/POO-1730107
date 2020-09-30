package edu.upv.poo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Contiene las funcionalidades para trabajar con archivos de numerosList para 
 obtener datos estadísticos.
 * @author luisroberto
 */
public class Estadistica {
    
    private String rutaArchivo;
    private String encodingArchivo;
    
    private File file;
    
    private double[] numeros;    
    private double min;
    private double max;
    private double sum;
    private double avg;
        
    /**
     * Inicializa una nueva instancia de Estadistica, enviando como parámetro la
     * ruta del archivo con los números del cual se quieren sacar los datos
     * estadísticos.
     * @param rutaArchivo ruta completa del archivo de que contiene los números
     * de los cuales se quieren obtener los datos estadísticos.
     * @param encodingArchivo Encoding a usar para leer el archivo.
     */
    public Estadistica(String rutaArchivo, String encodingArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.encodingArchivo = 
                encodingArchivo == null || encodingArchivo.isEmpty() ? 
                "UTF-8" : encodingArchivo.trim();
        file = new File(rutaArchivo);
    }
    
    /**
     * Inicializa una nueva instancia de Estadistica, enviando como parámetro la
     * ruta del archivo con los números del cual se quieren sacar los datos
     * estadísticos. Aquí se usara el encoding UTF-8 para leer el archivo.
     * @param rutaArchivo ruta completa del archivo de que contiene los números
     * de los cuales se quieren obtener los datos estadísticos.
     */
    public Estadistica(String rutaArchivo) {
        this(rutaArchivo, "UTF-8");
    }
    
    /**
     * Obtiene la ruta del archivo de la cual se van a obtener los datos
     * estadísticos.
     */
    public String getRutaArchivo() { return rutaArchivo; }
    
    /**
     * Obtiene el encoding con el que se va a leer el archivo.
     */
    public String getEncodingArchivo() { return encodingArchivo; }
    
    /**
     * Cantidad de números obtenidos del archivo.
     */
    public int count() { 
        return numeros == null ? 0 : numeros.length;
    }
    
    /**
     * Número más pequeño del conjunto de números.
     */
    public double getMin() { 
        return count() == 0 ? 0.0 : min; 
    }
    
    /**
     * Número más grande del conjunto de números.
     */
    public double getMax() { 
        return count() == 0 ? 0.0 : max; 
    }
    
    /**
     * Sumatoria de todos los números.
     */
    public double getSum() { 
        return count() == 0 ? 0.0 : sum; 
    }
    
    /**
     * Promedio del conjunto de números.
     */
    public double getAvg() { 
        return count() > 0 ? avg : 0.0; 
    }
    
    /**
     * Contiene los números obtenidos en el procesamiento del archivo.
     */
    public double[] getNumeros() {
        return count() == 0 ? new double[0] : numeros;
    }
    
    /**
     * Realiza la ejecución de obtener los datos estadísticos de los números
     * del archivo especificado. Al procesar el archivo, hace una ejecución
     * segura en caso de datos erroneos (no numeros) encontrados en el archivo,
     * por lo que los datos erroneos se omiten y solo quedan los numeros 
     * obtenidos.
     * @throws EstadisticaException 
     */
    public void procesar() throws EstadisticaException {        
        validarArchivo();        
        leerArchivo();
        obtenerValoresEstadisticos();
    }
    
    private void validarArchivo() throws EstadisticaException {
        
        try {    
            
            if (!file.exists()) {
                throw new EstadisticaException("No existe el archivo.");
            }
            
            if (file.isDirectory()) {
                String message = 
                        "La ruta del archivo proporcionada corresponde a " +
                        "un directorio, por lo que no se puede leer.";
                throw new EstadisticaException(message);
            }

        }
        catch (SecurityException ex) {
            throw new EstadisticaException(
                    "Error de permisos al leer el archivo.", ex);
        }
        // NOTA: La excpeción arrojada EstadisticaException no es cachada, por
        // lo que se va al siguiente bloque try-catch que debe ser implementado
        // en otros métodos. Esto porque así definimos el comportamiento.
                
    }
    
    private void leerArchivo() throws EstadisticaException {
        
        ArrayList<Double> numerosList = new ArrayList<>();  // GENERICS
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        
        try {  // Bloque try-catch que maneja los errores al leer el archivo.         
            
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String linea = null;
            while ((linea = reader.readLine()) != null) {
                if (linea.isEmpty()) continue;
                try {
                    numerosList.add(Double.parseDouble(linea));
                } catch (NumberFormatException ex) {}
            }
            
        }
        catch (IOException ex) {            
            throw new EstadisticaException("Error al leer el archivo.", ex);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {}
            }
        }
        
        numeros = new double[numerosList.size()];
        for (int i = 0; i < numeros.length; i++) 
            numeros[i] = numerosList.get(i);
        
    }
    
    private void obtenerValoresEstadisticos() {
        
        min = Double.MAX_VALUE;
        max = Double.MIN_VALUE;
        sum = 0.0;        
        
        for (double i : numeros) {
            if (i < min) min = i;
            if (i > max) max = i;
            sum += i;
        }
        
        // Aquí prevenimos un error si no hay números, ya que una división entre
        // 0 no es posible  ;)
        if (count() != 0) avg = sum / count();
        
    }
    
}
