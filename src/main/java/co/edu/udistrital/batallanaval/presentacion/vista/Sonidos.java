/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.vista;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author rfcas
 */
public class Sonidos {
    
    private static volatile Sonidos _instance = null;
    
    public static Sonidos getInstance(){
        if(_instance == null){
            synchronized(Sonidos.class){
                if(_instance == null){
                    _instance = new Sonidos();
                }
            }
        }
        return Sonidos._instance;
    }
    
    private Map<String, String> sounds;
    
    private Sonidos(){
        super();
        this.sounds = new HashMap<>();        
    }
    
    
    
    public void playSound( String name ){   
        try{
            InputStream is = this.getClass().getResourceAsStream( this.sounds.get( name ) );
            is = AudioSystem.getAudioInputStream( is );
            Clip clip = AudioSystem.getClip();
            clip.open((AudioInputStream)is);
            clip.start();
        }
        catch(Exception e ){
            Logger.getLogger("Sonidos").log(Level.SEVERE, "Error con el sonido "+name+" "+e.getMessage());
        }
    }
    
}
