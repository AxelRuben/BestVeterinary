/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.EmpleadoDao;
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
import pojo.Cliente;
import pojo.Empleado;

/**
 *
 * @author blanc
 */
public class Empleados extends javax.swing.JFrame {
TableRowSorter<TableModel> sorter;
    EmpleadoDao empleadoDao;
    
    
    java.util.Date fecha;
    /**
     * Creates new form empleados
     */
    public Empleados() {
        initComponents();
        empleadoDao = new EmpleadoDao();
        fecha = new java.util.Date();
        this.setTitle("Empleados");
        this.setLocationRelativeTo(null);
        loadModel();
        jRadioButton1.setSelected(true);
        this.setResizable(false);
        this.setSize(926,610);
        TableColumnModel columnModel = empleadoos.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(10);
    setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
    }
    Inicio inicio = new Inicio();
    
    
    public void filter(){
                try{
                    sorter.setRowFilter(RowFilter.regexFilter(jTextField4.getText().toUpperCase()));
                }catch(Exception e){
                    System.out.println("Texto vacío" + e);
                }
            }
    
    public int sexoDI(String sexoo) {
        int sex=0;
        if (sexoo.equals("F")) {
            sex = 1;
        } else if (sexoo.equals("M")) {
            sex = 2;
        }
        return sex;
    }
    public String sexoDV(String sexoo) {
        String sex="";
        if (sexoo.equals("F")) {
            sex = "Femenino";
        } else if (sexoo.equals("M")) {
            sex = "Masculino";
        }
        return sex;
    }
    
    void cargarDatosV(int id){
        view.setSize(550, 350);
        view.setTitle("Visualizar empleado");
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setResizable(false);
        view.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
        Empleado empleado= empleadoDao.selectedEmpleado(id);
        jnombre2.setText(empleado.getNombre());
//        sexo1.setText(sexoDI(cliente.getSexo()));
        aniV.setText("" + calcularA(calcularDias(empleado.getCumple())));
        jsexo2.setText(sexoDV(empleado.getSexo()));
        jespecialidad2.setText(empleado.getEspecialidad());
        jhorario2.setText(empleado.getHorario());
//        jcontacto2.setText(cliente.getContacto());
//        jcorreo2.setText(cliente.getCorreo());
//        jdireccion2.setText(cliente.getDireccion());
    }
    
