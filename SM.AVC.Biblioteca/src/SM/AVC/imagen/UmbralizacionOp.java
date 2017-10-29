/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.AVC.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Esta clase se encargará de aplicar el filtro umbralización a la imagen
 * Para ello tenemos que aplicar las transformaciones pertinentes (se pueden
 * observar en el método filter implementado a partir de la clase sm.image.BufferedImageOpAdapter
 * pues hereda de la misma)
 * Lo que hace es transformar todos píxeles de la imagen a partir de un mínimo y un máximo
 * dependiendo de los valores obtenidos
 * @author Alcia Vílchez Ceballos
 */
public class UmbralizacionOp extends sm.image.BufferedImageOpAdapter{

    private int umbral;
    public UmbralizacionOp(int umb){
        umbral = umb;
        
    }
    @Override
    public BufferedImage filter(BufferedImage bi, BufferedImage bi1) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(bi == null) {
            throw new NullPointerException("src image is null");
        }

        if (bi1 == null) {
            bi1 = createCompatibleDestImage(bi, null);
        }
        Color colorPx = null;
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                colorPx = new Color(bi.getRGB(x, y));
                //el umbral lo dejamos en 145
                if((colorPx.getRed() + colorPx.getGreen() + colorPx.getBlue())/3 > umbral)
                    bi1.setRGB(x,y,Color.WHITE.getRGB());
                else
                    bi1.setRGB(x,y,Color.black.getRGB());
            }   //Por hacer: efecto sepia
        }
        return bi1;
    }
    
}
