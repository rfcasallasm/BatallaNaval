/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.vista;

import co.edu.udistrital.batallanaval.modelo.Nave;
import co.edu.udistrital.batallanaval.modelo.Tablero;
import co.edu.udistrital.batallanaval.utiles.Imagenes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author rfcas
 */
public class VerTableroPanel extends JPanel{
    
    private Logger logger = Logger.getLogger("Vista");
    
    private Tablero tablero;    
    
    public VerTableroPanel(){
        super();        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        if( this.tablero == null ){
            return;
        }
        boolean modoAtaque = (this.getMouseListeners().length > 0);
        g.setColor( this.tablero.isDerrotado() ? Color.decode("#ff8080") : (modoAtaque ? Color.white : Color.decode("#49599a")) );
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Point factor = new Point(
            (this.getWidth()/this.tablero.getWidth()),
            (this.getHeight()/this.tablero.getHeight())    
        );
        g.setColor(this.tablero.isDerrotado() ? Color.WHITE : ( modoAtaque ? Color.BLUE : Color.white ) );
        
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
        g.setColor( this.tablero.isDerrotado() ? Color.red : Color.decode("#feb6aa") );
        ((Graphics2D)g).setStroke( new BasicStroke(0.5f*border));
        
        for( Nave nave : this.tablero.getNaves() ){
            if(nave.getPosicion() != null){
                x = border + factor.x*nave.getPosicion().x;
                y = border + factor.y*nave.getPosicion().y;
                width = -2*border + factor.x*(!nave.isHorizontal()?1:nave.getLongitud());
                height = -2*border + factor.y*(nave.isHorizontal()?1:nave.getLongitud());
                g.drawImage(Imagenes.getInstance().getImage(nave.getNombre()+"_"+(nave.isHorizontal() ? "h" : "v")), x, y, width, height, null);
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
                        this.tablero.isDerrotado() ? new Color(255f/255, 0f/255, 0f/255, 0.2f) : new Color(100f/255, 16f/255, 0f/255, 0.2f),
                        this.tablero.isDerrotado() ? new Color(95f/255, 0f/255, 0f/255, 0.4f) : new Color(196f/255, 95f/255, 95f/255, 0.4f),
                        this.tablero.isDerrotado() ? new Color(255f/255, 0f/255, 0f/255, 0.6f) : new Color(100f/255, 16f/255, 0f/255, 0.6f),
                        this.tablero.isDerrotado() ? new Color(95f/255, 0f/255, 0f/255, 0.8f) : new Color(196f/255, 95f/255, 196f/255, 0.8f)
                    },
                    MultipleGradientPaint.CycleMethod.REPEAT
                );
                ((Graphics2D)g).setPaint(lgp);                                
                g.fillRoundRect(x, y, width, height, factor.x, factor.y);
                ((Graphics2D)g).setPaint(paint);  
            }
        }*/
        int x1, y1, x2, y2;
        for( Entry<Point, Boolean> ataque : this.tablero.getAtaques().entrySet() ){            
            g.setColor(Boolean.TRUE.equals( ataque.getValue() ) ? Color.RED : (!this.tablero.isDerrotado() && modoAtaque ? Color.BLUE : Color.WHITE) );
            x1 = -border + ataque.getKey().x*factor.x;
            y1 = -border + ataque.getKey().y*factor.y;
            x2 = x1 + factor.x + 2*border;
            y2 = y1 + factor.y + 2*border;
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x1, y2, x2, y1);
        }
        
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    
    
}
