package edu.upv.poo;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author luisroberto
 */
public class App {

    // Opciones del menú de la aplicación.
    public static final int OPCION_SALIR = 0;
    public static final int OPCION_MANEJO_ERRORES_DATOS_ENTRADA = 1;
    public static final int OPCION_MANEJO_ERRORES_LEER_ARCHIVOS = 2;
    public static final int OPCION_MANEJO_ERRORES_ESCRIBIR_ARCHIVOS = 3;
    public static final int OPCION_MANEJO_ERRORES_LEER_MATRIZ = 4;
    
    private Scanner in = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    
    /**
     * Proceso principal de la aplicación.
     */
    public void run() {
        
        System.out.println("\t\t== Practica 01: Manejo de Errores ==\n");
        
        int opcionMenu = OPCION_SALIR;
        do {
            
            imprimirMenu();
            System.out.print("Opcion: ");
            opcionMenu = in.nextInt();  in.nextLine();
            System.out.println("");
            
            switch (opcionMenu) {
                case OPCION_MANEJO_ERRORES_DATOS_ENTRADA:
                    manejoErroresDatosEntrada();
                    break; 
                case OPCION_MANEJO_ERRORES_LEER_ARCHIVOS:
                    manejoErroresLeerArchivos();
                    break;
                case OPCION_MANEJO_ERRORES_ESCRIBIR_ARCHIVOS:
                    manejoErroresEscribirArchivos();
                    break;
                case OPCION_MANEJO_ERRORES_LEER_MATRIZ:
                    manejoErroresLeerMatriz();
                    break;
                case OPCION_SALIR:
                    System.out.println("Adios, hasta pronto.");
                    break;
                default:
                    System.out.println("[Opción no válida]");
                    
            }
                   
            
            System.out.println("");
            
        } while (opcionMenu != OPCION_SALIR);
        
    }
    
