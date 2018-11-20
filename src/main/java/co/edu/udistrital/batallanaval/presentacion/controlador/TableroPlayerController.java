/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.controlador;

import co.edu.udistrital.batallanaval.modelo.Tablero;
import co.edu.udistrital.batallanaval.presentacion.vista.VerTableroPanel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rfcas
 */
public class TableroPlayerController extends PlayerController implements MouseListener{    
    
    private VerTableroPanel vista;
    
    public TableroPlayerController(Tablero tableroLocal, VerTableroPanel tableroRemoto){
        super(tableroLocal);
        this.vista = tableroRemoto;        
    }

    private Point convertirPunto(Point punto){  
        return new Point(
            punto.x*this.vista.getTablero().getWidth()/this.vista.getWidth(),
            punto.y*this.vista.getTablero().getHeight()/this.vista.getHeight()
        );
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Point donde = this.convertirPunto(e.getPoint());
        this.logger.info("mouseClicked("+donde+")");
        this.setAtaque(donde);        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Nada
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Nada
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Nada
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Nada
    }    

    @Override
    protected void iniciarTurno() {        
        this.vista.addMouseListener(this);
        this.vista.getParent().repaint();
    }

    @Override
    protected void finalizarTurno() {
        this.vista.removeMouseListener(this);        
    }

    @Override
    public boolean putAtaque(Point point) {
        boolean retValue = super.putAtaque(point);        
        this.vista.getParent().repaint();
        return retValue;
    }
    
    
    
    @Override
    public void setResult(Point point, boolean result) {
        super.setResult(point, result);
        //En este caso lo pone en el tablero "visitante" para tener la referencia
        this.vista.getTablero().getAtaques().put(point, result);
        this.vista.getParent().repaint();
    }
    
}
