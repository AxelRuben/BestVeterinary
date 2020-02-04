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
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import pojo.Paciente;
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
    VentaDao ventaDao = new VentaDao();
    DefaultTableModel dTMIn = new DefaultTableModel();
    DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
    int idV;

    /**
     * Creates new form Ventas
     */
    public Ventas() {
        initComponents();
        this.setTitle("Realizar venta");
        this.setLocationRelativeTo(null);
        miscelaneasDao = new MiscelaneasDao();
        Object iden[] = {"Id", "Nombre", "Subtotal"} ;
        dTMIn.setColumnIdentifiers(iden);
        loadModelIn();
        loadComEmp();
        this.setResizable(false);
        this.setSize(926, 610);
        loadComCli();
        setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
    }
    Inicio inicio = new Inicio();

    public void loadModelIn() {
        DefaultTableModel dt = miscelaneasDao.cargarModeloV();
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
    void agregarVenta() throws SQLException {
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel tabla1 = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
        Miscelaneas miscelaneas = miscelaneasDao.selectedMiscelaneas(id);
        String tipo = miscelaneas.getTipo();
        String nombre = jTable1.getValueAt(row, 1).toString();
        double precio = miscelaneas.getCosto();
        if (tipo.equalsIgnoreCase("Servicios")) {
            Object producto[] = {id, nombre, precio};
            table2.addRow(producto);
            tabla1.removeRow(row);
            total.setText(calcularTotal() + "");

        } else if (tipo.equalsIgnoreCase("Medicamento") || tipo.equalsIgnoreCase("Articulos")) {
            cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos desea llevar?"));
            int stock = miscelaneas.getStock();
            double subtotal = precio * cantidad;

            if (verificarStock(cantidad, stock)) {
                Object producto[] = {id, nombre, subtotal};
                table2.addRow(producto);
                tabla1.removeRow(row);
                total.setText(calcularTotal() + "");
                if (!miscelaneasDao.actualizar_stock(id, stock-cantidad)) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar Stock");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay existencias suficientes");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error fatality");
        }
    }
    
    void regresarVenta() throws SQLException {
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel tabla1 = (DefaultTableModel) jTable1.getModel();
        int row = jTable2.getSelectedRow();
        int id = Integer.parseInt(jTable2.getValueAt(row, 0).toString());
        Miscelaneas miscelaneas = miscelaneasDao.selectedMiscelaneas(id);
        String tipo = miscelaneas.getTipo();
        if (tipo.equalsIgnoreCase("Servicios")) {
            Object producto[] = {id, miscelaneas.getNombre(), tipo, miscelaneas.getCosto()};
            tabla1.addRow(producto);
            table2.removeRow(row);
            total.setText(calcularTotal() + "");

        } else if (tipo.equalsIgnoreCase("Medicamento") || tipo.equalsIgnoreCase("Articulos")) {
            cantidad = (int) (Double.parseDouble(jTable2.getValueAt(row, 2).toString())/Double.parseDouble(jTable2.getValueAt(row, 2).toString()));
            int stock = miscelaneas.getStock();
                Object producto[] = {id, miscelaneas.getNombre(), tipo, miscelaneas.getCosto()};
                tabla1.addRow(producto);
                table2.removeRow(row);
                total.setText(calcularTotal() + "");
                if (!miscelaneasDao.actualizar_stock(id, stock+cantidad)) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar Stock");
                }
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error fatality");
        }
    }
    String articulos = "";

    void exportFormat() throws IOException, XDocReportException {
        InputStream in = Pacientes.class.getResourceAsStream("ventasFormato.docx");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                in, TemplateEngineKind.Velocity);
        IContext context = report.createContext();
        Venta venta = ventaDao.selectedVenta(idV);
        java.util.Date date = new java.util.Date();
        Cliente cliente1 = clienteDao.selectedCliente(venta.getCliente_idcliente());
        context.put("nombrec", jTable3.getValueAt(jTable3.getSelectedRow(), 3));
        context.put("fechac", date.getDate() + "-" + date.getMonth() +"-"+ date.getYear());
        context.put("empleadoc", jTable3.getValueAt(jTable3.getSelectedRow(), 2));
        context.put("totalc", t.getText());
        context.put("pagoc", p.getText());
        context.put("cambioc", c.getText());

        for (int i = 0; i < tablaVenta.getRowCount(); i++) {
            articulos += (String) tablaVenta.getValueAt(i, 1) + "\n";
        }
        context.put("articulosc", articulos);
            File file = new File(System.getProperty("user.home")+"\\OneDrive\\Documentos\\BestVeterinarySA");
            if (!file.exists()) {
            file.mkdir();
                System.out.println("Creado");
        }
    File file1 = new File(System.getProperty("user.home")+"\\OneDrive\\Documentos\\BestVeterinarySA\\Tickets");
            if (!file1.exists()) {
            file1.mkdir();
                System.out.println("Creado 2");
        }
    String nombreSalida="Venta-"+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+date.getMinutes()+".docx";
        
        OutputStream out = new FileOutputStream(new File(System.getProperty("user.home")+"/OneDrive/Documentos/BestVeterinarySA/Tickets/" + nombreSalida));
        report.process(context, out);
        System.out.println("Creado con éxito");
        JOptionPane.showMessageDialog(null, "Éxito al exportar el ticket");
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        t = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        p = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 90, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        jButton3.setToolTipText("Visualizar");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel3.setBackground(new java.awt.Color(222, 165, 164));

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablaVenta);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/izq.png"))); // NOI18N
        jButton9.setToolTipText("Regresar");
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Productos adquiridos en la venta: ");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export.png"))); // NOI18N
        jButton10.setToolTipText("Exportar");
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel9.setText("Total:");

        t.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setText("Pago:");

        p.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setText("Cambio:");

        c.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                            .addComponent(t, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view.getContentPane());
        view.getContentPane().setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel8.setText("jLabel8");

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 330, 410));

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
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
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
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 210, 28));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 200, 28));

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
        try {
            // TODO add your handling code here:
            if (jTable1.getSelectedRow()==-1) {
                JOptionPane.showMessageDialog(null, "Seleccione un dato");
            } else {
            agregarVenta();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar Venta");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void pagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagoKeyReleased
        // TODO add your handling code here
        try {
        if (Double.parseDouble(total.getText())<=Double.parseDouble(pago.getText())) {
                cambio.setText(calcularCambio() + "");
        }
        } catch (Exception e) {
            cambio.setText("");
        }
    }//GEN-LAST:event_pagoKeyReleased

    private void pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTable2.getRowCount() == 0 || jComboBox1.getSelectedIndex() == 0 || jComboBox2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");

        } else {
            try {
                insertar_ven();
                resetear();
                JOptionPane.showMessageDialog(null, "Éxito al guardar venta");
            } catch (SQLException ex) {
                System.out.println("Error al insertar venta " + ex);;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTable3.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {
            ventasMenu.dispose();
            view.setVisible(true);
            view.setSize(800, 500);
            view.setLocationRelativeTo(null);
            view.setTitle("Detalles de Venta");
            view.setResizable(false);
            view.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
            VentaHasMiscelaneasDao vHMD = new VentaHasMiscelaneasDao();
            Venta venta = ventaDao.selectedVenta(Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString()));
            tablaVenta.setModel(vHMD.cargarModelo(Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString())));
            jLabel7.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            t.setText("" + venta.getTotal());
            p.setText("" + venta.getPago());
            c.setText("" + venta.getCambio());
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
        ventasMenu.setSize(925, 600);
        ventasMenu.setResizable(false);
        ventasMenu.setLocationRelativeTo(null);
        this.setVisible(false);
        jTable3.setModel(ventaDao.cargarModelo());

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ventasMenu.dispose();
        inicio.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // TODO add your handling code here:
            if (jTable2.getSelectedRow()==-1) {
                JOptionPane.showMessageDialog(null, "Seleccione un dato");
            } else {
            regresarVenta();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar Venta "+ ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        view.dispose();
        ventasMenu.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            exportFormat();
        } catch (IOException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XDocReportException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    void resetear(){
        loadModelIn();
        jTable2.setModel(dTMIn);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        total.setText("");
        pago.setText("");
        cambio.setText("");
    }
    
    
    void insertar_ven() throws SQLException {
        VentaDao ventaDao = new VentaDao();
        Venta_has_Miscelaneas vHM;
        VentaHasMiscelaneasDao vHMd = new VentaHasMiscelaneasDao();
        Empleado empleado = (Empleado) jComboBox1.getSelectedItem();
        int idemp = empleado.getIdempleado();
        Cliente cliente = (Cliente) jComboBox2.getSelectedItem();
        int idclio = cliente.getIdcliente();
        double to = Double.parseDouble(total.getText());
        double pa = Double.parseDouble(pago.getText());
        double cam = Double.parseDouble(cambio.getText());
        Venta venta = new Venta(to, idemp, idclio, cam, pa);
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
    private javax.swing.JLabel c;
    private javax.swing.JLabel cambio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel p;
    private javax.swing.JTextField pago;
    private javax.swing.JLabel t;
    private javax.swing.JTable tablaVenta;
    private javax.swing.JLabel total;
    private javax.swing.JDialog ventasMenu;
    private javax.swing.JDialog view;
    // End of variables declaration//GEN-END:variables
}