    private void imprimirMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println(" 1. Manejo de errores en datos de entrada.");
        System.out.println(" 2. Manejo de archivos (leer archivos y procesarlos).");
        System.out.println(" 3. Manejo de archivos (escribir en archivo numeros random).");
        System.out.println(" 4. Manejo de archivos (leer una matríz)");
    }
    
    /**
     * Proceso que ejemplifica el manejo de errores de los datos de entrada de
     * una aplicación. Así como las tecnicas más usuales de manejarlos.
     */
    public void manejoErroresDatosEntrada() {
        
        // Leemos el datos de entrada como un string, para poder determinar si
        // es un número el que se ingresó y no parar la ejecución de nuestra 
        // aplicación si es que no se ingresó un numero.
        System.out.println("Escriba un numero: ");
        String numeroStr = in.nextLine();  
        int numero = 0;
        try {
            numero = Integer.parseInt(numeroStr);
            System.out.printf("Usted ingreso el número %d \n", numero);
        }
        catch (Exception ex) {  // Mala práctica cachar la excepción general  :(
            System.out.println("Error al leer el número: " + ex.getMessage());
        }
        
        // Pedimos el dato (un número) hasta que nos proporcionen un valor correcto.
        int numeroOtro = 0;
        boolean numeroCorrecto = false;
        while (!numeroCorrecto) {
            System.out.print("Ingrese otro número: ");
            String numeroOtroStr = in.nextLine();
            try {
                numeroOtro = Integer.parseInt(numeroOtroStr);
                numeroCorrecto = true;
            }
            catch (NumberFormatException ex) {  // Es mejor cachar la excepción específica :)
                System.out.println("[Error en la entrada de datos. No es un número.]");
            }
        }
        System.out.println("El otro número ingresado fue: " + numeroOtro);
        
    }
    
    /**
     * Ejemplificación del manejo de errores al leer archivos y procesarlos.
     */
    public void manejoErroresLeerArchivos() {
        
        System.out.print("Ingrese la ruta del archivo a trabajar:");
        String rutaArchivo = in.nextLine();
        
        // Objeto que contiene la operación del procesamiento de los datos, así
        // como los resultados de la operación.
        Estadistica est1 = new Estadistica(rutaArchivo);
        
        try {
            est1.procesar();
        } 
        catch (EstadisticaException ex) {  // Exception que puede arrojar procesar()
            
            System.out.println(ex.getMessage());
            
            if (ex.getCause() != null) {  
                // Exception interna, qué provocó el error, en caso que haya un 
                // factor externo que haya provocado la exception.
                System.out.println(ex.getCause().getMessage());
            }
        }
        
        // Solo si registos correctos, imprimimos los resultados.
        if (est1.count() == 0) return;  // return anticipado del método.
        
        // Los resultados (estadísticos) del procesamiento.
        System.out.println("Count: " + est1.count());
        System.out.println("Sum:   " + est1.getSum());
        System.out.println("Min:   " + est1.getMin());
        System.out.println("Max:   " + est1.getMax());
        System.out.println("Avg:   " + est1.getAvg());
                
    }
    
    /**
     * Ejemplifica el manejo de errores al escribir archivos.
     */
    public void manejoErroresEscribirArchivos() {
        
        System.out.print("Ingrese el directorio a trabajar: ");
        String directorioBase = in.nextLine();
        
        // Inicializamos el objeto GeneradorRandoms, pero al inicializar el
        // objeto (llamada al constructor) vemos que puede arrojar exceptions,
        // por lo que hay que manejar los errores definidos en el contructor.
        // En caso de error, damos una salida anticipada del método, ya que 
        // todo el proceso posterior, depende del objeto generado.
        GeneradorRandoms randGen = null;
        try {
            randGen = new GeneradorRandoms("gen1", directorioBase);
        }
        catch (GeneradorRandomsException ex) {
            System.out.println(
                    "Error en iniciar Generador Randoms " + 
                    ex.getNombreGenerador() + ". " + ex.getMessage());
            return; 
        }
        catch (IllegalArgumentException ex) {
            System.out.println(
                    "Error en los parámetros para iniciar el Generador: " +
                    ex.getMessage());
            return;
        }
        
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = in.nextLine();
        System.out.print("Cantidad de numeros random a generar: ");
        int cantRandoms = in.nextInt(); in.nextLine();
        
        System.out.println("Generando...");
        float[] numerosRandomGenerados = null;
        try {
            numerosRandomGenerados = randGen.generar(cantRandoms, nombreArchivo);
        }
        catch (Exception ex) {
            
            // NOTA: Cachamos la excepción general solo para fines prácticos.
            
            System.out.println("StackTrace: ");
            for (StackTraceElement st : ex.getStackTrace()) {
                System.out.printf(
                        "- Linea %d de archivo %s de %s.%s \n", st.getLineNumber(), 
                        st.getFileName(), st.getClassName(), st.getMethodName());
            }
            
            String tipoEx = ex.getClass().getCanonicalName();
            System.out.printf(
                    "Error (%s) al generar: %s\n", tipoEx, ex.getMessage());
            if (ex.getCause() != null) {
                tipoEx = ex.getCause().getClass().getCanonicalName();
                System.out.printf(
                        "   Causa (%s): %s\n", tipoEx, ex.getCause().getMessage());
            }
            
        }
        
        // Si se realizó correctamente la ejecución (no exceptions), podemos
        // obtener el valor de retorno de GeneradorRandoms.generar().
        if (numerosRandomGenerados != null) {
            System.out.println("[Archivo con números random generado correctamente]");
        }
        
    }
    
    /**
     * Ejemplificación del manejo de errores al leer un archivo que contiene la
     * definición de una matríz.
     */
    public void manejoErroresLeerMatriz() {
        
        // Obtención del archivo de la aplicación.
        File dirApp = new File("").getAbsoluteFile();  
        
        System.out.println("Directorio: " + dirApp.getPath());
        System.out.print("Seleccione un archivo de la matriz que quier leer: ");
        String archivo = in.nextLine();
        System.out.print("Null on no number (y = yes, n = no): ");
        boolean nullOnNoNumber = 
                in.nextLine().trim().toLowerCase().contains("y");
        
        // TODO: Llamar la funcionalidad de MatrixReader para leer el archivo
        // y obtener la matriz con los números de los archivos.
        
    }
    
}
