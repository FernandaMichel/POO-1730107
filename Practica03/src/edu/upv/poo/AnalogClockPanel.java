package edu.upv.poo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Representa un JPanel en el que se dibuja un reloj análogo con la hora actual.
 * @author luisroberto
 */
public class AnalogClockPanel extends JPanel {
    
    /**
     * Tamaño del margen del relog (de la carátula) respecto al contenedor.
     */
    private static final int CLOCK_MARGIN = 10;
    
    /**
     * Tamaño por default del reloj.
     */
    private static final int PREFERRED_SIZE = 500;
    
    /**
     * Tiempo en miliseguntos de cada cuanto se va a hacer la actualización del
     * dibujo del reloj.
     */
    private static final int REFRESH_RATE = 10;
    
    /**
     * Inicializa una nueva instancia del JPanel.
     */
    public AnalogClockPanel() {
        setPreferredSize(new Dimension(PREFERRED_SIZE, PREFERRED_SIZE));
        lastHourRead = getHourStr();
        timer.start();
    }
    
    /**
     * ArrayList que contiene los listeners del reloj, a los que va a notificar
     * el cambio de hora.
     */
    private final ArrayList<ClockListener> clockListeners = new ArrayList<>();
    
    /**
     * Imagen en memoria correspondiente a la carátula del reloj.
     */
    private BufferedImage clockFaceBase;
    
    /**
     * Objeto Timer que se va a encargar de hacer la ejecución de la 
     * actualización del dibujo del reloj cada cierto tiempo (determinado por
     * la constante REFRESH_RATE).
     */
    private final Timer timer = new Timer(REFRESH_RATE, e -> update());
    
    /**
     * Contiene la última hora leida (en formato (HH:mm:ss).
     */
    private String lastHourRead;
    
    /**
     * Obtiene el tamaño del reloj dibujado, en función del tamaño del panel.
     * @return Tama;o del reloj.
     */
    public int getClockSize() {
        int sizeBase = getWidth() < getHeight() ? getWidth() : getHeight();
        return sizeBase - 2 * CLOCK_MARGIN;
    }
    
    /**zz
     * Obtiene la ubicación del centro del reloj.
     * @return La uibicación del centro del reloj.
     */
    public int getClockCenter() {
        return getClockSize() / 2 + CLOCK_MARGIN;
    }
    
    public int getHour12() {
        return Calendar.getInstance().get(Calendar.HOUR);
    }
    
    public int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    
    public int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    
    public int getSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
    
    /**
     * Obtiene la hora actual en formato de HH:mm:ss.
     * @return La hora actual en formato de HH:mm:ss.
     */
    public String getHourStr() {
        int h = getHour();
        int m = getMinute();
        int s = getSecond();
        return String.format("%02d:%02d:%02d", h, m, s);
    }
    
    /**
     * Agrega un nuevo listener del reloj, al que se le va a notificar el cambio
     * de hora.
     * @param l Objeto listener a agregar.
     */
    public void addClockListener(ClockListener l) {
        if (l == null) return;
        clockListeners.add(l);
    }
    
    /**
     * Remueve un listener del cambio de hora del reloj.
     * @param l Objeto listener a remover.
     */
    public void removeClockListener(ClockListener l) {
        if (l == null) return;
        clockListeners.remove(l);
    }
    
    /**
     * Notifica a los Listener el cambio de hora.
     */
    private void notifyHourChanged() {
        for (ClockListener l : clockListeners) {
            EventObject e = new EventObject(this);
            l.hourChanged(e);
        }
    }
    
    /**
     * Método propio del JPanel en el cual dibujamos dentro de él usando el 
     * parámetro Graphics que no envia.
     * @param g Objeto Graphics que nos sirve para dibujar.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawClockFaceBase(g2d);
        drawClockHands(g2d);
    }
    
    /**
     * Realiza el proceso de actualización y redibujo del JPanel si es que 
     * cambió la hora, además notifica a los Listeners este cambio de hora.
     */
    private void update() {
        String now = getHourStr();
        if (lastHourRead.equals(now)) return;
        lastHourRead = now;
        repaint();
        notifyHourChanged();
    }
    
