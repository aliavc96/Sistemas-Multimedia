/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.Graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Esta clase es la encargada de implantar casi todos los atributos de las distintas
 * formas (rectángulo, óvalo, etc)
 * Además de definir los métodos abstractos que serán implementados en las clases 
 * que formarán las distintas figuras: formarFigura(), updateShape(), setLocation()
 * que servirán para crear y mover la figura
 * (formarFigura, setLocation...)
 * Esta clase es abstracta debido a que no se va a declarar ningún objeto de la misma
 * Además posee métodos abstractos que serán implementados de manera estricta en 
 * las clases que heredan de ésta para poder formar y mover la figura
 * 
 * Los atributos son:
 * grosor: se utiliza tanto para establecer el grosor del borde de la figura como 
 * para el "grosor" de la fuente del texto
 * 
 * colorAsignado: se utiliza para pintar la figura con el método draw, es decir,
 * este color no fijará el relleno de la figura y para el degradado horizontal
 * y vertical
 *
 * colorRelleno. este color se utilizará para el degradado horizontal,
 * vertical y para pintar la figura con el método fill de Graphics2D
 * 
 * alisado: en caso de ser true pintará la forma con un renderizado (ver atributo render)
 * que siempre será el mismo, por eso es constante
 * 
 * nivelTransparencia: por defecto no hay transparencia y se aplica con un comboBox
 * de ventanaPrincipal
 * 
 * @author Alicia Vílchez Ceballos  ETSIIT (UGR)
 * 
 */
public abstract class MyShape {
    
    //Atributos de las formas inicializados porque no existe el constructor:
    private int grosor = 1;
    private Color colorAsignado = Color.BLACK;
    private boolean editar = false;
    private boolean alisado = false;
    private float nivelTransparencia = 1;
    String tipoG; //para establecer el tipo de graciente: horizontal, vertical o relleno
   
    RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON); //para el alisado, mejorando la renderizacion de la figura
    
    private final float dash1[] = {10.0f};
    private BasicStroke dashed;
    private Color colorRelleno;
    private boolean discontinuidad = false;

    //atributos para el texto
    String tf; // para establecer el tipo de fuente del texto
    
    //la figura la implementan cada una de 
    public abstract void formarFigura();
        //implementacion en las subclases

    public abstract void setLocation(Point2D point);
    
    public abstract void updateShape();
    
   /**
    * 
    * @param grosor 
    */
    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    /**
     * 
     * @param colorRelleno 
     */
    public void setColorRelleno(Color color){
        colorRelleno = color;
    }

    /**
     * 
     * @param discontinuidad 
     */
    public void setDiscontinuidad(boolean discontinuidad) {
        this.discontinuidad = discontinuidad;
    }

    /**
     * 
     * @param colorAsignado 
     */
    public void setColorAsignado(Color colorAsignado) {
        this.colorAsignado = colorAsignado;
    }

    /**
     * 
     * @param editar 
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Además de establecer el atributo alisado, construye el render
     * @param alisado 
     */
    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
        render.put(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    }

    /**
     * 
     * @param nivelTransparencia 
     */
    public void setNivelTransparencia(float nivelTransparencia) {
        this.nivelTransparencia = nivelTransparencia;
    }

    /**
     * 
     * @param fuente
     */
    public void setFuente(String f) {
        tf = f;
    }

    /**
     * 
     * @param tipoGradiente
     */
    public void setTipoG(String tipoG) {
        this.tipoG = tipoG;
    }

    

    //métodos get

    /**
     * 
     * @return grosor asignado
     */
    public int getGrosor() {
        return grosor;
    }

    /**
     * 
     * @return color asignado
     */
    
    public Color getColorAsignado() {
        return colorAsignado;
    }

    /**
     * 
     * @return el color de relleno de la figura
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * 
     * @return si la figura es editable 
     */
    
    public boolean isEditar() {
        return editar;
    }

    /**
     * 
     * @return si el alisado está activo
     */
    public boolean isAlisado() {
        return alisado;
    }

    /**
     * 
     * @return el nivel de transparencia
     */
    public float getNivelTransparencia() {
        return nivelTransparencia;
    }

    /**
     * 
     * @return el tipo de gradiente establecido 
     */
    public String getTipoG() {
        return tipoG;
    }
    
    /**
     * Este método se encargará de aplicar los atributos al objeto graphics2D 
     * correspondiente (pasado como parámetro) y el resto de figuras dibujarán la forma con el mismo objeto
     * y con las coordenadas correspondientes.
     * Lo que no establece son los degradados del cuadrado y de la elipse, pues
     * para realizarlos necesita unas coordenadas que se corresponden 
     * con las mismas figuras
     * @param objetoGraphics2D 
     */
    public void paintShape(Graphics2D g){  
     
        g.setPaint(colorAsignado);
        g.setStroke(new BasicStroke(grosor));
        if(nivelTransparencia < 1)
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, nivelTransparencia));
        if(alisado) //si activamos alisado, se aplicará la renderizacion
            g.setRenderingHints(render);
        if(discontinuidad)
            g.setStroke(new BasicStroke(grosor,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f));
        
        Font fuente = new Font(tf, Font.BOLD | Font.ITALIC, grosor);
        g.setFont(fuente);
      
      
    }
   
}
