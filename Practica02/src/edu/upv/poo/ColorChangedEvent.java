package edu.upv.poo;

import java.awt.Color;
import java.util.EventObject;

/**
 * Representa el argumento correspondiente al evento del cambio de color de 
 * PaintToolboxFrame.
 * @author luisroberto
 */
public class ColorChangedEvent extends EventObject {
    
    private final Color color;
    private final String colorName;
    
    /**
     * Inicializa una nueva instancia de ColorChangedEvent.
     * @param source Objeto de donde se dispara el evento.
     * @param color Nuevo color, al que se cambió.
     * @param colorName El nombre del color al que se cambió.
     */
    public ColorChangedEvent(Object source, Color color, String colorName) {
        super(source);
        this.color = color;
        this.colorName = colorName;
    }
    
    /**
     * Obtiene el color al que se cambió.
     * @return El color.
     */
    public Color getColor() { return color; }
    
    /**
     * Obtiene el nombre del color al que se cambió.
     * @return El nombre del color.
     */
    public String getColorName() { return colorName; }
    
}