    /**
     * Realiza el dibujo de la carátula del reloj. Si no se ha dibujado o se ha
     * cambiado el tamaño del JPanel, se crea la carátula del reloj en un
     * BufferedImage, para esta imagen generada de la carátula agregarse al 
     * JPanel para dibujarse.
     * @param g Objeto Graphics2D que nos servirá para dibujar la carátula.
     */
    private void drawClockFaceBase(Graphics2D g) {
        
        if (clockFaceBase == null || clockFaceBase.getWidth() != getWidth() ||
                clockFaceBase.getHeight() != getHeight()) {
            
            // Creación de un nuevo objeto BufferedImage que contendrá la 
            // carátula del reloj.
            clockFaceBase = (BufferedImage)createImage(getWidth(), getHeight());
            Graphics2D gcfb = clockFaceBase.createGraphics();
            gcfb.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Dibujo de la carátula (el circulo).
            gcfb.setColor(Color.CYAN);
            gcfb.fillOval(
                    CLOCK_MARGIN, CLOCK_MARGIN, getClockSize(), getClockSize());
            
            gcfb.setColor(Color.BLACK);
            
            // Dibujo de las marcas correspondientes a los minutos, en la carátula.
            for (int s = 0; s < 60; s++) {                
                float angleSec = (float)Math.PI / 30.0f * s;
                int minRadSec = s % 5 == 0 ?
                        getClockSize() / 2 - 10 : getClockSize() / 2 - 5;
                int maxRadSec = getClockSize() / 2;
                drawLineRadiusBase(gcfb, angleSec, minRadSec, maxRadSec);    
                
            if(getWidth() < 200 || getHeight() < 200) {
                gcfb.setFont(new Font("System", Font.ITALIC, 10));
                
                gcfb.drawString(getHourStr(), getClockCenter()-35,  getClockSize()-17);
            }               
            else {
                gcfb.setFont(new Font("System", Font.ITALIC, 17));
                
                gcfb.drawString(getHourStr(), getClockCenter()-35,  getClockSize()-27);
            }
                
                
            }
            gcfb.drawString("3", getClockSize()-13, getClockSize()/2 + 15);
            gcfb.drawString("6", getClockCenter()-3, getClockSize()-4);
            gcfb.drawString("9", (int)(getClockCenter()-getClockSize()/2.2), getClockSize()/2 + 15);
            gcfb.drawString("12", getClockCenter()-5, 35);
            
        }
        
        // Se dibuja la imagen de la carátula (del objeto BufferedImage) al Panel.
        g.drawImage(clockFaceBase, null, 0, 0);
        
    }
    
    /**
     * Realiza el dibujo de una linea desde el centro de la carátula del reloj.
     * @param g Objeto de tipo Graphics con el cual vamos a dibujar.
     * @param angle El ángulo de la linea a dibujar.
     * @param minRad Desde donde va a dibujar la linea (0 = desde el centro).
     * @param maxRad Tamaño de la linea.
     */
    private void drawLineRadiusBase(
            Graphics g, float angle, int minRad, int maxRad) {
        int center = getClockCenter();
        int x1 = center + (int)(minRad * Math.sin(angle));
        int y1 = center + (int)(minRad * Math.cos(angle));
        int x2 = center + (int)(maxRad * Math.sin(angle));
        int y2 = center + (int)(maxRad * Math.cos(angle));
        g.drawLine(x1, y1, x2, y2);
    }
    
    /**
     * Realiza el dibujo de las manecillas del reloj.
     * @param g Objeto de tipo Graphics con el que se va a dibujar.
     */
    private void drawClockHands(Graphics g) {
        
        float pi = (float)Math.PI;
        float piX3 = pi * 3;
        
        float second = getSecond();
        int secondRad = getClockSize() / 2 - (int)(getClockSize() * 0.05f);
        float secondAngle = piX3 - (pi / 30f * second);
        drawLineRadiusBase(g, secondAngle, 0, secondRad);
        
        float minuteF = getMinute() + second / 60.0f;
        int minuteRad = (int)(secondRad * 3.0f / 4.0f);
        float minuteAngle = piX3 - (pi / 30.0f * minuteF);
        drawLineRadiusBase(g, minuteAngle, 0, minuteRad);
        
        float hourF = getHour() + minuteF / 60.0f;
        int hourRad = secondRad / 2;
        float hourAngle = piX3 - (pi / 30.0f * hourF);
        drawLineRadiusBase(g, hourAngle, 0, hourRad);
        
    }
    
}
