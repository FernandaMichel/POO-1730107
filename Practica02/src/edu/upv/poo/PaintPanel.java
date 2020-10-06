package edu.upv.poo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Definición de un objeto JPanel en el que se pueda dibujar.
 * @author luisroberto
 */
public class PaintPanel extends JPanel {
    
    // En estos array list se van a ir guardando los trazos dibujados para
    // poderlos dibujar en cada repaint().
    private final ArrayList<TrazoPencil> trazosPencil = new ArrayList<>();
    private final ArrayList<Point> trazosLines = new ArrayList<>();
    private final ArrayList<Point> trazosCircle = new ArrayList<>();
    private final ArrayList<Point> trazosRectangle = new ArrayList<>();
    private final ArrayList<Color> pointsColors = new ArrayList<>(); 
        private final ArrayList<Integer> linesSize = new ArrayList<>();

    
    private final PaintPanelMouseEvents mouseEvents = 
            new PaintPanelMouseEvents();
    
    private Color actualColor = Color.BLACK;
    private String actualTool = Tool.PENCIL;
    private String actualToolLine = Tool.LINE;
    private String actualToolCircle = Tool.CIRCLE;
    private String actualToolRectangle = Tool.RECTANGLE;
    private int actualPointSize = 5;
    
    /**
     * Inicializa una nueva instancia de PaintPanel.
     */
    public PaintPanel() {
        setBackground(Color.WHITE);
        addMouseListener(mouseEvents);
        addMouseMotionListener(mouseEvents);
    }
    
    public Color getActualColor() { return actualColor; }
    
    public void setActualColor(Color actualColor) {
        this.actualColor = actualColor;
    }
    
    public String getActualTool() { return actualTool; }
    
    public void setActualTool(String actualTool) {
        this.actualTool = actualTool;
    }
    
    public String getActualToolLine() { return actualToolLine;}
    
    public void setActualToolLine(String actualToolLine){
        this.actualToolLine = actualToolLine;
    }
    
    public String getActualToolCircle() { return actualToolCircle;}
    
    public void setActualToolCircle(String actualToolCircle) {
        this.actualToolCircle = actualToolCircle;
    }
    
    public String getActualToolRectangle() { return actualToolRectangle;}
    
    public void setActualToolRectangle(String actualToolRectangle){
        this.actualToolRectangle = actualToolRectangle;
    }
    
    public int getActualPointSize() { return actualPointSize; }
    
    public void setActualPointSize(int actualPointSize) {
        this.actualPointSize = actualPointSize;
    }
    

    
    /**
     * Método que nos brinda la funcionalidad de dibujar dentro del JPanel
     * usando el objeto Graphics.
     * @param g Objeto que nos permite dibujar.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        drawTrazosPencil(g2d);
        drawTrazosLines(g2d);
        drawTrazosCircle(g2d);
        drawTrazosRectangle(g2d);
    }
    
    private void drawTrazosPencil(Graphics2D g) {
        for (TrazoPencil trazo : trazosPencil) {
            trazo.draw(g);
        }
    }
    
    private void drawTrazosLines(Graphics2D g) {
        for (int i = 0; i < trazosLines.size() - 1; i+=2) {
            Point inicio = trazosLines.get(i);
            Point fin = trazosLines.get(i + 1);
            Color c = pointsColors.get(i);      
            g.setColor(c);            
            g.drawLine(inicio.x, inicio.y, fin.x, fin.y);
        }  
    }
    
    private void drawTrazosCircle(Graphics2D g){
         for (int i = 0; i < trazosCircle.size() - 1; i+=2) {
            Point inicio = trazosCircle.get(i);
            Point fin = trazosCircle.get(i + 1);
            Color c = pointsColors.get(i);      
            g.setColor(c);            
            double dist = Math.sqrt(Math.pow((fin.x-inicio.x), 2)
                    +Math.pow(fin.y-inicio.y ,2));
            
            g.drawOval(inicio.x, inicio.y, (int)dist, (int)dist);
        } 
    }
        
    private void drawTrazosRectangle(Graphics2D g){
        for (int i = 0; i < trazosRectangle.size() - 1; i+=2) {
            Point inicio = trazosRectangle.get(i);
            Point fin = trazosRectangle.get(i + 1);
            Color c = pointsColors.get(i);      
            g.setColor(c);            
            g.drawRect(inicio.x, inicio.y, fin.x, fin.y);
        }
    }
    
    /**
     * Definición de un objeto trazo que representa a una linea dibujada en el 
     * JPanel.
     */
    class TrazoLine {
        
        private Point point1;
        private Point point2;
        private Color color;
        private int size;
        
        public TrazoLine(Point point1, Point point2, Color color, int size) {
            this.point1 = point1;
            this.point2 = point2;
            this.color = color;
            this.size = size;
        }

        

        public Point getPoint1() { return point1; }

        public Point getPoint2() { return point2; }

        public Color getColor() { return color; }

        public int getSize() { return size; }
        
