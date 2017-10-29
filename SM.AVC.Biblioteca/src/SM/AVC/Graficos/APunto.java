/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Ana Alcia Vílchez Ceballos,  ETSIIT (UGR)
 */
public class APunto extends Line2D.Double{
    
    
     public APunto(Point2D p1){
        super(p1, p1);
    }
     
    public boolean isNear( Point2D p){
        return this.ptSegDist(p) <= 3.0;
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

    public void update(Point2D p1) {
        this.setLine(p1, p1);
    }
}
