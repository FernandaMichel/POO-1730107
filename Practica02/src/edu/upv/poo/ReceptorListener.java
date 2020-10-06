package edu.upv.poo;

import java.util.EventListener;

/**
 * Representa el contrato o protocolo que debe seguir un Listener del objeto
 * SubjectFrame para recibir una notificación de cambio de estado.
 * @author luisroberto
 */
public interface ReceptorListener extends EventListener {
    
    /**
     * Representa la recepción de una notificación/evento desde SubjectFrame.
     * @param e Argumento del evento que contiene información de este.
     */
    void notificacionRecibida(NotificacionEvent e);
    
}
