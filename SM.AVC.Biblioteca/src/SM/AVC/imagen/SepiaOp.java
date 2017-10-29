/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.imagen;
import java.awt.Color;
import java.awt.image.BufferedImage;
import sm.image.*;

/**
 * Esta clase se encargará de aplicar el filtro sepia a la imagen
 * Para ello tenemos que aplicar las transformaciones pertinentes (se pueden
 * observar en el método filter implementado a partir de la clase sm.image.BufferedImageOpAdapter
 * pues hereda de la misma)
 * @author Alcia Vílchez Ceballos
 */
public class SepiaOp extends sm.image.BufferedImageOpAdapter {

    /**
     * 
     * @param BufferedImage
     * @param BufferdImage
     * @return bufferedImage
     */
    @Override
    public BufferedImage filter(BufferedImage bi, BufferedImage bi1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(bi == null) {
            throw new NullPointerException("src image is null");
            //return null;
        }

        if (bi1 == null) {
            bi1 = createCompatibleDestImage(bi, null);
        }
        Color colorPx = null;
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                colorPx = new Color(bi.getRGB(x, y));
                int r = (int)Math.min(255,  0.393*colorPx.getRed() + 0.769*colorPx.getGreen() + 0.189*colorPx.getBlue());
                int g = (int)Math.min(255, 0.349*colorPx.getRed() + 0.686*colorPx.getGreen() + 0.168*colorPx.getBlue());
                int b = (int)Math.min(255, 0.272*colorPx.getRed() + 0.534*colorPx.getGreen() + 0.131*colorPx.getBlue());
                colorPx = new Color(r,g,b);
                bi1.setRGB(x,y,colorPx.getRGB());
            }   //Por hacer: efecto sepia
        }
        return bi1;
    }
    
}
