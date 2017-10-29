/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.IU;

import SM.AVC.Graficos.ACurva;
import SM.AVC.Graficos.ALinea;
import SM.AVC.Graficos.APunto;
import SM.AVC.Graficos.ARectangulo;
import SM.AVC.Graficos.ALinea2;
import SM.AVC.Graficos.AOvalo;
import SM.AVC.Graficos.ATexto;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana Alicia Vílchez Ceballos
 */
public class LienzoProyecto extends javax.swing.JPanel {

    Herramientas enUso = Herramientas.LAPIZ; //es un enum que determina las herramientas
    //formas y componentes
    
    Point2D p1 = null;
    Point2D p2 = null;
    
    //formas:
    private ARectangulo rectangulo =  new ARectangulo();
    private AOvalo ovalo = new AOvalo();
    private ALinea2 linea = new ALinea2();
    private ALinea2 punto = new ALinea2();
    private ACurva curva = new ACurva();
    private ATexto texto = new ATexto();
    private BufferedImage image;
    //Para el editado
    private Point2D dXY = new Point(0, 0);
    
   //atributos formas
    boolean editar = false;
    boolean alisado = false;
    private Color colorAsignado = Color.BLACK;
    private Color colorRelleno = Color.BLACK;

    Stroke grosor = new BasicStroke(1);
    Stroke trazo;
    Composite comp; //este atributo definirá el grado de transparencia
    RenderingHints render; //para el alisado, mejorando la renderizacion de la figura
    private int gr = 1; //aqui se almacena el valor del grosor
    private boolean discontinuidad = false;
    private boolean figuraCreada = false;
    //para los degradados 
    String tipoGradiente = null;
    public float nivelTransparencia = 1;
    private boolean imagenAbierta = false;
    //para el texto
    private String tipoFuente;
    String text = "";
    /**
     * Creates new form LienzoProyecto
     */
    
