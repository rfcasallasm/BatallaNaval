/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval;

import co.edu.udistrital.batallanaval.presentacion.modelo.Modelo;
import java.net.UnknownHostException;

/**
 *
 * @author rfcas
 */
public class Launcher {
    
    public Launcher(){
        
    }
    
    public static void main(String args[]) throws UnknownHostException{
        //System.out.println(InetAddress.getLocalHost().getHostAddress());        
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %3$s => %5$s %n");
        Modelo modelo = new Modelo();
        modelo.inicializar();
    }
    
}
