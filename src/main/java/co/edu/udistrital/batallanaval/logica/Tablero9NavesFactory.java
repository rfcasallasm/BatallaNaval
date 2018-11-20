/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.logica;

import co.edu.udistrital.batallanaval.modelo.Nave;
import co.edu.udistrital.batallanaval.modelo.Tablero;

/**
 *
 * @author rfcas
 */
public class Tablero9NavesFactory implements TableroFactory{

    @Override
    public Tablero createTablero() {
        return new Tablero(10, 10, //10x10
            new Nave[]{new Nave("Portaaviones", 4), //1 portaaviones
                new Nave("Crucero", 3), new Nave("Crucero", 3), //2 cruceros
                new Nave("Submarino", 2), new Nave("Submarino", 2), new Nave("Submarino", 2), //3 submarinos
                new Nave("Destructor", 1), new Nave("Destructor", 1), new Nave("Destructor", 1), new Nave("Destructor", 1) // 4 destructores
            }
        );
    }
    
}
