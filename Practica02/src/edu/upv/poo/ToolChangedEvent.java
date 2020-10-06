package edu.upv.poo;

import java.util.EventObject;

/**
 * Representa el argumento correspondiente al evento del cambio de herramienta 
 * de dibujo de PaintToolboxFrame.
 * @author luisroberto
 */
public class ToolChangedEvent extends EventObject {
    
    private final String tool;
    
    /**
     * Inicializa una nueva instancia de ToolChangedEvent.
     * @param source Objeto de donde se dispara el evento.
     * @param tool La nueva herramienta seleccionada.
     */    
    public ToolChangedEvent(Object source, String tool) {
        super(source);
        this.tool = tool;
    }
    
    /**
     * Obtiene la nueva herramienta seleccionada.
     * @return La herramienta seleccionada.
     */
    public String getTool() { return tool; }
    
}