    public LienzoProyecto() {
        initComponents();
        image = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i< image.getWidth(); i++){
            for(int j=0; j < image.getHeight(); j++)
                image.setRGB(i, j, Color.WHITE.getRGB());
        }
        repaint();
    }
    
    @Override
     public void paint(Graphics g){

        //super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(image!=null) {
            g.drawImage(image, 0, 0, this);  
        }
        metodoDraw(g2d);
    }
     
    private void createShape(){
           
            rectangulo = null;
            ovalo = null;
            linea = null;
            punto = null;
            curva = null;
            texto = null;
    
            if (enUso == Herramientas.RECTANGULO){
                rectangulo = new ARectangulo(p1);
                rectangulo.formarFigura();
                rectangulo.setColorAsignado(colorAsignado);
                rectangulo.setColorRelleno(colorRelleno);
                rectangulo.setAlisado(alisado);
                rectangulo.setGrosor(gr);
                rectangulo.setNivelTransparencia(nivelTransparencia);
                rectangulo.setTipoG(tipoGradiente);
                rectangulo.setDiscontinuidad(discontinuidad);
                
               
            }
            
            if(enUso == Herramientas.OVALO){
                ovalo = new AOvalo(p1);
                ovalo.formarFigura();
               
                ovalo.setColorAsignado(colorAsignado);
                ovalo.setColorRelleno(colorRelleno);
                ovalo.setAlisado(alisado);
                ovalo.setGrosor(gr);
                ovalo.setNivelTransparencia(nivelTransparencia);
                ovalo.setTipoG(tipoGradiente);
                ovalo.setDiscontinuidad(discontinuidad);
                
            }
            
            if(enUso == Herramientas.RECTA){
                linea = new ALinea2(p1);
                linea.formarFigura();
                linea.setColorAsignado(colorAsignado);
                linea.setAlisado(alisado);
                linea.setGrosor(gr);
                linea.setNivelTransparencia(nivelTransparencia);
                linea.setDiscontinuidad(discontinuidad);
                
                
            }
            
             if(enUso == Herramientas.LAPIZ){
                punto = new ALinea2(p1);
                punto.formarFigura();
                punto.setColorAsignado(colorAsignado);
                punto.setAlisado(alisado);
                punto.setGrosor(gr);
                punto.setNivelTransparencia(nivelTransparencia);
                
            }
             
            if(enUso == Herramientas.CURVA){
                curva = new ACurva(p1);
                curva.formarFigura();
                curva.setColorAsignado(colorAsignado);
                curva.setAlisado(alisado);
                curva.setGrosor(gr);
                curva.setNivelTransparencia(nivelTransparencia);
                curva.setDiscontinuidad(discontinuidad);
                
                
            }
            if(enUso == Herramientas.TEXTO){
                texto = new ATexto(p1);
                texto.setColorAsignado(colorAsignado);
                texto.setFuente(tipoFuente);
                texto.setGrosor(gr);
                texto.setTexto(text);
                texto.setNivelTransparencia(nivelTransparencia);
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

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
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
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
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
        // TODO add your handling code here:
         
        if(editar){
            if(rectangulo != null){
                Point2D p = new Point2D.Double((rectangulo.getRectangulo()).getBounds().getX() - evt.getPoint().getX(),
                    (rectangulo.getRectangulo()).getBounds().getY() - evt.getPoint().getY());
                dXY.setLocation(p);
                //rectangulo.setP1s(p);
            }
            else if(ovalo != null){
                Point2D p = new Point2D.Double((ovalo.getOvalo()).getBounds().getX() - evt.getPoint().getX(),
                        (ovalo.getOvalo()).getBounds().getY() - evt.getPoint().getY());
                dXY.setLocation(p);
               // ovalo.setP1s(p);
            }
            else if(linea != null){
                Point2D p = new Point2D.Double((linea.getLinea()).getX1() - evt.getPoint().getX(), 
                        (linea.getLinea()).getY1() - evt.getPoint().getY());
                dXY.setLocation(p);
            }
            else if(punto != null){
                Point2D p = new Point2D.Double((punto.getLinea()).getX1() - evt.getPoint().getX(), 
                        (punto.getLinea()).getY1() - evt.getPoint().getY());
                dXY.setLocation(p);
            }
            if(curva != null){
                Point2D p = new Point2D.Double((curva.getCurva()).getBounds().getX() - evt.getPoint().getX(),
                    (curva.getCurva()).getBounds().getY() - evt.getPoint().getY());
                dXY.setLocation(p);
            }
               
        
        }
        else{
            p1 = evt.getPoint();
            createShape();
            
        }
         repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        p2 = evt.getPoint();
        Point2D p = new Point2D.Double(evt.getPoint().getX() + dXY.getX(), evt.getPoint().getY() + dXY.getY());
               
        if(rectangulo != null){
            if(!editar){
               rectangulo.setP2(p2);
               rectangulo.updateShape();

            }
            else
                rectangulo.setLocation(p);
        }
        else if(ovalo != null){
            if(!editar){
                ovalo.setP2(p2);
                ovalo.updateShape();
            }
            else
                ovalo.setLocation(p);
        }
         
        else if(linea != null){
            if(!editar){
                linea.setP2(p2);
                linea.updateShape();
                
            }
            else
                linea.setLocation(p);
        }
        else if(punto != null){
            if(editar)
                punto.setLocation(p);
        }
        else if(curva != null){
            if(!editar){
                curva.setP2(p2);
                curva.updateShape();
                
            }
            else
                curva.setLocation(p);
        }
        repaint();
         
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if(!editar && (enUso == Herramientas.CURVA || enUso == Herramientas.LAPIZ || enUso == Herramientas.OVALO || enUso == Herramientas.RECTA ||
                enUso == Herramientas.RECTANGULO || enUso == Herramientas.TEXTO) )
            figuraCreada = true;
    }//GEN-LAST:event_formMouseReleased

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
      
       
    }//GEN-LAST:event_formFocusLost

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
     
        
    }//GEN-LAST:event_formFocusGained

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        if(enUso == Herramientas.TEXTO && !editar){
            p1 = evt.getPoint();
            text = JOptionPane.showInputDialog(
                 null,"Introduzca el texto",
                 "Texto",
                 JOptionPane.QUESTION_MESSAGE);
             
            System.out.print("la cadena es: " + text);
             createShape();
             repaint();
        }

        
        
    }//GEN-LAST:event_formMouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        
        if(!editar && figuraCreada){
            volcarImagen();
            figuraCreada = false;
        }
        

    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formMouseExited

    public void volcarImagen(){
         paint(image.createGraphics());
         repaint();
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
        if(this.image != null)
           this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight())); 
        repaint();
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
     public BufferedImage getImage(boolean var) {
        BufferedImage out = new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
        paint(out.createGraphics()); 
        return out;
    }
    
     
      //metodos gets
    public Herramientas getEnUso() {
        return enUso;
    }

    public Color getColorAsignado() {
        return colorAsignado;
    }
    
    public boolean isEditar() {
        return editar;
    }

    public boolean isAlisado() {
        return alisado;
    }

    public Color getColorRelleno(){
        return colorRelleno;
    }

    public int getGrosor() {
        return gr;
    }

    public boolean isDiscontinuidad() {
        return discontinuidad;
    }

    public float getNivelTransparencia() {
        return nivelTransparencia;
    }

    public String getTipoGradiente() {
        return tipoGradiente;
    }

    public String getTipoFuente() {
        return tipoFuente;
    }
    
    
    //metodos set
   
    public void setEnUso(Herramientas enUso) {
        this.enUso = enUso;
        editar = false;
    }

    public void setColorAsignado(Color colorAsignado) {
        this.colorAsignado = colorAsignado;
        if(editar){
            if(rectangulo != null)
                  rectangulo.setColorAsignado(colorAsignado);
            else if(ovalo != null)
                ovalo.setColorAsignado(colorAsignado);
            
            else if(linea != null)
                linea.setColorAsignado(colorAsignado);
            else if(punto != null)
                punto.setColorAsignado(colorAsignado);
            else if(curva != null)
                curva.setColorAsignado(colorAsignado);
            else if(texto != null)
                texto.setColorAsignado(colorAsignado);
            
            repaint();
        }
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
        if(editar)
            enUso = null;
       
    }

    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
        if(editar){
            if(rectangulo != null)
                  rectangulo.setAlisado(alisado);
            else if(ovalo != null)
                ovalo.setAlisado(alisado);
            
            else if(linea != null)
                linea.setAlisado(alisado);
            else if(punto != null)
                punto.setAlisado(alisado);
            else if(curva != null)
                curva.setAlisado(alisado);
            
            repaint();
        }
    }

    public void setImagenAbierta(boolean imagenAbierta) {
        this.imagenAbierta = imagenAbierta;
    }
    
    

    public void setGrosor(int gr) {
        grosor = new BasicStroke((float)gr);
        this.gr = gr;
        if(editar){
            if(rectangulo != null)
                  rectangulo.setGrosor(gr);
            else if(ovalo != null)
                ovalo.setGrosor(gr);
            
            else if(linea != null)
                linea.setGrosor(gr);
            
            else if(punto != null)
                punto.setGrosor(gr);
            
            else if(curva != null)
                curva.setGrosor(gr);
            else if(texto != null)
                texto.setGrosor(gr);
            
            repaint();
        }
    }
    
    public void setColorRelleno(Color color){
        colorRelleno = color;
        if(editar){
            if(rectangulo != null)
                  rectangulo.setColorRelleno(color);
            else if(ovalo != null)
                ovalo.setColorRelleno(color);   
            repaint();
        }
       
    }

    public void setDiscontinuidad(boolean discontinuidad) {
        this.discontinuidad = discontinuidad;
        if(editar){
            if(rectangulo != null)
                  rectangulo.setDiscontinuidad(discontinuidad);
            else if(ovalo != null)
                ovalo.setDiscontinuidad(discontinuidad);  
            else if(linea != null)
                linea.setDiscontinuidad(discontinuidad);
            else if(curva != null)
                curva.setDiscontinuidad(discontinuidad);
            repaint();
        }
    }

    public void setNivelTransparencia(float nivelTransparencia) {
        this.nivelTransparencia = nivelTransparencia;
        if(editar){
            if(rectangulo != null)
                  rectangulo.setNivelTransparencia(nivelTransparencia);
            else if(ovalo != null)
                ovalo.setNivelTransparencia(nivelTransparencia);
            
            else if(linea != null)
                linea.setNivelTransparencia(nivelTransparencia);
            else if(punto != null)
                punto.setNivelTransparencia(nivelTransparencia);
            else if(curva != null)
                curva.setNivelTransparencia(nivelTransparencia);
            else if(texto != null)
                texto.setNivelTransparencia(nivelTransparencia);
            repaint();
        }
    }

    public void setTipoGradiente(String tipoGradiente) {
        this.tipoGradiente = tipoGradiente;
         if(editar){
            if(rectangulo != null){
                  rectangulo.setTipoG(tipoGradiente);
            }
            else if(ovalo != null){
               ovalo.setTipoG(tipoGradiente);
            }
            repaint();
        }
    }
    
     public void setFuente(String fuente) {
        tipoFuente = fuente;
        if(editar && texto != null){
            texto.setFuente(fuente);
            repaint();
        }
    }
    
    public void metodoDraw(Graphics2D g2d){
        if(rectangulo != null)
           rectangulo.paintShape(g2d);
        else if(linea != null)
           linea.paintShape(g2d);
        else if(ovalo != null)
           ovalo.paintShape(g2d);
        else if(punto != null)
            punto.paintShape(g2d);
        else if(curva != null)
            curva.paintShape(g2d);
        else if(texto != null)
            texto.paintShape(g2d);
    }

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
