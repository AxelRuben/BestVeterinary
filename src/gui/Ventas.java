/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ClienteDao;
import dao.EmpleadoDao;
import dao.MiscelaneasDao;
import dao.VentaDao;
import dao.VentaHasMiscelaneasDao;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pojo.Cliente;
import pojo.Empleado;
import pojo.Miscelaneas;
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
        this.setTitle("Realizar venta");
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
        int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
        Miscelaneas miscelaneas = miscelaneasDao.selectedMiscelaneas(id);
        String nombre = jTable1.getValueAt(row, 1).toString();
        double precio = miscelaneas.getCosto();
        cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos desea llevar?"));
        int stock = miscelaneas.getStock();
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

        ventasMenu = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        view = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cambio = new javax.swing.JLabel();
        pago = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/izq.png"))); // NOI18N
        jButton4.setToolTipText("Regresar a Ventas");
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 90, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        jButton3.setToolTipText("Visualizar");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Total"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 550, 390));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa (GR).png"))); // NOI18N
        jButton8.setToolTipText("Inicio");
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventas-Fondo.jpg"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 580));

        javax.swing.GroupLayout ventasMenuLayout = new javax.swing.GroupLayout(ventasMenu.getContentPane());
        ventasMenu.getContentPane().setLayout(ventasMenuLayout);
        ventasMenuLayout.setHorizontalGroup(
            ventasMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventasMenuLayout.setVerticalGroup(
            ventasMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view.getContentPane());
        view.getContentPane().setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 290, 340));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa (GR).png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 130, 110));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/der1.png"))); // NOI18N
        jButton5.setToolTipText("Agregar producto");
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 240, 70, 50));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/izq1.png"))); // NOI18N
        jButton7.setToolTipText("Cancelar producto");
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 80, 50));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 340, 219));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hecho.png"))); // NOI18N
        jButton1.setToolTipText("Guardar Venta");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 80, 80));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Total");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, -1, -1));

        total.setOpaque(true);
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 367, 227, 22));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Pago");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Cambio");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 430, -1, -1));

        cambio.setOpaque(true);
        jPanel1.add(cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 439, 227, 22));

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
        jPanel1.add(pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 401, 227, 27));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 210, 28));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 200, 28));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        jButton2.setToolTipText("Visualizar ventas");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 79, 51));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventas-Fondo.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            insertar_ven();
            JOptionPane.showMessageDialog(null, "Exito al guardar venta");
        } catch (SQLException ex) {
            System.out.println("Error al insertar venta " + ex);;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {
//            cargarDatosV(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ventasMenu.dispose();
        this.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ventasMenu.setVisible(true);
        ventasMenu.setTitle("Ventas");
        ventasMenu.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
        ventasMenu.setSize(935, 620);
        ventasMenu.setLocationRelativeTo(null);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         // TODO add your handling code here:
         ventasMenu.dispose();
         inicio.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    void insertar_ven() throws SQLException {
        VentaDao ventaDao = new VentaDao();
        Venta_has_Miscelaneas vHM;
        VentaHasMiscelaneasDao vHMd = new VentaHasMiscelaneasDao();
        Empleado empleado = (Empleado)jComboBox1.getSelectedItem();
        int idemp = empleado.getIdempleado();
        Cliente cliente = (Cliente)jComboBox2.getSelectedItem();
        int idclio = cliente.getIdcliente();
        double to = Double.parseDouble(total.getText());
        Venta venta = new Venta(to, idemp, idclio);
        int id = ventaDao.insertar(venta);
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            vHM = new Venta_has_Miscelaneas(id, Integer.parseInt(jTable2.getValueAt(i, 0).toString()));
            vHMd.insertar(vHM);
        }
    }
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField pago;
    private javax.swing.JLabel total;
    private javax.swing.JDialog ventasMenu;
    private javax.swing.JDialog view;
    // End of variables declaration//GEN-END:variables
}
