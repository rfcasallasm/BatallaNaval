/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval;

import co.edu.udistrital.batallanaval.presentacion.modelo.Modelo;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rfcas
 */
public class Launcher {
    
    public Launcher(){
        
    }
    
    public static String getLocaIpAddresses(){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            Enumeration<NetworkInterface> enumNI = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            while(enumNI.hasMoreElements()){
                networkInterface = enumNI.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while(inetAddresses.hasMoreElements()){
                    inetAddress = inetAddresses.nextElement();
                    if(inetAddress instanceof Inet4Address && "127.0.0.1".equals( inetAddress.getHostAddress() )){
                        stringBuilder.append(inetAddress.getHostAddress());
                        stringBuilder.append("/");
                    }
                }
            }
        }
        catch( SocketException se ){
            try {
                stringBuilder.append( InetAddress.getLocalHost().getHostAddress() );
            } catch (UnknownHostException ex) {
                stringBuilder.append("127.0.0.1");
            }
        }
        return stringBuilder.toString();
    }
    
    public static void main(String args[]){
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %3$s => %5$s %n");
        String localhost = Launcher.getLocaIpAddresses();
        Modelo modelo = new Modelo(localhost);
        modelo.inicializar();
    }
    
}
