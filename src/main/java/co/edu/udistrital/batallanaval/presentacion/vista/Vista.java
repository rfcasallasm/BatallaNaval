/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.batallanaval.presentacion.vista;

import co.edu.udistrital.batallanaval.modelo.PartidaLogger;
import co.edu.udistrital.batallanaval.presentacion.controlador.ConnectionController;
import co.edu.udistrital.batallanaval.presentacion.modelo.Modelo;
import co.edu.udistrital.batallanaval.utiles.IPAddressFormatter;
import java.awt.event.ItemEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultFormatterFactory;

/**
 *
 * @author rfcas
 */
public class Vista extends javax.swing.JFrame implements PartidaLogger{

    
    private Logger logger = Logger.getLogger("Vista");
    
    private Modelo modelo;
    /**
     * Creates new form Vista
     */
    public Vista(Modelo modelo) {
        this.modelo = modelo;
        initComponents();
        //        
        this.appendToLog("Debes configurar el tablero antes de conectarte a un adversario");
        this.appendToLog("*** Click izquierdo: Ubicar naves/Cambiar orientación");
        this.appendToLog("*** Click derecho  : Quitar elemento");
        this.appendToLog("Cuando estes preparado, inicia la conexión como cliente o servidor");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelConexion = new javax.swing.JPanel();
        jComboBoxModo = new javax.swing.JComboBox<>();
        jTextFieldIp = new javax.swing.JFormattedTextField();
        jTextFieldPort = new javax.swing.JFormattedTextField();
        jToggleButtonConexion = new javax.swing.JToggleButton();
        jPanelGame = new javax.swing.JPanel();
        jPanelPartida = new javax.swing.JPanel();
        jPanelDefensa = new VerTableroPanel();
        jPanelAtaque = new VerTableroPanel();
        jPanelEditar = new javax.swing.JPanel();
        jPanelTableroEditable = new EditarTableroPanel(this.modelo.getTablero());
        jPanelNaves = new VerNavesPanel(this.modelo.getTablero());
        jPanelLog = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        ((DefaultCaret)jTextAreaLog.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batalla Naval");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanelConexion.setBorder(javax.swing.BorderFactory.createTitledBorder("Conexion"));

        jComboBoxModo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBoxModo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servidor", "Cliente" }));
        jComboBoxModo.setSelectedIndex(1);
        jComboBoxModo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        jComboBoxModo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxModoItemStateChanged(evt);
            }
        });
        jPanelConexion.add(jComboBoxModo);

        jTextFieldIp.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ip", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        jTextFieldIp.setColumns(15);
        jTextFieldIp.setFormatterFactory(new DefaultFormatterFactory( new IPAddressFormatter() ));
        jTextFieldIp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanelConexion.add(jTextFieldIp);

        jTextFieldPort.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Puerto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        jTextFieldPort.setColumns(4);
        jTextFieldPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jTextFieldPort.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanelConexion.add(jTextFieldPort);

        jToggleButtonConexion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jToggleButtonConexion.setText("Conectar");
        jToggleButtonConexion.setToolTipText("Conectar");
        jToggleButtonConexion.setActionCommand("Cliente");
        jToggleButtonConexion.addActionListener(new ConnectionController(this));
        jPanelConexion.add(jToggleButtonConexion);

        getContentPane().add(jPanelConexion, java.awt.BorderLayout.NORTH);

        jPanelGame.setLayout(new java.awt.CardLayout());

        jPanelPartida.setBorder(javax.swing.BorderFactory.createTitledBorder("Juego"));
        jPanelPartida.setMaximumSize(new java.awt.Dimension(400, 400));
        jPanelPartida.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanelPartida.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanelPartida.setLayout(new java.awt.GridLayout(1, 2, 10, 10));

        jPanelDefensa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador 1", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanelPartida.add(jPanelDefensa);

        jPanelAtaque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador 2", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanelPartida.add(jPanelAtaque);

        jPanelGame.add(jPanelPartida, "partida");

        jPanelEditar.setMaximumSize(new java.awt.Dimension(2560, 2560));
        jPanelEditar.setMinimumSize(new java.awt.Dimension(380, 380));
        jPanelEditar.setPreferredSize(new java.awt.Dimension(380, 380));
        jPanelEditar.setLayout(new java.awt.GridLayout(1, 2, 10, 10));
        jPanelEditar.add(jPanelTableroEditable);
        jPanelEditar.add(jPanelNaves);

        jPanelGame.add(jPanelEditar, "editar");

        getContentPane().add(jPanelGame, java.awt.BorderLayout.CENTER);

        jPanelLog.setLayout(new javax.swing.OverlayLayout(jPanelLog));

        jTextAreaLog.setColumns(50);
        jTextAreaLog.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextAreaLog.setRows(5);
        jTextAreaLog.setText("Bienvenido!!!");
        jScrollPane1.setViewportView(jTextAreaLog);

        jPanelLog.add(jScrollPane1);

        getContentPane().add(jPanelLog, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxModoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxModoItemStateChanged
        
        if (evt.getStateChange() == ItemEvent.SELECTED) {            
          Object item = evt.getItem();
          if( "Cliente".equals( item ) ){
              jTextFieldIp.setValue( new byte[]{0,0,0,0} );
              jTextFieldIp.setEditable(true);
              //
              jTextFieldPort.setValue(32);
              //
              jToggleButtonConexion.setText("Conectar");
              jToggleButtonConexion.setActionCommand("Cliente");
          }
          else if( "Servidor".equals( item ) ){              
              try {                  
                  
                  jTextFieldIp.setValue(InetAddress.getLocalHost().getAddress());
              } catch (UnknownHostException ex) {
                  jTextFieldIp.setValue( new byte[]{127,0,0,0} );
              }
              jTextFieldIp.setEditable(false);
              //
              jTextFieldPort.setValue(32);
              //
              jToggleButtonConexion.setText("Escuchar");
              jToggleButtonConexion.setActionCommand("Servidor");
          }
          // do something with object
       }
    }//GEN-LAST:event_jComboBoxModoItemStateChanged
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxModo;
    private javax.swing.JPanel jPanelAtaque;
    private javax.swing.JPanel jPanelConexion;
    private javax.swing.JPanel jPanelDefensa;
    private javax.swing.JPanel jPanelEditar;
    private javax.swing.JPanel jPanelGame;
    private javax.swing.JPanel jPanelLog;
    private javax.swing.JPanel jPanelNaves;
    private javax.swing.JPanel jPanelPartida;
    private javax.swing.JPanel jPanelTableroEditable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JFormattedTextField jTextFieldIp;
    private javax.swing.JFormattedTextField jTextFieldPort;
    private javax.swing.JToggleButton jToggleButtonConexion;
    // End of variables declaration//GEN-END:variables

    public JFormattedTextField getjTextFieldIp() {
        return jTextFieldIp;
    }

    public JFormattedTextField getjTextFieldPort() {
        return jTextFieldPort;
    }    

    public void appendToLog(String message){        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.jTextAreaLog.append("\n");
        this.jTextAreaLog.append(dateFormat.format( new Date() ));        
        this.jTextAreaLog.append(" : ");
        this.jTextAreaLog.append(message);
        this.jTextAreaLog.repaint();
        this.logger.log(Level.INFO, message);
        /*
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            this.logger.log(Level.WARNING, "Error escribiendo en el log de pantalla", ie);                   
        }*/
    }
    
    public Modelo getModelo(){
        return this.modelo;
    }

    public JPanel getjPanelGame() {
        return jPanelGame;
    }

    public JPanel getjPanelAtaque() {
        return jPanelAtaque;
    }

    public JPanel getjPanelDefensa() {
        return jPanelDefensa;
    }        

    public JPanel getjPanelEditar() {
        return jPanelEditar;
    }     
    
    public JPanel getjPanelTableroEditable(){
        return jPanelTableroEditable;
    }
    
    public JToggleButton getjToggleButtonConexion(){
        return jToggleButtonConexion;
    }
    
}
