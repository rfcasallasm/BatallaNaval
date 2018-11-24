/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.controlador;

import co.edu.udistrital.batallanaval.presentacion.vista.EditarTableroPanel;
import co.edu.udistrital.batallanaval.presentacion.vista.Vista;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author rfcas
 */
public class ConnectionController implements ActionListener, Runnable {
    
    private Vista vista;
    private String comando;
    
    public ConnectionController(Vista vista){
        super();
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if( e.getSource() instanceof JToggleButton){
            JToggleButton toggleBtn = (JToggleButton)e.getSource();            
            if(toggleBtn.isSelected()){
                String message = null;
                if(!this.vista.getModelo().getTablero().isValido()){
                    message = "Debes corregir los errores en el tablero antes de establecer una conexión";
                }
                else if( this.vista.getjTextFieldIp().getValue() == null ){
                    message = "uuupps! ¿y la ip?";
                }
                else if( this.vista.getjTextFieldPort().getValue() == null ){
                    message = "uuupps! ¿y el puerto?";
                }
                if(message == null){                    
                    this.comando = e.getActionCommand();
                    ((EditarTableroPanel)this.vista.getjPanelTableroEditable()).setEditable(false);
                    Thread thread = new Thread(this);
                    thread.start();
                }
                else{
                    JOptionPane.showMessageDialog(this.vista, message);                    
                    toggleBtn.setSelected(false);
                }
                
            }
            else{
                ((EditarTableroPanel)this.vista.getjPanelTableroEditable()).setEditable(true);
                //TODO: Buscar que hacer                
            }
        }        
    }

    @Override
    public void run() {
        Socket socket = null;
        boolean modo  = false;
        if("Servidor".equals( this.comando )){            
            try{
                this.vista.appendToLog("Esperando conexiones...");                        
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(this.vista.getjTextFieldPort().getValue().toString()));
                socket = serverSocket.accept();
                modo = true;
            }
            catch(IOException ioe){
                this.vista.appendToLog("Upps... "+ioe.getMessage());
            }
        }
        else if("Cliente".equals( this.comando ) ){            
            try {                        
                this.vista.appendToLog("Conectandose al servidor...");
                socket = new Socket(InetAddress.getByAddress( (byte[])this.vista.getjTextFieldIp().getValue() ), Integer.parseInt(this.vista.getjTextFieldPort().getValue().toString()));
                modo = false;
            } catch (IOException ioe) {
                this.vista.appendToLog("Upps... "+ioe.getMessage());
            }
        }
        if(socket != null){
            this.vista.appendToLog("Conexión "+socket.toString());
            this.vista.getModelo().crearPartida(modo, socket);
        }
    }
    
}


