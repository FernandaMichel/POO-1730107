 package edu.upv.poo;

import java.awt.BorderLayout;
import java.util.EventObject;
import javax.swing.JFrame;

/**
 * Clase principal de la aplicación.
 * @author luisroberto
 */
public class Practica03 
        extends JFrame {

    /**
     * Titulo de la ventana.
     */
    public static final String TITLE = "My Analog Clock";
    
    /**
     * Método principal de la ejecución de la aplicación.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Practica03().run();
    }
    
    /**
     * Inicializa una nueva instancia de Practica03, en el cual se define el
     * JFrame (la ventana).
     */
    public Practica03() {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(analogClockPanel, BorderLayout.CENTER);
        pack();
        addClockListeners();
    }
    
    private AnalogClockPanel analogClockPanel = new AnalogClockPanel();
    
    /**
     * Agrega los listeners al analogClockPanel.
     */
    private void addClockListeners() {
        
        // Creación de un objeto listener "al vuelo", implementando la 
        // interface y su respectivo método (forma tradicional de Java).
        ClockListener l1 = new ClockListener() {
            @Override
            public void hourChanged(EventObject e) {
                System.out.println("Llamando desde l1");
            }            
        };
        analogClockPanel.addClockListener(l1);
        
        // Creación de objeto listener con el uso de una expresión lambda.
        // Esto solo se puede hacer cuando la interface tiene solo un método.
        ClockListener l2 = (EventObject e) -> {
            System.out.println("Llamando desde l2");
        };
        analogClockPanel.addClockListener(l2);
               
        // Creación de objeto listener con el uso de una expresión lambda.
        // Esta forma solo se puede hacer cuando la definición del método
        // de la interface es de una sola linea/statement.
        ClockListener l3 = e -> System.out.println("Llamando desde l3");
        analogClockPanel.addClockListener(l3);
        
        // Creación de objeto listener con el uso de una expresión lambda, pero
        // definida directamente donde se envía el parámetro de addClockListner.
        analogClockPanel.addClockListener(e -> {
            AnalogClockPanel c = (AnalogClockPanel)e.getSource();
            System.out.println("Hora: " + c.getHourStr());
        });
        
        // También podemos enviar métodos ya definidos en la clase (o en otros
        // objetos) como expresión lambda, siempre y cuando tengan el mismo 
        // tipo de dato de retorno y los mismos tipos de parámetros.
        analogClockPanel.addClockListener(this::horaCambiada);
        analogClockPanel.addClockListener(this::hourChanged);
                
    }
    
    /**
     * Ejecución principal de la aplicación.
     */
    public void run() {
        setVisible(true);
    }

    public void hourChanged(EventObject e) {
        AnalogClockPanel acp = (AnalogClockPanel)e.getSource();
        setTitle(TITLE + " | " + acp.getHourStr());
    }
    
    private void horaCambiada(EventObject e) {
        AnalogClockPanel c = (AnalogClockPanel)e.getSource();
        System.out.println("Segundo obtenido: " + c.getSecond());
    }
        
}
