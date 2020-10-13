package edu.upv.poo;

import java.util.EventObject;

/**
 * Representa un Listener del cambio de hora del Reloj (AnalogClockPanel).
 * @author luisroberto
 */
public interface ClockListener {
    
    /**
     * Evento correspondiente al cambio de hora del reloj.
     * @param e Event argument.
     */
    void hourChanged(EventObject e);
    
}
