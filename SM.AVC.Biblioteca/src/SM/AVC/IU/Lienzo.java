/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.IU;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import javax.sound.sampled.Line;
import SM.AVC.Graficos.ALinea;
import SM.AVC.Graficos.APunto;
//import SM.AVC.Herramientas;
/**
 *
 * @author Ana Alicia Vílchez Ceballs   ETSIIT (UGR)
 */
public class Lienzo extends javax.swing.JPanel {

    /**
     * Creates new form Lienzo
     */

    Herramientas enUso; //es un enum que determina las herramientas
    //formas
    ArrayList <Shape> listaFormas;
    Point p1;
    Point p2;
    private Shape s; //figura actual
    private final Point2D dXY ; //distancia entre el punto x e y
     
    //atributos formas
    boolean editar;
    boolean alisado;
    boolean transparencia;
    private Color colorAsignado;
    boolean relleno;
    Stroke grosor;
    Stroke trazo;
    Composite comp; //este atributo definirá el grado de transparencia
    RenderingHints render; //para el alisado, mejorando la renderizacion de la figura
    private int gr; //aqui se almacena el valor del grosor
    
    
    public Lienzo() {
        initComponents();
        //valores por defecto
        colorAsignado = Color.BLACK;
        relleno = false;
        enUso = Herramientas.LAPIZ;
        listaFormas = new ArrayList();
        grosor = new BasicStroke((int)1);
        gr = 1;
        s = null;
        dXY = new Point(0, 0);
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        transparencia = false;
        alisado = false;
        render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        render.put(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        
    }
    
    //metodos gets
    public Herramientas getEnUso() {
        return enUso;
    }

    public Color getColorAsignado() {
        return colorAsignado;
    }

    public boolean isRelleno() {
        return relleno;
    }
    
    public boolean isEditar() {
        return editar;
    }

    public boolean isAlisado() {
        return alisado;
    }

    public boolean isTransparencia() {
        return transparencia;
    }

    public int getGrosor() {
        return gr;
    }
    //metodos set
   
    public void setEnUso(Herramientas enUso) {
        this.enUso = enUso;
    }

    public void setColorAsignado(Color colorAsignado) {
        this.colorAsignado = colorAsignado;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
    }

    public void setTransparencia(boolean transparencia) {
        this.transparencia = transparencia;
    }

    public void setGrosor(int gr) {
        grosor = new BasicStroke((float)gr);
        this.gr = gr;
    }
    
    
    
   
    //sobrecargamos el metodo paint (cuando hacemos repaint() llamamos a este metodo
    @Override
     public void paint(Graphics g){
        super.paint(g);
        //lo que hacemos es convertir el objeto graphics a un graphics2D
        //La clase Graphics2D hereda de la clase Graphics
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(colorAsignado); // le ponemos el color asignado a las figuras
        g2d.setStroke(grosor);
        if(transparencia) //si activamos transparencia se aplicara la composicion hecha en el initComponents
            g2d.setComposite(comp);
        if(alisado) //si activamos alisado, se aplicará la renderizacion
            g2d.setRenderingHints(render);
        
        //Para pintar e ir actualizando las figuras...
         for(Shape aForm : listaFormas){
             g2d.draw(aForm);
             if(relleno)
                g2d.fill(aForm);  
        }
    }
    
     
    private void createShape(){
        
        //System.out.print("herramienta en uso: " + enUso + "\n");
  
        if(p1 != null && enUso == Herramientas.LAPIZ){
            s = new APunto(p1);
            listaFormas.add(s);
        }
        else if(p1 == null || p2 == null ) 
            s = null;
        
        else{
            if (enUso == Herramientas.RECTANGULO){
                s = new Rectangle.Double();
               ((RectangularShape)s).setFrameFromDiagonal(p1, p1);
                listaFormas.add(s);
            }
            else if(enUso == Herramientas.OVALO){
                s = new Ellipse2D.Double();
                ((RectangularShape)s).setFrameFromDiagonal(p1, p1);
                listaFormas.add(s);
            }
            
            else if(enUso == Herramientas.RECTA){
                s = new ALinea(p1,p1);
                listaFormas.add(s);
            }
            else s = null; 
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
    
        if(editar){
            s = getSelectedShape(evt.getPoint()); 
            if(s != null){
                Point2D p = new Point2D.Double(s.getBounds().getX() - evt.getPoint().getX(),
                        s.getBounds().getY() - evt.getPoint().getY());
                if(s instanceof ALinea || s instanceof APunto)
                    p = new Point2D.Double(((Line2D)s).getX1() - evt.getPoint().getX(), 
                            ((Line2D)s).getY1() - evt.getPoint().getY());
                dXY.setLocation(p);
            }
        }
        else {
            p1 = evt.getPoint();
            createShape(); 

        }
        repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        if(editar){
            if(s!= null){
                Point2D p = new Point2D.Double(evt.getPoint().getX() + dXY.getX(), evt.getPoint().getY() + dXY.getY());
                setLocation(p, s);
            }
        }
        else{
            p2 = evt.getPoint();
            updateShape();
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
      
    }//GEN-LAST:event_formMouseReleased
    
    //Este es el método que nos devuelve la figura seleccionada comparando puntos
     private Shape getSelectedShape(Point2D p){
        for(Shape sh: listaFormas){
            if(sh.contains(p)){ 
                //System.out.print("Encontrado");
                return sh;
            }
        }
        return null;
    }
    
 
    public void setLocation(Point2D point, Shape s){
        if (s != null) {     //Si s no es null
            if (s instanceof ALinea){
                ((ALinea)s).setLocation(point);
            }
            else if(s instanceof APunto)
                ((APunto)s).setLocation(point);
                
            else if(s instanceof RectangularShape)
                ((RectangularShape)s).setFrame(point, 
                        new Dimension((int)((RectangularShape)s).getWidth(),
                                (int)((RectangularShape)s).getHeight()));
        }  
    }
    
    public void updateShape(){
        if(s instanceof ALinea)
            ((ALinea)s).update(p1,p2);
        else if(s instanceof APunto)
            ((APunto)s).update(p1);
        else if (s instanceof RectangularShape)
            ((RectangularShape)s).setFrameFromDiagonal(p1, p2);
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
