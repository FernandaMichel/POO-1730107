package edu.upv.poo;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Contiene la definición del Frame correspondiente a la funcionalidad del 
 * Paint.
 * @author luisroberto
 */
public class PaintFrame 
        extends JFrame
        implements ToolsListener {
    
    private final PaintPanel paintPanel = new PaintPanel();
    private final PaintToolboxFrame paintToolboxFrame;
    
    /**
     * Inicializa una nueva instancia de PaintFrame especificando el Frame
     * del Toolbox de donde se van a cambiar las herramientas de dibujo de la
     * aplicación.
     * @param paintToolboxFrame Frame que representa la caja de herramientas de
     * dibujo de la aplicación.
     */
    public PaintFrame(PaintToolboxFrame paintToolboxFrame) {
        super("My Paint");
        this.paintToolboxFrame = paintToolboxFrame;
        paintToolboxFrame.addToolsListener(this);
        add(paintPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 780);
        paintToolboxFrame.setVisible(true);
    }
    
    /**
     * Inicializa una nueva instancia de PaintFrame la cual no se tiene que 
     * especificar el Frame de las herramientas de dibujo ya que se crea en este
     * constructor.
     */
    public PaintFrame() {
        this(new PaintToolboxFrame());
    }

    /**
     * Método de tipo event handler correspondiente al cambio de color en las
     * herramientas de dibujo.
     * @param e El event argument con la información del evento.
     */
    @Override
    public void colorChanged(ColorChangedEvent e) {
        System.out.println("Cambiando el color a " + e.getColorName());
        paintPanel.setActualColor(e.getColor());
    }

    /**
     * Método de tipo event handler correspondiente al cambio de tamaño del 
     * trajo del dibujo.
     * @param e Event argument con la información del evento.
     */
    @Override
    public void sizeChanged(SizeChangedEvent e) {
        System.out.println("Cambiando el size a " + e.getSize());
        paintPanel.setActualPointSize(e.getSize());
    }

    /**
     * Método de tipo event handler correspondiente al cambio de herramienta de
     * dibujo.
     * @param e Event argument con la infromación del evento. 
     */
    @Override
    public void toolChanged(ToolChangedEvent e) {
        System.out.println("Cambiando tool a " + e.getTool());
        paintPanel.setActualTool(e.getTool());
    }
    
}
