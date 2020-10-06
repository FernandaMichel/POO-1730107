package edu.upv.poo;

import java.util.EventObject;

/**
 * Representa la información del evento correspondiente a la recepción de la
 * notificación.
 * @author luisroberto
 */
public class NotificacionEvent extends EventObject {
    
    private String algunDato;
    
    public NotificacionEvent(Object source, String algunDato) {
        super(source);
        this.algunDato = algunDato;
    }
    
    public String getAlgunDato() { return algunDato; }
    
}
