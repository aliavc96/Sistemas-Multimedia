/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * Esta clase se encarga de formar el rectángulo y de poner los atributos que se establecen en
 * la clase abstracta myShape
 * Posee un atributo que se corresponde con un objeto de la clase Rectangle.Double
 * el cual será esencial para formar el rectángulo y dos puntos a partir de los cuales se realizará
 * 
 * @author Alicia Vílchez Ceballos
 */
public class ARectangulo extends MyShape{
    
    private Rectangle.Double rectangulo = new Rectangle.Double();;
    Point2D p1;
    Point2D p2;
    
    
    /**
     * Establece el primer punto del rectángulo a formar
     * @param Point2D
     */
    public ARectangulo(Point2D p){
        p1 = p;
    }
    
    public ARectangulo(){
    }
    
    /**
     * Este método empieza formando la figura, aunque no la acaba pues el segundo 
     * punto todavía no quedaría estableciddo. Lo que establecemos es el comienzo de la diagonal
     * del rectángulo
     */
    @Override
     public void formarFigura(){
       if(p1 != null){
           ((RectangularShape)rectangulo).setFrameFromDiagonal(p1, p1);
       }
    }
     /**
      * 
      * @return Rectangle2D.Double 
      */
    public Rectangle2D.Double getRectangulo() {
        return rectangulo;
    }
     
    /**
     * Este método pinta el rectángulo gracias al parámetro que le pasamos 
     * y establece los atributos fijados previamente
     * Además establece el gradiente horizontal y vertical
     * @param Graphics2D 
     */
   @Override
    public void paintShape(Graphics2D g){
        //va a llamar a la clase de la que hereda para establecer los atributos
        super.paintShape(g);
        //dibujamos la forma
        g.draw(rectangulo);
        //estos atributos los aplicamos aqui para que cojan el color después de hacer el draw
        //para rellenar la figura con el nuevo color específico
        if(getTipoG() == "relleno"){
            g.setPaint(getColorRelleno());
            g.fill(rectangulo);
        }
        
        else if( getTipoG() == "gradienteV"){
            if(p1 != null && p2 != null){
                GradientPaint degradado = new GradientPaint((float)p1.getX(), (float)p1.getY(),
                         getColorAsignado(), (float)p2.getX(), (float)p1.getX() , getColorRelleno());
                g.setPaint(degradado);
                g.fill(rectangulo);
           }
        }
        else if(getTipoG() == "gradienteH" ){
           if(p1 != null && p2 != null){
                GradientPaint degradado = new GradientPaint((float)p1.getX(), (float)p1.getY(), getColorAsignado(), 
                         (float)p1.getX() , (float)p2.getY(), getColorRelleno());
                g.setPaint(degradado);
                g.fill(rectangulo);
           }
        }
     }
    
    
    /**
     * Este método se encarga de sitúar el rectángulo en el punto pasado como argumento 
     * (manteniendo el ancho y alto)
     * @param point2D 
     */
    @Override
     public void setLocation(Point2D point){
           ((RectangularShape)rectangulo).setFrame(point, 
                new Dimension((int)((RectangularShape)rectangulo).getWidth(),
                (int)((RectangularShape)rectangulo).getHeight())); 
               
    }
     /**
      * Este método termina de formar el rectangulo estableciendo la primera 
      * y la última coordenada de la diagonal del mismo 
      */
    @Override
     public void updateShape(){
       ((RectangularShape)rectangulo).setFrameFromDiagonal(p1, p2);
    }

    /**
     * 
     * @param Point2D 
     */
    public void setP1(Point2D p1) {
        this.p1 = p1;
    }
    
    /**
     * 
     * @param Point2D 
     */
    public void setP2(Point2D p2) {
        this.p2 = p2;
    }
      
      
     
}



