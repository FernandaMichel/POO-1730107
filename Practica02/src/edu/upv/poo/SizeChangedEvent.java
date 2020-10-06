package edu.upv.poo;

import java.util.EventObject;

/**
 * Representa el argumento correspondiente al evento del cambio de tamaño del
 * trazo de PaintToolboxFrame.
 * @author luisroberto
 */
public class SizeChangedEvent extends EventObject {
    
    private final int size;
    
    /**
     * Inicializa una nueva instancia de SizeChangedEvent.
     * @param source Objeto de donde se dispara el evento.
     * @param size El nuevo tamaño del trazo.
     */
    public SizeChangedEvent(Object source, int size) {
        super(source);
        this.size = size;
    }
    
    /**
     * Obtiene el nuevo tamaño del trazo.
     * @return El tamaño del trazo.
     */
    public int getSize() { return size; }
    
}
