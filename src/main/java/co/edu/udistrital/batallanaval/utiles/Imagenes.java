/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.utiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author rfcas
 */
public class Imagenes {
    
    private static volatile Imagenes _instance = null;
    
    public static Imagenes getInstance(){
        if(_instance == null){
            synchronized(Imagenes.class){
                if(_instance == null){
                    _instance = new Imagenes();
                }
            }
        }
        return Imagenes._instance;
    }
    
    private Map<String, BufferedImage > images;
    
    private Imagenes(){
        super();
        this.images = new HashMap<>();        
        this.images.put("crucero_h", this.loadImage("/imgs/crucero_h.png"));
        this.images.put("crucero_v", this.loadImage("/imgs/crucero_v.png"));
        this.images.put("destructor_h", this.loadImage("/imgs/destructor_h.png"));
        this.images.put("destructor_v", this.loadImage("/imgs/destructor_v.png"));
        this.images.put("portaaviones_h", this.loadImage("/imgs/portaavion_h.png"));
        this.images.put("portaaviones_v", this.loadImage("/imgs/portaavion_v.png"));
        this.images.put("submarino_v", this.loadImage("/imgs/submarino_v.png"));
        this.images.put("submarino_h", this.loadImage("/imgs/submarino_h.png"));
    }
    
    private BufferedImage loadImage(String path){        
        try {
            return ImageIO.read( this.getClass().getResourceAsStream( path ));
        } catch (IOException e) {
            Logger.getLogger("Imagenes").log(Level.SEVERE, "Error con la imagen "+path+" "+e.getMessage());
        }
        return null;
    }
    
    public BufferedImage getImage( String name ){   
        return this.images.get(name.toLowerCase());
    }
    
}
