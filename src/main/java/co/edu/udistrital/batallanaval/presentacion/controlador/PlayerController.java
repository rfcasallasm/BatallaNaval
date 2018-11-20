/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.controlador;

import co.edu.udistrital.batallanaval.modelo.Nave;
import co.edu.udistrital.batallanaval.modelo.Tablero;
import java.awt.Point;
import java.util.logging.Logger;

/**
 *
 * @author rfcas
 */
public abstract class PlayerController {

    protected Logger logger;
    protected Tablero tablero;
    
    private Point ataque;
    private boolean result;
    
    
    public PlayerController(Tablero tablero){
        super();
        this.logger  = Logger.getLogger( this.getClass().getSimpleName() );
        this.tablero = tablero;
    }

    public final Point getAtaque() throws InterruptedException {
        Point retValue;
        synchronized(this){            
            iniciarTurno();
            this.logger.info("getAtaque(): Esperando ataque");
            this.wait();
            this.logger.info("getAtaque(): Ataque recibido");
            retValue = this.ataque;
            this.ataque = null;
            finalizarTurno();
        }                        
        return retValue;
    }
        
    protected final void setAtaque(Point ataque) {        
        synchronized(this){
            this.ataque = ataque;
            this.notifyAll();
        }        
    }

    protected abstract void iniciarTurno();
    protected abstract void finalizarTurno();    
    
    public boolean putAtaque(Point point){
        boolean hit = false;
        for( Nave nave : this.tablero.getNaves() ){
            hit |= nave.isPointInside(point);
        }
        this.tablero.getAtaques().put(point, hit);
        return hit;
    }

    public Tablero getTablero() {
        return tablero;
    }   

    public void setResult(Point point, boolean result) {
        this.result = result;
    }        
    
}
