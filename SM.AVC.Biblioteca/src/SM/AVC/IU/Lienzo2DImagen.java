/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.IU;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ali_a
 */
public class Lienzo2DImagen extends Lienzo{

    /**
     * Creates new form Lienzo2DImagen
     */
    private BufferedImage image;
    
    public Lienzo2DImagen() {
        initComponents();
        //image = new BufferedImage(300,300,0);
        //Para que en el lienzo se cree la imagen por defecto
        image = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
        for(int i=0; i< image.getWidth(); i++){
            for(int j=0; j < image.getHeight(); j++)
                image.setRGB(i, j, Color.WHITE.getRGB());
        }
        setImage(image);
        repaint();
    }

    //metodo get y set 
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
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null)
            g.drawImage(image, 0, 0, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));
        setColorAsignado(new java.awt.Color(204, 204, 204));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
