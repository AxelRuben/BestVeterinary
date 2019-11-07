/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.TipoAnimalDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pojo.TipoAnimal;

/**
 *
 * @author blanc
 */
public class Razas extends javax.swing.JFrame {
String path="";
    TipoAnimalDao tipoAnimalDao = new TipoAnimalDao();
    TableRowSorter<TableModel> sorter;
    /**
     * Creates new form Razas
     */
    public Razas() {
        initComponents();
        this.setTitle("Razas");
        this.setSize(425,525);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        tipoAnimalDao= new TipoAnimalDao();
        loadModel();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
    }
    Inicio in = new Inicio();
    
   
    int addRaza() throws SQLException{
    String name = nombre.getText();
    String image= path;
    String tipe= tipo.getSelectedItem().toString();
        TipoAnimal tipoAnimal = new TipoAnimal(tipe, image, name);
    
    int id = tipoAnimalDao.insertar(tipoAnimal);
    return id;   
}
    
    
    public void loadModel() {
        DefaultTableModel dt = tipoAnimalDao.cargarModelo();
        sorter = new TableRowSorter<>(dt);
        TableColumnModel columnModel = datos.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(5);
        columnModel.getColumn(2).setPreferredWidth(25);
        datos.setColumnModel(columnModel);
        datos.setModel(dt);
        datos.setAutoCreateRowSorter(true);
        datos.setRowSorter(sorter);
    }
    
     void cargarDatos(int id){
       update.setSize(670, 500);
        update.setTitle("Añadir Raza");
        update.setVisible(true);
        update.setLocationRelativeTo(null);
        TipoAnimal tipoAnimal = tipoAnimalDao.selectedAnimal(id);
        nombre1.setText(tipoAnimal.getRaza());
//        sexo1.setText(sexoDI(cliente.getSexo()));
        nombre1.setText(tipoAnimal.getRaza());
    }
     
     boolean delete(int id){
        int option = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminar la raza?","Confirmación",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        boolean result= false;
        System.out.println(option);
        if (option==0) {
            result= tipoAnimalDao.delete_raza(id);
        }
        return result;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        agregarfoto = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        update = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        nombre1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        imagen1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        raza1 = new javax.swing.JComboBox<>();
        view = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(204, 170, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 76, -1));
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 166, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Imagen");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 76, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 76, -1));

        imagen.setOpaque(true);
        jPanel2.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 240, 172));

        agregarfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas.png"))); // NOI18N
        agregarfoto.setToolTipText("Añadir imágen");
        agregarfoto.setContentAreaFilled(false);
        jPanel2.add(agregarfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 73, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pregunta.png"))); // NOI18N
        jLabel6.setToolTipText("Selecciona y guarda una foto en caso de necesitar una referencia.");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, 31));

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato", "Ave", "Roedor", "Reptil", "Pez", "General", "Otros..." }));
        jPanel2.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 166, 30));

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add.getContentPane());
        add.getContentPane().setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 170, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 70, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 60, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 76, -1));
        jPanel3.add(nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 166, 28));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Imagen");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 76, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tipo");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 76, -1));

        imagen1.setOpaque(true);
        jPanel3.add(imagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 240, 172));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas.png"))); // NOI18N
        jButton2.setToolTipText("Añadir imágen");
        jButton2.setContentAreaFilled(false);
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 73, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pregunta.png"))); // NOI18N
        jLabel12.setToolTipText("Selecciona y guarda una foto en caso de necesitar una referencia.");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, 31));

        raza1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato", "Ave", "Roedor", "Reptil", "Pez", "General", "Otros..." }));
        jPanel3.add(raza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 166, 30));

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update.getContentPane());
        update.getContentPane().setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 170, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hecho.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Nombre");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 76, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Imagen");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 76, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Tipo");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 76, -1));

        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel20.setOpaque(true);
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 166, 28));

        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel21.setOpaque(true);
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 166, 28));

        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 290, 196));

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view.getContentPane());
        view.getContentPane().setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(datos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 253, 281));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas.png"))); // NOI18N
        jButton14.setToolTipText("Agregar");
        jButton14.setContentAreaFilled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 70, -1));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lapiz.png"))); // NOI18N
        jButton15.setToolTipText("Modificar");
        jButton15.setContentAreaFilled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 70, -1));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/basura.png"))); // NOI18N
        jButton16.setToolTipText("Eliminar");
        jButton16.setContentAreaFilled(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 70, -1));

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa (2).png"))); // NOI18N
        jButton17.setToolTipText("Home");
        jButton17.setContentAreaFilled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 70, 60));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        jButton3.setToolTipText("Ver");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 70, 40));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/razas-Fondo.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        add.setSize(537, 478);
        add.setTitle("Añadir Raza");
        add.setVisible(true);
        add.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        in.inicioV();
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        update.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         // TODO add your handling code here:
        view.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
         // TODO add your handling code here:
        cargarDatos(Integer.parseInt(datos.getValueAt(datos.getSelectedRow(), 0).toString()));
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        view.setSize(670, 500);
        view.setTitle("Visualizar Raza");
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         // TODO add your handling code here:
         int row = datos.getSelectedRow();
        
        int id= (int) datos.getValueAt(row, 0);
        String razaU=raza1.getSelectedItem().toString();
        String imagenU=path;
        String nombreU=nombre1.getText();
        TipoAnimal tipoAnimal = new TipoAnimal(id, razaU, imagenU, nombreU);
        if (tipoAnimalDao.actualizar_TipoAnimal(tipoAnimal)) {
            JOptionPane.showMessageDialog(this, "La raza se modificó con exito");
            nombre1.setText("");
            imagen1.setText("");
            update.dispose();
            loadModel();
        } else {
            JOptionPane.showMessageDialog(this, "Verifique sus datos");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
         // TODO add your handling code here:
         int row = datos.getSelectedRow(); //Se obtiene la linea seleccionada
         int id= (int) datos.getValueAt(row, 0); //Obtengo el ID del amigo
         if (delete(id)) {
            JOptionPane.showMessageDialog(null, "Éxito al eliminar raza");
            loadModel();
        }else{
             JOptionPane.showMessageDialog(null, "Error al eliminar raza");
         }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            // SAVE
            if (addRaza()!=0) {
                JOptionPane.showMessageDialog(null, "Éxito");
                add.dispose();
                datos.setModel(tipoAnimalDao.cargarModelo());
                nombre.setText("");
                imagen.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Razas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        add.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Razas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Razas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Razas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Razas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Razas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog add;
    private javax.swing.JButton agregarfoto;
    private javax.swing.JTable datos;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel imagen1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField nombre1;
    private javax.swing.JComboBox<String> raza1;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JDialog update;
    private javax.swing.JDialog view;
    // End of variables declaration//GEN-END:variables
}
