package edu.upv.poo;

import java.util.EventListener;

/**
 * Representa el contrato o protocolo a seguir correspondiente al Listener u
 * Observer de los eventos relacionados con las herramientas de dibujo de
 * PaintToolboxFrame.
 * @author luisroberto
 */
public interface ToolsListener extends EventListener {
    
    /**
     * Evento del cambio de color
     * @param e Event argument con la información del cambio de color.
     */
    void colorChanged(ColorChangedEvent e);
    
    /**
     * Evento del cambio de tamaño del trazo.
     * @param e Event argument con la infomación del cambio de tamaño del trazo.
     */
    void sizeChanged(SizeChangedEvent e);
    
    /**
     * Evento del cambio de herramienta de dibujo.
     * @param e Event argument con la información del cambio de herramienta.
     */
    void toolChanged(ToolChangedEvent e);
    
}
