/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.controlador;

import co.edu.udistrital.batallanaval.modelo.Tablero;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author rfcas
 */
public class SocketPlayerController extends PlayerController implements Runnable {
    
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    
    public SocketPlayerController(Tablero tableroLocal, Socket socket){
        super(tableroLocal);
        this.socket = socket;
        try{
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream  = new ObjectInputStream(socket.getInputStream());
        }
        catch(IOException ioe){
            this.logger.log(Level.SEVERE, "Ocurrio un error inesperado de E/S", ioe);
            throw new RuntimeException("No se pudo inicializar el controlador", ioe);
        }
    }   
    
    @Override
    public void run() {        
        try{            
            this.logger.info("leyendo objeto");
            Point point = (Point)inputStream.readObject();
            this.logger.info("leyendo objeto "+point);
            this.setAtaque(point);
        }
        catch(IOException ioe){
            this.logger.log(Level.SEVERE, "Ocurrio un error inesperado de E/S", ioe);
        } catch (ClassNotFoundException cnfe) {
            this.logger.log(Level.SEVERE, "Se recibio un objeto desconocido", cnfe);
        }
        finally{

        }        
    }

    @Override
    protected void iniciarTurno() {        
        Thread thread = new Thread(this);        
        thread.start();
    }

    @Override
    protected void finalizarTurno() {
        
    }

    @Override
    public void setResult(Point point, boolean result) {
        super.setResult(point, result);        
        try{                                    
            this.logger.info("enviando booleano "+result);
            outputStream.writeObject( (Boolean)result );
        }
        catch(IOException ioe){
            this.logger.log(Level.SEVERE, "Ocurrio un error inesperado de E/S", ioe);            
        } 
        finally{
            
        }
    }

    @Override
    public boolean putAtaque(Point point) {
        boolean hit = false;        
        try{
            this.logger.info("enviando "+point+"...");
            outputStream.writeObject(point);            
            this.logger.info("leyendo booleano");
            hit = (Boolean)inputStream.readObject();
            this.logger.info("leyendo booleano "+hit);
        }
        catch(IOException ioe){
            this.logger.log(Level.SEVERE, "Ocurrio un error inesperado de E/S", ioe);            
        } catch (ClassNotFoundException cnfe) {
            this.logger.log(Level.SEVERE, "Se recibio un objeto desconocido", cnfe);
        }
        finally{
            
        }
        this.tablero.getAtaques().put(point, hit);
        return hit;
    }

    @Override
    protected void finalize() throws Throwable {
        try{this.outputStream.close();}catch(IOException e){;};
        try{this.inputStream.close();}catch(IOException e){;};
        try{this.socket.close();}catch(IOException e){;};
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
