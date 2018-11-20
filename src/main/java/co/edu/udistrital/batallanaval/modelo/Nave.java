/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.modelo;

import java.awt.Point;

/**
 *
 * @author rfcas
 */
public class Nave {
    
    private String nombre;
    private int longitud;
        
    private Point posicion;
    private boolean horizontal;
    
    public Nave(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;        
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getLongitud(){
        return this.longitud;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }    

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
    
    public boolean isPointInside(Point point){
        return (
            this.posicion != null
            && ( this.posicion.x <= point.x && point.x < ( this.posicion.x + (!this.horizontal?1:this.longitud) ) )
            && ( this.posicion.y <= point.y && point.y < ( this.posicion.y + (this.horizontal?1:this.longitud) ) )
        );
    }    

    public void paint(){
        
    }
    
    @Override
    public String toString(){
        return this.nombre + ( this.posicion != null ? "["+this.posicion.x+", "+this.posicion.y+"] - ["+(this.posicion.x+(!this.horizontal?1:this.longitud)-1)+", "+(this.posicion.y+(this.horizontal?1:this.longitud)-1)+"]" : "" );
    }
}
