/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.vista;

import co.edu.udistrital.batallanaval.modelo.Nave;
import co.edu.udistrital.batallanaval.modelo.Tablero;
import co.edu.udistrital.batallanaval.presentacion.controlador.EditarTableroController;
import co.edu.udistrital.batallanaval.utiles.Imagenes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.Point;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author rfcas
 */
public class EditarTableroPanel extends JPanel{
    
    private Logger logger = Logger.getLogger("Vista");
    
    private Tablero tablero;
    private boolean editable;
    private EditarTableroController controller;
    
    public EditarTableroPanel(Tablero tablero){
        super();
        this.editable = true;
        this.tablero = tablero;
        this.controller = new EditarTableroController(this);
        this.initComponents();
    }

    private void initComponents(){
        this.addMouseListener(this.controller);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Point factor = new Point(
            (this.getWidth()/this.tablero.getWidth()),
            (this.getHeight()/this.tablero.getHeight())    
        );
        
        
        g.setColor( this.editable ? Color.decode("#9a5949") : Color.decode("#49599a") );
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.WHITE);
        for( int i = 1; i < this.tablero.getWidth(); i++ ){            
            g.drawLine( i*factor.x, 0, i*factor.x, this.getHeight());
        }
        for( int j = 1; j < this.tablero.getHeight(); j++ ){
            g.drawLine( 0, j*factor.y, this.getWidth(), j*factor.y );
        }
        
        //Graphics2D g2d = (Graphics2D) g;
        Paint paint;
        LinearGradientPaint lgp;
        //
        int x, y, width, height;
        //
        int border = (int)(Math.min( 10d, 0.3d*Math.max(factor.x, factor.y) )/2);
        /*
        g.setColor( !this.editable ? Color.decode("#feb6aa") : Color.decode("#aab6fe") );
        ((Graphics2D)g).setStroke( new BasicStroke(0.5f*border));
        */
        
        for( Nave nave : this.tablero.getNaves() ){
            if(nave.getPosicion() != null){
                x = border + factor.x*nave.getPosicion().x;
                y = border + factor.y*nave.getPosicion().y;
                width  = -2*border + factor.x*(!nave.isHorizontal()?1:nave.getLongitud());
                height = -2*border + factor.y*( nave.isHorizontal()?1:nave.getLongitud());                
                g.drawImage(Imagenes.getInstance().getImage(nave.getNombre()+"_"+(nave.isHorizontal() ? "h" : "v")), x, y, width, height, this);                
            }            
        }
        
        /*
        for( Nave nave : this.tablero.getNaves() ){
            if(nave.getPosicion() != null){  
                x = border + factor.x*nave.getPosicion().x;
                y = border + factor.y*nave.getPosicion().y;
                width = -2*border + factor.x*(!nave.isHorizontal()?1:nave.getLongitud());
                height = -2*border + factor.y*(nave.isHorizontal()?1:nave.getLongitud());
                g.drawRoundRect(x, y, width, height, factor.x, factor.y);
                paint = ((Graphics2D)g).getPaint();
                lgp = new LinearGradientPaint(
                    //x, y, x+width, y+height,
                        0,0, 10, 10,
                    new float[]{0f, 0.3f, 0.6f, 1f},
                    new Color[]{
                        this.editable ? new Color(0f/255, 16f/255, 100f/255, 0.5f) : new Color(100f/255, 16f/255, 0f/255, 0.5f),
                        this.editable ? new Color(95f/255, 95f/255, 196f/255, 0.6f) : new Color(196f/255, 95f/255, 95f/255, 0.6f),
                        this.editable ? new Color(0f/255, 16f/255, 100f/255, 0.7f) : new Color(100f/255, 16f/255, 0f/255, 0.7f),
                        this.editable ? new Color(95f/255, 95f/255, 196f/255, 0.8f) : new Color(196f/255, 95f/255, 196f/255, 0.8f)
                    },
                    CycleMethod.REPEAT
                );
                ((Graphics2D)g).setPaint(lgp);                                
                g.fillRoundRect(x, y, width, height, factor.x, factor.y);
                ((Graphics2D)g).setPaint(paint);  
            }
        }*/
        
    }

    public Tablero getTablero() {
        return tablero;
    }         

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        this.repaint();
    }
    
    
    
}
