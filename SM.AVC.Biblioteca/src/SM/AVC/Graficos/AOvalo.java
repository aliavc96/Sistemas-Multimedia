/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * Esta clase se encarga de formar la elipse y de poner los atributos que se establecen en
 * la clase abstracta myShape
 * Posee un atributo que se corresponde con un objeto de la clase Ellipse.Double
 * el cual será esencial para formar el "círculo" y dos puntos a partir de los cuales se realizará
 * 
 * @author Alicia Vílchez Ceballos
 */
public class AOvalo extends MyShape{
    
    private Ellipse2D.Double ovalo;
    
    //puntos para formar el ovalo
    private Point2D p1;
    private Point2D p2;
    
    public AOvalo(){
        
    }
    
    public AOvalo(Point2D p){
        p1 = p;
    }
    /**
     * Este método establece la primera coordenada del diámetro del circulo
     * (uno de ellos). Como hereda de RectangularShape() el procedimiento 
     * es el mismo que con el del rectángulo
     */
    @Override
     public void formarFigura(){
       if(p1 != null){
           ovalo = new Ellipse2D.Double();
           ((RectangularShape)ovalo).setFrameFromDiagonal(p1, p1);
       }
    }

     /**
      * 
      * @return Ellipse2D.Double
      */
    public Ellipse2D.Double getOvalo() {
        return ovalo;
    }
    
     /**
     * Este método pinta la elipse gracias al parámetro que le pasamos 
     * y establece los atributos fijados previamente
     * Además establece el gradiente horizontal y vertical
     * @param Graphics2D 
     */ 
    @Override
    public void paintShape(Graphics2D g){
        //va a llamar a la clase de la que hereda para establecer los atributos
        super.paintShape(g);
        //dibujamos la forma
        g.draw(ovalo);
        if(getTipoG() == "relleno"){
            g.setPaint(getColorRelleno());
            g.fill(ovalo); 
        }
       else if( getTipoG() == "gradienteV"){
           if(p1 != null && p2 != null){
                GradientPaint degradado = new GradientPaint((float)p1.getX(), (float)p1.getY(),
                         getColorAsignado(), (float)p2.getX(), (float)p1.getX() , getColorRelleno());
                g.setPaint(degradado);
                g.fill(ovalo);
           }
        }
        else if(getTipoG() == "gradienteH" ){
            if(p1 != null && p2 != null){
                GradientPaint degradado = new GradientPaint((float)p1.getX(), (float)p1.getY(), getColorAsignado(), 
                         (float)p1.getX() , (float)p2.getY(), getColorRelleno());
                g.setPaint(degradado);
                g.fill(ovalo);
           }
        }
         
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
     
    /**
      * Este método termina de formar la elipse estableciendo la primera 
      * y la última coordenada de la diagonal del mismo 
      */
    @Override
    public void updateShape(){
       ((RectangularShape)ovalo).setFrameFromDiagonal(p1, p2);
       //repaint();
    }
    
     /**
     * Este método se encarga de sitúar la elipse en el punto pasado como argumento 
     * (manteniendo el ancho y alto) como con el rectángulo
     * @param point2D 
     */
    @Override
    public void setLocation(Point2D point){
           ((RectangularShape)ovalo).setFrame(point, 
                new Dimension((int)((RectangularShape)ovalo).getWidth(),
                (int)((RectangularShape)ovalo).getHeight()));
        
    }

  
}
