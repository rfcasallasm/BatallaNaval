/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.modelo;

import co.edu.udistrital.batallanaval.presentacion.controlador.PlayerController;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author rfcas
 */
public class Partida implements Runnable{
   
    private PartidaLogger partidaLogger;
    
    private PlayerController[] jugadores;
    private int turno;
    
    public Partida(){
        super();        
        this.partidaLogger = null;
    }
    
    public void iniciarPartida(PlayerController[] jugadores, int turno){
        this.turno = turno;
        this.jugadores = jugadores;
        Thread thread = new Thread(this);
        thread.start();
    }                
    
    public boolean isFinalizada(){
        boolean derrotado = false;
        for( PlayerController controller : this.jugadores ){
            derrotado |= controller.getTablero().isDerrotado();
        }
        return derrotado;
    }    
    
    @Override
    public void run() {        
        Point ataque = null;
        boolean result = false;
        while(!isFinalizada()){
            try {
                this.appendToLog( (turno == 0 ? "Tu turno" : "Turno del oponente")+", esperando ataque..." );
                ataque = this.jugadores[turno].getAtaque();
                this.appendToLog( (turno == 0 ? "Atacaste la posición" : "El oponente ataco la posición")+" "+ataque.x+" "+ataque.y );
            } catch (InterruptedException ex) {
                this.appendToLog( "Error inesperado obteniendo "+(turno == 0 ? "tu ataque" : "el ataque del oponente")+": "+ex.getMessage() );
            }
            if(ataque != null){
                for( int i = 0; i < this.jugadores.length; i++ ){
                    if( i != turno ){
                        this.appendToLog( "Aplicando "+(turno == 0 ? "tu ataque" : "el ataque del oponente") );
                        result = this.jugadores[i].putAtaque(ataque);
                        this.appendToLog( (turno == 0 ? "Tu ataque" : "El ataque del oponente")+" resulto en: "+(result ? "¡ ¡ ¡ I M P A C T O ! ! !" : "nada") );
                        this.jugadores[turno].setResult(ataque, result);
                    }
                }
                //
                ataque = null;
            }
            //Cambio de turno
            turno = result ? turno : (turno < (this.jugadores.length-1) ? (turno+1) : 0);
        }
    }

    public int getTurno() {
        return turno;
    }        

    public PartidaLogger getPartidaLogger() {
        return partidaLogger;
    }

    public void setPartidaLogger(PartidaLogger partidaLogger) {
        this.partidaLogger = partidaLogger;
    }
    
    private void appendToLog(String message){
        if(this.partidaLogger == null){
            Logger.getLogger(this.getClass().getSimpleName()).info(message);
        }
        else{
            this.partidaLogger.appendToLog(message);
        }
    }
    
}