        public int getX1() { return (int)point1.getX(); }
        
        public int getY1() { return (int)point1.getY(); }
        
        public int getX2() { return (int)point2.getX(); }
        
        public int getY2() { return (int)point2.getY(); }
        
        public void draw(Graphics2D g) {
            g.setColor(getColor());
            g.setStroke(new BasicStroke(getSize()));
            g.drawLine(getX1(), getY1(), getX2(), getY2());
        }
        
    }
    
    /**
     * Definición de un objeto que representa un trazo de un lápiz, en este caso
     * el de un punto, ya que el comportamiento del trazo del lapiz es dibujar
     * varios puntos.
     */
    class TrazoPencil{
        
        private Point point;
        private Color color;
        private int size;
        
        public TrazoPencil(Point point, Color color, int size) {
            this.point = point;
            this.color = color;
            this.size = size;
        }
        
        public Point getPoint() { return point; }
        
        public Color getColor() { return color; }
        
        public int getSize() { return size; }
        
        public int getX() { return (int)point.getX(); }
        
        public int getY() { return (int)point.getY(); }
        
        public void draw(Graphics2D g) {
            g.setColor(getColor());
            g.fillOval(getX(), getY(), getSize(), getSize());
        }        
        
    }
    
    
        class TrazoCircle {
        
        private Point point1;
        private Point point2;
        private Color color;
        private int size;
        
        public TrazoCircle (Point point1, Point point2, Color color, int size) {
            this.point1 = point1;
            this.point2 = point2;
            this.color = color;
            this.size = size;
        }


        public Point getPoint1() { return point1; }

        public Point getPoint2() { return point2; }

        public Color getColor() { return color; }

        public int getSize() { return size; }
        
        public int getX1() { return (int)point1.getX(); }
        
        public int getY1() { return (int)point1.getY(); }
        
        public int getX2() { return (int)point2.getX(); }
        
        public int getY2() { return (int)point2.getY(); }
        
        public void draw(Graphics2D g) {
            g.setColor(getColor());
            g.setStroke(new BasicStroke(getSize()));
            g.drawOval(getX1(), getY1(), getX2(), getY2());
        }
        
    }
        
        
     class TrazoRectangle {
        
        private Point point1;
        private Point point2;
        private Color color;
        private int size;
        
        public TrazoRectangle(Point point1, Point point2, Color color, int size) {
            this.point1 = point1;
            this.point2 = point2;
            this.color = color;
            this.size = size;
        }

        public Point getPoint1() { return point1; }

        public Point getPoint2() { return point2; }

        public Color getColor() { return color; }

        public int getSize() { return size; }
        
        public int getX1() { return (int)point1.getX(); }
        
        public int getY1() { return (int)point1.getY(); }
        
        public int getX2() { return (int)point2.getX(); }
        
        public int getY2() { return (int)point2.getY(); }
        
        public void draw(Graphics2D g) {
            g.setColor(getColor());
            g.setStroke(new BasicStroke(getSize()));
            g.drawRect(getX1(), getY1(), getX2(), getY2());
        }
        
    }       
    
    /**
     * Clase que contiene la definición de los métodos event handlers del cursor
     * del JFrame (PaintPanel) actual.
     */
    class PaintPanelMouseEvents implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.printf(
                    "PaintPanel MouseClicked! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
            if (actualTool.equals(Tool.PENCIL)) {
                TrazoPencil trazo = new TrazoPencil(
                        e.getPoint(), actualColor, actualPointSize);
                trazosPencil.add(trazo);
            }if(actualTool.equals(Tool.LINE)){
                trazosLines.add(e.getPoint());
                pointsColors.add(actualColor);
                linesSize.add(actualPointSize);
            repaint();
            }if(actualTool.equals(Tool.RECTANGLE)){
                trazosRectangle.add(e.getPoint());
                pointsColors.add(actualColor);
                linesSize.add(actualPointSize);
            repaint();
            }    
            if(actualTool.equals(Tool.CIRCLE)){
                trazosCircle.add(e.getPoint());
                pointsColors.add(actualColor);
                linesSize.add(actualPointSize);
            repaint();
            }
            repaint();
            
            System.out.printf(
                    "PaintPanel MouseClicked! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
            if (actualToolLine.equals(Tool.LINE)) {
                
            }
            repaint();
            
      
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.printf(
                    "PaintPanel MousePressed! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.printf(
                    "PaintPanel MouseReleased! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.printf(
                    "PaintPanel MouseEntered! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.printf(
                    "PaintPanel MouseExit! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.printf(
                    "PaintPanel MouseDragged! @ %f, %f \n", 
                    e.getPoint().getX(), e.getPoint().getY());
            if (actualTool.equals(Tool.PENCIL)) {
                TrazoPencil trazo = new TrazoPencil(
                        e.getPoint(), actualColor, actualPointSize);
                trazosPencil.add(trazo);
            }
            repaint();
                 
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }
        
    }
    
}  // end class