    int calcularDias(java.util.Date cumpleanios) {
        int dias = 0;
        int anio = cumpleanios.getYear();
        int mes = cumpleanios.getMonth();
        int dia = cumpleanios.getDate();
        java.util.Date fechaHoy = new java.util.Date();
        while (true) {
            if (cumpleanios.compareTo(fechaHoy) >= 0) {
                break;
            } else {
                dias++;
                dia++;
                cumpleanios = new java.util.Date(anio, mes, dia);
            }
        }
     
        return dias;
    }
boolean delete(int id){
        int option = JOptionPane.showConfirmDialog(null, "¿Desea dar de baja a este paciente?","Confirmación",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        boolean result= false;
        System.out.println(option);
        if (option==0) {
            result= empleadoDao.delete_empleado(id);
        }
        return result;
    }
    int calcularA(int diass) {
        int anioss = diass / 365;
        return anioss;
    }
    
    void cargarDatos(int id){
        update.setSize(560, 330);
        update.setResizable(false);
        update.setTitle("Editar empleado");
        update.setVisible(true);
        update.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
        update.setLocationRelativeTo(null);
        Empleado empleado = empleadoDao.selectedEmpleado(id);
        nombre1.setText(empleado.getNombre());
        cumpleanios1.setDate(empleado.getCumple());
        especialidad1.setText(empleado.getEspecialidad());
        jCheckBox1.setSelected(empleado.isActivo());
        System.out.println(""+empleado.isActivo());
        
    }
    
    
    
    
    int addTrabajadores() throws SQLException{
    String name = jnombre.getText();
    String sex= jsexo.getSelectedItem().toString();
    String esp= jespecialidad.getText();
        java.sql.Date cumpll = new java.sql.Date(cumpleanios.getDate().getTime());
    String hor= jhorario.getSelectedItem().toString();
        Empleado empleado = new Empleado(name, sex, esp, cumpll, hor);
    int id = empleadoDao.insertar(empleado);
    empleadoos.setModel(empleadoDao.cargarModelo());
    return id;
    
    }
    public void loadModel() {
        DefaultTableModel dt = empleadoDao.cargarModelo();
        sorter = new TableRowSorter<>(dt);
        empleadoos.setModel(dt);
        empleadoos.setAutoCreateRowSorter(true);
        empleadoos.setRowSorter(sorter);
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
        jLabel3 = new javax.swing.JLabel();
        jnombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jespecialidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jsexo = new javax.swing.JComboBox<>();
        jhorario = new javax.swing.JComboBox<>();
        cumpleanios = new com.toedter.calendar.JDateChooser();
        update = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        nombre1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        especialidad1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        sexo1 = new javax.swing.JComboBox<>();
        horario1 = new javax.swing.JComboBox<>();
        cumpleanios1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        view = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jnombre2 = new javax.swing.JLabel();
        jespecialidad2 = new javax.swing.JLabel();
        jhorario2 = new javax.swing.JLabel();
        aniV = new javax.swing.JLabel();
        jsexo2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        empleadoos = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(196, 250, 251));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Nombre");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        jPanel2.add(jnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 227, 29));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Cumpleaños");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Sexo");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));
        jPanel2.add(jespecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 227, 29));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Especialidad");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel15.setText("Horario");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jsexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        jPanel2.add(jsexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 227, 31));

        jhorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        jPanel2.add(jhorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 227, 31));
        jPanel2.add(cumpleanios, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 230, 30));

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add.getContentPane());
        add.getContentPane().setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(196, 250, 251));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Nombre");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
        jPanel3.add(nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 227, 29));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Cumpleaños");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Sexo");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));
        jPanel3.add(especialidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 227, 29));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel11.setText("Especialidad");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel16.setText("Horario");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        sexo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        jPanel3.add(sexo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 227, 31));

        horario1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        jPanel3.add(horario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 227, 31));
        jPanel3.add(cumpleanios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 230, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setText("Activo");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update.getContentPane());
        update.getContentPane().setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(196, 250, 251));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setText("Nombre");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel14.setText("Años");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel17.setText("Sexo");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel18.setText("Especialidad");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hecho.png"))); // NOI18N
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel19.setText("Horario");

        jnombre2.setBackground(new java.awt.Color(255, 255, 255));
        jnombre2.setOpaque(true);

        jespecialidad2.setBackground(new java.awt.Color(255, 255, 255));
        jespecialidad2.setOpaque(true);

        jhorario2.setBackground(new java.awt.Color(255, 255, 255));
        jhorario2.setOpaque(true);

        aniV.setBackground(new java.awt.Color(255, 255, 255));
        aniV.setOpaque(true);

        jsexo2.setBackground(new java.awt.Color(255, 255, 255));
        jsexo2.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(69, 69, 69))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(91, 91, 91)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jnombre2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aniV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton11))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jsexo2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(73, 73, 73)
                                .addComponent(jhorario2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(32, 32, 32)
                                .addComponent(jespecialidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel14))
                            .addComponent(jButton11))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jnombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aniV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jsexo2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jespecialidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jhorario2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view.getContentPane());
        view.getContentPane().setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas.png"))); // NOI18N
        jButton1.setToolTipText("Agregar");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 70, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lapiz.png"))); // NOI18N
        jButton2.setToolTipText("Modificar");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 70, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        jButton3.setToolTipText("Visualizar");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 70, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/basura.png"))); // NOI18N
        jButton4.setToolTipText("Dar de baja");
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 70, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa (GR).png"))); // NOI18N
        jButton6.setToolTipText("Inicio");
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 130, 110));

        empleadoos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Turno"
            }
        ));
        jScrollPane1.setViewportView(empleadoos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 157, 420, 410));

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 190, 32));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 40, -1));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Todos");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Inactivo");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Activo");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/empleados-Fondo.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 580));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        add.setSize(520, 320);
        add.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
        add.setVisible(true);
        add.setLocationRelativeTo(null);
        add.setTitle("Añadir empleado");
        add.setResizable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        inicio.inicioV();
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        add.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        update.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        view.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         try {
            if (jnombre.getText().equals("")||cumpleanios.getDate()==null||jespecialidad.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor inserte los datos");
            }else if (addTrabajadores()!=0) {
                JOptionPane.showMessageDialog(null, "Éxito al insertar empleado");
                add.dispose();
                jnombre.setText("");
                cumpleanios.setDate(fecha);
                jsexo.setSelectedIndex(0);
                jhorario.setSelectedIndex(0);
                jespecialidad.setText("");
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado" + ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
         // TODO add your handling code here:
         filter();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
          // TODO add your handling code here:
         
        if (nombre1.getText().equals("")||especialidad1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor inserte datos");
        }else{
        int row = empleadoos.getSelectedRow();
        
        int id= (int) empleadoos.getValueAt(row, 0);
            System.out.println(id);
        String nombreU=nombre1.getText();
        System.out.println(nombreU);
        String sexoU=sexo1.getSelectedItem().toString();
        System.out.println(sexoU);
        String especialidadU=especialidad1.getText();
        System.out.println(especialidadU);
        java.sql.Date cump = new java.sql.Date(cumpleanios1.getDate().getTime());
        System.out.println(cump);
        String horarioU=horario1.getSelectedItem().toString();
        System.out.println(horarioU);
        boolean act=jCheckBox1.isSelected();
        System.out.println(act);
        Empleado empleado = new Empleado(id, nombreU, sexoU, especialidadU, cump, horarioU,act);
        boolean op = empleadoDao.actualizar_empleado(empleado);
            System.out.println(""+op);
        if (op) {
            JOptionPane.showMessageDialog(this, "El empleado se modificó con éxito");
            nombre1.setText("");
            especialidad1.setText("");
            cumpleanios1.setDate(fecha);
            jCheckBox1.setSelected(true);
            update.dispose();
            loadModel();
        } else {
            JOptionPane.showMessageDialog(this, "Verifique sus datos");
        }
        }
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         // TODO add your handling code here:
        if (empleadoos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {
            cargarDatos(Integer.parseInt(empleadoos.getValueAt(empleadoos.getSelectedRow(), 0).toString()));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         // TODO add your handling code here:
         if (empleadoos.getSelectedRow()==-1) {
             JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {
        cargarDatosV(Integer.parseInt(empleadoos.getValueAt(empleadoos.getSelectedRow(), 0).toString()));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         // TODO add your handling code here:
         if (empleadoos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {
         int row = empleadoos.getSelectedRow(); //Se obtiene la linea seleccionada
         int id= (int) empleadoos.getValueAt(row, 0); //Obtengo el ID del amigo
         if (delete(id)) {
            JOptionPane.showMessageDialog(null, "Éxito al dar de baja al paciente");
            loadModel();
        }else{
             JOptionPane.showMessageDialog(null, "Error al dar de baja al paciente");
         }
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
         // TODO add your handling code here:
         empleadoos.setModel(empleadoDao.cargarModeloA(true));
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
         // TODO add your handling code here:
         empleadoos.setModel(empleadoDao.cargarModeloA(false));
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
         // TODO add your handling code here:
         empleadoos.setModel(empleadoDao.cargarModelo());
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog add;
    private javax.swing.JLabel aniV;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser cumpleanios;
    private com.toedter.calendar.JDateChooser cumpleanios1;
    private javax.swing.JTable empleadoos;
    private javax.swing.JTextField especialidad1;
    private javax.swing.JComboBox<String> horario1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jespecialidad;
    private javax.swing.JLabel jespecialidad2;
    private javax.swing.JComboBox<String> jhorario;
    private javax.swing.JLabel jhorario2;
    private javax.swing.JTextField jnombre;
    private javax.swing.JLabel jnombre2;
    private javax.swing.JComboBox<String> jsexo;
    private javax.swing.JLabel jsexo2;
    private javax.swing.JTextField nombre1;
    private javax.swing.JComboBox<String> sexo1;
    private javax.swing.JDialog update;
    private javax.swing.JDialog view;
    // End of variables declaration//GEN-END:variables
}
