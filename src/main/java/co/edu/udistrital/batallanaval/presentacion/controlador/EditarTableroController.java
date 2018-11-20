/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.controlador;

import co.edu.udistrital.batallanaval.modelo.Nave;
import co.edu.udistrital.batallanaval.presentacion.vista.EditarTableroPanel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

/**
 *
 * @author rfcas
 */
public class EditarTableroController implements MouseListener{

    private Logger logger = Logger.getLogger("Controller");
    private EditarTableroPanel vista;
    private Nave naves[];
    private Point punto;
    
    public EditarTableroController(EditarTableroPanel vista){
        super();
        this.vista = vista;
        this.naves = this.vista.getTablero().getNaves();        
    }
    
    private Point convertirPunto(Point punto){  
        return new Point(
            punto.x*this.vista.getTablero().getWidth()/this.vista.getWidth(),
            punto.y*this.vista.getTablero().getHeight()/this.vista.getHeight()
        );
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if( !this.vista.isEditable() ){
            return;
        }
        Point donde = this.convertirPunto(e.getPoint());
        this.logger.info("mouseClicked("+donde+")");
        Nave naveEnPunto = null;
        for(Nave nave : this.naves ){
            if(nave.isPointInside(donde)){
                naveEnPunto = nave;
                break;
            }
        }
        if(naveEnPunto == null){
            this.logger.info("mouseClicked("+donde+"): no se encontro ninguna nave en el punto");
            if(e.getButton() == MouseEvent.BUTTON1){                
                for(Nave nave : this.naves ){
                    if(nave.getPosicion() == null){
                        naveEnPunto = nave;
                        break;
                    }
                }
                if(naveEnPunto != null){
                    this.logger.info("mouseClicked("+donde+"): se encontro la nave "+naveEnPunto+" sin ubicar");
                    naveEnPunto.setPosicion(donde);
                    naveEnPunto.setHorizontal(true);
                    this.logger.info("mouseClicked("+donde+"): "+naveEnPunto+" ubicada");
                }
                else{
                    //Nada porque ya ubico todo, asi sea mal ubicado
                    this.logger.info("mouseClicked("+donde+"): no se encontraron naves sin ubicar");
                }
            }
            else{
                this.logger.info("mouseClicked("+donde+"): hizo clic derecho en una posicion vacia");
                //No hace nada porque le dio click derecho a una posicion vacia
            }
        }
        else{
            if(e.getButton() == MouseEvent.BUTTON1){
                this.logger.info("mouseClicked("+donde+"): cambiando orientacion de la nave "+naveEnPunto);
                naveEnPunto.setHorizontal(!naveEnPunto.isHorizontal() );
                this.logger.info("mouseClicked("+donde+"): "+naveEnPunto+" reorientada");
            }
            else if(e.getButton() == MouseEvent.BUTTON3){
                this.logger.info("mouseClicked("+donde+"): quitando la ubicacion de la nave "+naveEnPunto);
                naveEnPunto.setPosicion(null);                
            }
        }
        this.vista.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Point donde = this.convertirPunto(e.getPoint());
        //this.logger.info("mousePressed("+donde+")");
        //this.punto = donde;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Point donde = this.convertirPunto(e.getPoint());
//        this.logger.info("mouseReleased("+donde+")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Point donde = this.convertirPunto(e.getPoint());
        //this.logger.info("mouseEntered("+donde+")");
        this.punto = null;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Point donde = this.convertirPunto(e.getPoint());
        //this.logger.info("mouseExited("+donde+")");
        this.punto = null;
    }
    
}
