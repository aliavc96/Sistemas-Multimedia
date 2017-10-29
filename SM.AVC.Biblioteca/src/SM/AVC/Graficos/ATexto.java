/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Esta clase se encargará de "pintar" el texto en el lienzo gracias al método 
 * drawString de Graphics2D a
 * @author Alicia Vílchez Ceballos
 */
public class ATexto extends MyShape{

    //Font fuente = new Font("Arial", Font.BOLD | Font.ITALIC, 45);
    Point2D p; //será el punto con el que se establecerá la funete
    String texto;
    
    public ATexto(){
        //fuente = new Font("Arial", Font.BOLD | Font.ITALIC, 45);
    }
    /**
     * Se establece el punto donde empieza a implementarse el texto (de izquierda a derecha)
     * @param point2D 
     */
    public ATexto(Point2D p1){
        p = p1;
    }
    /**
     * Este método no se utiliza para implementar texto
     */
    @Override
    public void formarFigura() {
        
    }

    /**
     * Este método se encuentra en construcción
     */
    @Override
    public void setLocation(Point2D point) {
        
    }

    /**
     * Este método no se utiliza para implementar texto
     */
    @Override
    public void updateShape() {
        
    }
    /**
    * Se establece el texto a escribir en el lienzo
    * @param String 
    */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    /**
     * Se establece el punto donde comenzará el texto
     * @param Point2D
     */
    public void setP(Point2D p) {
        this.p = p;
    }
    
    /**
     * En este método llamamos método paintShape de la clase myShape
     * para establecer los atributos tales como la transparencia, la fuente, el color,
     * el grosor...
     * y posteriormente llamamos al método drawString() del parámetro para poder pintar el texto
     * en caso de que este contenga algo
     * @param Graphics2D 
     */
    @Override
    public void paintShape(Graphics2D g){
        super.paintShape(g);
            //dibujamos la forma
        if(texto != null){
          g.drawString(texto, (int)p.getX(), (int)(p.getY()));
        }
     }
    
    
}
