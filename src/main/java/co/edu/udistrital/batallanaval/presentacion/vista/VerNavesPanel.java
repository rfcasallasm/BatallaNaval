/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.udistrital.batallanaval.presentacion.vista;

import co.edu.udistrital.batallanaval.modelo.Nave;
import co.edu.udistrital.batallanaval.modelo.Tablero;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rfcas
 */
public class VerNavesPanel extends javax.swing.JPanel {

    private Logger logger = Logger.getLogger("Vista");
    
    private Tablero tablero;
    
    /** Creates new form VerNavesPanel */
    public VerNavesPanel(Tablero tablero) {
        super();
        this.tablero = tablero;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new javax.swing.OverlayLayout(this));

        jTable1.setModel(new NavesTableModel(this.tablero.getNaves()));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

class NavesTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = new String[]{"Nave", "Estado"};    

    private Nave[] naves;

    public NavesTableModel(Nave[] naves) {
        super();
        this.naves = naves;
    }    

    @Override
    public int getRowCount() {
        return this.naves.length;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int col) {
        return NavesTableModel.COLUMNAS[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {        
        Nave nave = this.naves[rowIndex];
        switch (columnIndex) {
            case 0:
                return nave.getNombre();
            case 1:
                return (nave.getPosicion() != null ? "OK" : "No ubicado");      
            default:
                return null;
        }
    }    

}
