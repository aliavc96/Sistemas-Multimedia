/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RectangularShape;

/**
 * La clase curva está casi sin implementar, pues ni dibuja ni edita bien.
 * Aún así si es posible, trataré de entender su funcionamiento y tratar de imple-
 * mentarla para la próxima entrega
 * @author Ana Alicia Vílchez Ceballos
 */
public class ACurva extends MyShape{

    private QuadCurve2D.Double curva = new QuadCurve2D.Double();
     //puntos para formar el ovalo
    private Point2D p1;
    private Point2D p2;
    private Point2D pc;
    
    //constructores
    public ACurva(){
        
    }
     public ACurva(Point2D p){
        p1 = p;
    }
     
    @Override
    public void formarFigura() {
        if(p1 != null){
           curva = new QuadCurve2D.Double(p1.getX(), p1.getY(), p1.getX(), p1.getY(), p1.getX(), p1.getY());
           pc = p1;
       }
    }

    @Override
    public void setLocation(Point2D point) {
      
    }

    @Override
    public void updateShape() {
       curva.setCurve(p1, pc, p2);
    }

    public QuadCurve2D.Double getCurva() {
        return curva;
    }

    public void setP1(Point2D p1) {
        this.p1 = p1;
    }

    public void setP2(Point2D p2) {
        this.p2 = p2;
    }

    public void setPc(Point2D pc) {
        this.pc = pc;
    }
    
    
    @Override
    public void paintShape(Graphics2D g){
          super.paintShape(g);
            //dibujamos la forma
        g.draw(curva);
    }
    
}
