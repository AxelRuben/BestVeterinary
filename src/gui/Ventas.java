/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ClienteDao;
import dao.EmpleadoDao;
import dao.MiscelaneasDao;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pojo.Venta;
import pojo.Venta_has_Miscelaneas;

/**
 *
 * @author blanc
 */
public class Ventas extends javax.swing.JFrame {

    MiscelaneasDao miscelaneasDao;
    TableRowSorter<TableModel> sorter;
    String opcion;
    DefaultTableModel dTM = new DefaultTableModel();
    DefaultTableModel dTM2 = new DefaultTableModel();
    boolean com;
    int cantidad;
    EmpleadoDao empleadoDao = new EmpleadoDao();
    ClienteDao clienteDao = new ClienteDao();
    DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();

    /**
     * Creates new form Ventas
     */
    public Ventas() {
        initComponents();
        this.setTitle("Ventas");
        this.setLocationRelativeTo(null);
        miscelaneasDao = new MiscelaneasDao();
        loadModelIn();
        loadComEmp();
        loadComCli();
        setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
    }
    Inicio inicio = new Inicio();

    public void loadModelIn() {
        DefaultTableModel dt = miscelaneasDao.cargarModeloIn();
        sorter = new TableRowSorter<>(dt);
        jTable1.setModel(dt);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setRowSorter(sorter);
    }
    
//    public int insertarVenta() {
//        Venta venta = new Venta();
//        int id = new VentaDAO().insertar(venta);
//        System.out.println(id);
//        if (id != 0) {
//            for (int i = 0; i < jTable2.getRowCount(); i++) {
//                Venta_has_Miscelaneas venta_has_Miscelaneas = new Venta_has_Miscelaneas();
//                venta_has_productoPOJO.setVenta_idventa(id);
//                venta_has_productoPOJO.setProducto_idproducto(Integer.parseInt(jTable2.getValueAt(i, 0).toString()));
//                venta_has_productoPOJO.setCantidad(Integer.parseInt(jTable2.getValueAt(i, 2).toString()));
//                venta_has_productoPOJO.setSubtotal(Double.parseDouble(jTable2.getValueAt(i, 3).toString()));
//                int id2 = new Venta_Has_ProductoDAO().insertar(venta_has_productoPOJO);
//                if (id != 0 && reducirStock(venta_has_productoPOJO)) {
//                    System.out.println("inserción correcta NM");
//                } else {
//                    System.out.println("error en la inserción NM");
//                    break;
//                }
//            }
//        } else {
//            System.out.println("error en la inserción de venta");
//        }
//        cargarModelo();
//        return id;
//    }

    void agregarVenta() {
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();
        int row = jTable1.getSelectedRow();

        String id = jTable1.getValueAt(row, 0).toString();
        String nombre = jTable1.getValueAt(row, 1).toString();
        double precio = Double.parseDouble(jTable1.getValueAt(row, 2).toString());
        cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos desea llevar?"));
        int stock = Integer.parseInt(jTable1.getValueAt(row, 3).toString());
        double subtotal = precio * cantidad;

        if (verificarStock(cantidad, stock)) {
            Object producto[] = {id, nombre, subtotal};
            table2.addRow(producto);
            total.setText(calcularTotal() + "");
        } else {
            JOptionPane.showMessageDialog(null, "No hay existencias suficientes");
        }
    }

    boolean verificarStock(int cantidad, int stock) {
        int restante = stock - cantidad;
        if (restante >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void loadComEmp() {
        defaultComboBoxModel = empleadoDao.cargarCombo();
        jComboBox1.setModel(defaultComboBoxModel);
    }
    public void loadComCli() {
        defaultComboBoxModel = clienteDao.cargarCombo();
        jComboBox2.setModel(defaultComboBoxModel);
    }

    double calcularTotal() {
        double total = 0;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            double subtotal = Double.parseDouble(jTable2.getValueAt(i, 2).toString());
            total += subtotal;
        }
        return total;
    }

    double calcularCambio() {
        double tot = Double.parseDouble(total.getText());
        double pag = Double.parseDouble(pago.getText());
        double cambio = pag - tot;
        return cambio;
    }
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cambio = new javax.swing.JLabel();
        pago = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa (GR).png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/derecha.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/izquierda.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Tipo" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hecho.png"))); // NOI18N
        jButton1.setToolTipText("Guardar Venta");
        jButton1.setContentAreaFilled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Total");

        total.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Pago");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Cambio");

        cambio.setOpaque(true);

        pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoActionPerformed(evt);
            }
        });
        pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pagoKeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton7))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(38, 38, 38)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pago, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(26, 26, 26)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3))
                            .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(pago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton5)
                        .addGap(27, 27, 27)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        inicio.inicioV();
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        agregarVenta();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void pagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagoKeyReleased
        // TODO add your handling code here:
        if ((pago.getText()).equals("")) {
            JOptionPane.showMessageDialog(null, "Porfavor ingrese un pago");
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                cambio.setText(calcularCambio() + "");
            }
        }
    }//GEN-LAST:event_pagoKeyReleased

    private void pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cambio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField pago;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
