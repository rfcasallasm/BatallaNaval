/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.modelo;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rfcas
 */
public class Tablero{
            
    private int width;
    private int height;    
    private Nave[] naves;
    
    private Map<Point, Boolean> ataques;
            
    public Tablero(int width, int height, Nave[] naves){
        super();           
        this.width  = width;
        this.height = height;
        this.naves  = naves;        
        this.ataques = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Nave[] getNaves() {
        return naves;
    }

    public Map<Point, Boolean> getAtaques() {
        return ataques;
    }
    
    
    public boolean isDerrotado(){
        int impactos = 0;
        int derrota  = 0;
        for( Nave nave : this.naves ){
            derrota += nave.getLongitud();
        }
        for( Boolean impacto : this.ataques.values() ){
            impactos += (Boolean.TRUE.equals( impacto ) ? 1 : 0);
        }        
        return (impactos >= derrota);
    }

            
    
}
