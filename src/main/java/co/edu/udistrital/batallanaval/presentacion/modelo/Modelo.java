/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.modelo;

import co.edu.udistrital.batallanaval.logica.Tablero9NavesFactory;
import co.edu.udistrital.batallanaval.logica.TableroFactory;
import co.edu.udistrital.batallanaval.modelo.Partida;
import co.edu.udistrital.batallanaval.modelo.Tablero;
import co.edu.udistrital.batallanaval.presentacion.controlador.PlayerController;
import co.edu.udistrital.batallanaval.presentacion.controlador.SocketPlayerController;
import co.edu.udistrital.batallanaval.presentacion.controlador.TableroPlayerController;
import co.edu.udistrital.batallanaval.presentacion.vista.Sonidos;
import co.edu.udistrital.batallanaval.presentacion.vista.VerTableroPanel;
import co.edu.udistrital.batallanaval.presentacion.vista.Vista;
import java.awt.CardLayout;
import java.net.Socket;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author rfcas
 */
public class Modelo {
    
    private Logger logger = Logger.getLogger("Vista");
    
    private Vista vista;   
    
    private Tablero tablero;
    
    private TableroFactory tableroFactory;
    
    private Partida partida;
    
    public Modelo(){
        super();
        this.tableroFactory = new Tablero9NavesFactory();
        this.tablero = this.tableroFactory.createTablero();
        this.vista = new Vista(this);             
    }
    
    public void inicializar(){    
        this.logger.info("cambiando visibilidad de la vista");
        this.vista.setVisible(true);
        this.showCard("editar");
    }
    
    public void crearPartida(boolean ataque, Socket socket){
        tablero.getAtaques().clear();
        //
        this.vista.appendToLog("Iniciando partida");
        Tablero tableroLocal  = this.tablero;
        Tablero tableroRemoto = this.tableroFactory.createTablero();
        //El de la izquierda es quien inicia atacando
        this.partida = new Partida();
        this.partida.setPartidaLogger(this.vista);
        //Configura la vista del jugador local
        VerTableroPanel panelDefensa = (VerTableroPanel)this.vista.getjPanelDefensa();
        panelDefensa.setTablero(tableroLocal);
        VerTableroPanel panelAtaque = (VerTableroPanel)this.vista.getjPanelAtaque();
        panelAtaque.setTablero(tableroRemoto);
        //Configura los controler
        TableroPlayerController controlLocal  = new TableroPlayerController(tableroLocal, panelAtaque);        
        SocketPlayerController  controlRemoto = new SocketPlayerController(tableroRemoto, socket);
        //
        partida.iniciarPartida(
                new PlayerController[]{controlLocal, controlRemoto},
                ataque ? 0 : 1
            );        
        this.showCard("partida");
    }

    private void showCard(String cardName){
        JPanel gamePanel = this.vista.getjPanelGame();
        CardLayout layout = (CardLayout)gamePanel.getLayout();
        layout.show(gamePanel, cardName);
    }
    
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public Partida getPartida(){
        return this.partida;
    }
    
}
