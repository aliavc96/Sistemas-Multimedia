/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import SM.AVC.Graficos.MyShape;
/**
 *
 * @author Ana Alcia Vílchez Ceballos,  ETSIIT (UGR)
 */
public class ALinea extends Line2D.Double{
    
    
    public ALinea(){
        super();
    }
    
    public ALinea(Point2D p1,Point2D p2){
        super(p1, p2);
    }
    
    public boolean isNear(Point2D p){
        return this.ptLineDist(p) <= 2.0;
    }
    
    public boolean contains(Point2D p) {
            return isNear(p);
        
    }
    
    public void setLocation(Point2D p) {
        double dx = p.getX()-this.getX1(); 
        double dy = p.getY()-this.getY1();  
        Point2D newp2 = new Point2D.Double(this.getX2()+dx,this.getY2()+dy);    
        this.setLine(p,newp2);    
    }
    
    public void update(Point2D p1, Point2D p2) {
        this.setLine(p1, p2);
    }
}
