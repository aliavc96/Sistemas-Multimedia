/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Ana Alicia Vílchez Ceballos
 */
public class ALinea2 extends MyShape{

    Line2D.Double linea;
    Point2D p1 ;
    Point2D p2;
    
    // constructor por defecto y por parametros
    public ALinea2(){
        
    }
    public ALinea2(Point2D p){
        p1 = p;
        
    }
    /**
     * Empezamos dándole el primer punto donde se empezará a formar la linea
     */
    @Override
    public void formarFigura() {
        linea = new Line2D.Double(p1, p1);
    }

    public Line2D.Double getLinea() {
        return linea;
    }
    
    
    
    /**
     * updateShape emplea el método setLine() para actualizar la creación de la figura
     * a partir del primer y segundo punto
     */
    @Override
     public void updateShape() {
        linea.setLine(p1, p2);
    }
     /**
      * Este método aplica los atributos correspndietes fijados anteriorrmente y
      * llama a draw de Graphics2D para dibujar la linea
      * @param Graphics2D 
      */
    @Override
    public void paintShape(Graphics2D g){
        super.paintShape(g);
        g.draw(linea);
    }
    
    public void setP1(Point2D p1) {
        this.p1 = p1;
    }

    public void setP2(Point2D p2) {
        this.p2 = p2;
    }
    
    /**
     * Como la clase line2D no tiene método setLocation, tenemos que crearlo nosotros
     * @param point2D
     * 
     */
    @Override
    public void setLocation(Point2D p) {
        double dx = p.getX()- linea.getX1(); 
        double dy = p.getY()- linea.getY1();  
        Point2D newp2 = new Point2D.Double(linea.getX2()+dx,linea.getY2()+dy);    
        linea.setLine(p,newp2);    
    }
      
    
}
