/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ClienteDao;
import dao.PacienteDao;
import dao.TipoAnimalDao;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SpinnerModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pojo.Cliente;
import pojo.Paciente;
import pojo.TipoAnimal;

/**
 *
 * @author blanc
 */
public class Pacientes extends javax.swing.JFrame {

    PacienteDao pacienteDao;
    ClienteDao clienteDao;
    TipoAnimalDao tipoAnimalDao;
    TableRowSorter<TableModel> sorter;
    java.util.Date fecha;
    /**
     * Creates new form Pacientes
     */
    
    public Pacientes() {
        initComponents();
        pacienteDao = new PacienteDao();
        clienteDao = new ClienteDao();
        tipoAnimalDao = new TipoAnimalDao();
        fecha = new java.util.Date();
        this.setTitle("Pacientes");
        this.setLocationRelativeTo(null);
        loadModel();
        loadCombooC();
        loadCombooR();
        setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
       
    
    }
    Inicio in = new Inicio();
    
    
public void filter(){
                try{
                    sorter.setRowFilter(RowFilter.regexFilter(jTextField3.getText(), jComboBox6.getSelectedIndex()));
                }catch(Exception e){
                    System.out.println("Texto vacío" + e);
                }
            }
    public void loadModel() {
        DefaultTableModel dt = pacienteDao.cargarModelo();
        sorter = new TableRowSorter<>(dt);
        jTable1.setModel(dt);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setRowSorter(sorter);
    }

    public void loadCombooC() {
        DefaultComboBoxModel dc = clienteDao.cargarCombo();
        jdueno.setModel(dc);
        jdueno2.setModel(dc);
    }

    public void loadCombooR() {
        DefaultComboBoxModel dc = tipoAnimalDao.cargarCombo();
        jtipo.setModel(dc);
        jtipo2.setModel(dc);
    }

    int addPaciente() throws SQLException {
        Cliente cliente = (Cliente) jdueno.getSelectedItem();
        TipoAnimal tipoAnimal = (TipoAnimal) jtipo.getSelectedItem();
        String name = jnombre.getText();
        Date cum = (Date) cumplea.getDate();
        String des = jTextArea1.getText();
        String sex = jsexo.getSelectedItem().toString();
        int cli = cliente.getIdcliente();
        int raz = tipoAnimal.getIdtipoAnimal();
        Paciente paciente = new Paciente(name, des, sex, cli, raz, cum);
        int id = pacienteDao.insertar(paciente);
        jTable1.setModel(clienteDao.cargarModelo());
        loadCombooC();
        loadCombooR();
        return id;

    }
    public int sexoDI(String sexoo) {
        int sex=0;
        if (sexoo.equals("M")) {
            sex = 1;
        } else if (sexoo.equals("H")) {
            sex = 2;
        }
        return sex;
    }
    
    
    
    
    int calcularDias(Date cumpleanios){
        int dias = 0;
        int anio = cumpleanios.getYear();
        int mes = cumpleanios.getMonth();
        int dia = cumpleanios.getDate();
        java.util.Date fechaHoy = new java.util.Date();
        while(true){
            if (cumpleanios.compareTo(fechaHoy)==0) {
                break;
            } else {
                dias++;
                dia++;
                cumpleanios = new Date(anio, mes, dia);
            }
        }
        return dias;
    }
    
    
    
    
    
    
    void cargarDatos(int id){
        update.setSize(600, 400);
        update.setTitle("Añadir paciente");
        update.setVisible(true);
        update.setLocationRelativeTo(null);
        update.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
        Paciente paciente = pacienteDao.selectedPaciente(id);
        jnombre2.setText(paciente.getNombre());
        Date fecha;
        /*
//        sexo1.setText(sexoDI(cliente.getSexo()));
        jedad2.setText(""+paciente.getEdad());
        jsexo2.setSelectedItem(sexoDI(paciente.getSexo()));
//        jtipo2.setText(cliente.getContacto());
//        correo1.setText(cliente.getCorreo());
        jTextArea4.setText(paciente.getDescripcion());*/
    }

    
    
    void cargarDatosV(int id){
        update.setSize(600, 400);
        update.setTitle("Añadir paciente");
        update.setVisible(true);
        update.setLocationRelativeTo(null);
        update.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
        Paciente paciente = pacienteDao.selectedPaciente(id);
        nom.setText(paciente.getNombre());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        cum.setText(simpleDateFormat.format(paciente.getCumple()));
    }
    
    
    
    boolean delete(int id){
        int option = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminar el paciente?","Confirmación",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        boolean result= false;
        System.out.println(option);
        if (option==0) {
            result= pacienteDao.delete_paciente(id);
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
        jLabel3 = new javax.swing.JLabel();
        jnombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdescripcion = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jtipo = new javax.swing.JComboBox<>();
        jdueno = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        guardarAdd = new javax.swing.JButton();
        jsexo = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        cumplea = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        view = new javax.swing.JDialog();
        nombr = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        cum = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        sex = new javax.swing.JLabel();
        raz = new javax.swing.JLabel();
        due = new javax.swing.JLabel();
        des = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        ani = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        mes = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        update = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jnombre2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jdescripcion2 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jtipo2 = new javax.swing.JComboBox<>();
        jdueno2 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jsexo2 = new javax.swing.JComboBox<>();
        guardarUpda = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        cumpleUp = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(251, 230, 229));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setText("Cumpleaños");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel2.add(jnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 227, 29));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setText("Sexo");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setText("Raza");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setText("Dueño");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setText("Descripción");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jdescripcion.setViewportView(jTextArea1);

        jPanel2.add(jdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 308, 130));

        jtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Husky", "Labrador", "Chow chow", "Frensh Poodle" }));
        jtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtipoActionPerformed(evt);
            }
        });
        jPanel2.add(jtipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 227, 31));

        jdueno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jdueno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jduenoActionPerformed(evt);
            }
        });
        jPanel2.add(jdueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 227, 32));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, -1, -1));

        guardarAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible.png"))); // NOI18N
        guardarAdd.setContentAreaFilled(false);
        guardarAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAddActionPerformed(evt);
            }
        });
        jPanel2.add(guardarAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jsexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Macho", "Hembra" }));
        jPanel2.add(jsexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 227, 31));

        jButton8.setText("...");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));
        jPanel2.add(cumplea, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 230, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add.getContentPane());
        add.getContentPane().setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        nombr.setBackground(new java.awt.Color(204, 204, 204));
        nombr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nombre");
        nombr.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Cumpleaños");
        nombr.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Sexo");
        nombr.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Raza");
        nombr.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Dueño");
        nombr.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Descripción");
        nombr.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hecho.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        nombr.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

        cum.setOpaque(true);
        nombr.add(cum, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 246, 22));

        nom.setOpaque(true);
        nombr.add(nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 246, 22));

        sex.setOpaque(true);
        nombr.add(sex, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 246, 22));

        raz.setOpaque(true);
        nombr.add(raz, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 246, 22));

        due.setOpaque(true);
        nombr.add(due, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 246, 22));

        des.setOpaque(true);
        nombr.add(des, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 246, 111));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Años");
        nombr.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        ani.setOpaque(true);
        nombr.add(ani, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 70, 22));

        jLabel30.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Edad");
        nombr.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        mes.setOpaque(true);
        nombr.add(mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 70, 22));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Meses");
        nombr.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view.getContentPane());
        view.getContentPane().setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nombr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nombr, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(251, 230, 229));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel23.setText("Cumpleaños");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel5.add(jnombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 227, 29));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel25.setText("Sexo");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel26.setText("Raza");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel27.setText("Dueño");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel28.setText("Descripción");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jdescripcion2.setViewportView(jTextArea4);

        jPanel5.add(jdescripcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 308, 130));

        jtipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Husky", "Labrador", "Chow chow", "Frensh Poodle" }));
        jtipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtipo2ActionPerformed(evt);
            }
        });
        jPanel5.add(jtipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 227, 31));

        jdueno2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jdueno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 227, 32));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        jsexo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Macho", "Hembra" }));
        jPanel5.add(jsexo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 227, 31));

        guardarUpda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible.png"))); // NOI18N
        guardarUpda.setContentAreaFilled(false);
        guardarUpda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarUpdaActionPerformed(evt);
            }
        });
        jPanel5.add(guardarUpda, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 81));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel31.setText("Nombre");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jPanel5.add(cumpleUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 230, 30));

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update.getContentPane());
        update.getContentPane().setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Raza", "Dueño"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 400, 400));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas.png"))); // NOI18N
        jButton1.setToolTipText("Agregar");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lapiz.png"))); // NOI18N
        jButton2.setToolTipText("Modificar");
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        jButton3.setToolTipText("Ver");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/basura.png"))); // NOI18N
        jButton4.setToolTipText("Eliminar");
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa (GR).png"))); // NOI18N
        jButton5.setToolTipText("Home");
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 130, 110));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 210, 32));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 40, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Tipo" }));
        jPanel1.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 140, 32));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pacientes-Fondo.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        in.inicioV();
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        add.setSize(600, 470);
        add.setTitle("Añadir paciente");
        add.setVisible(true);
        add.setLocationRelativeTo(null);
        add.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        view.setSize(510, 470);
        view.setTitle("Visualizar");
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon-V.png")).getImage());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        view.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jtipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtipo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtipo2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        update.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
         // TODO add your handling code here:
         filter();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void guardarUpdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarUpdaActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        Cliente cliente2 = (Cliente) jdueno2.getSelectedItem();
        TipoAnimal tipoAnimal2 = (TipoAnimal) jtipo2.getSelectedItem();
        
        int id= (int) jTable1.getValueAt(row, 0);
        String nombreU=jnombre2.getText();
        String descrU=jTextArea4.getText();
        String sexoU=jsexo2.getSelectedItem().toString();
        int cli2 = cliente2.getIdcliente();
        int raz2 = tipoAnimal2.getIdtipoAnimal();
        Date cum2 = (Date) cumpleUp.getDate();
        Paciente paciente = new Paciente(id, nombreU, descrU, sexoU, cli2, raz2, cum2);
        
        if (pacienteDao.actualizar_paciente(paciente)) {
            JOptionPane.showMessageDialog(this, "El Paciente se modificó con exito");
            jnombre2.setText("");
            jTextArea4.setText("");
            cumpleUp.setDate(fecha);
            update.dispose();
            loadModel();
        } else {
            JOptionPane.showMessageDialog(this, "Verifique sus datos");
        }
    }//GEN-LAST:event_guardarUpdaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         // TODO add your handling code here:
         if (jTable1.getSelectedRow()==-1) {
             JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {
         cargarDatos(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         // TODO add your handling code here:
         int row = jTable1.getSelectedRow(); //Se obtiene la linea seleccionada
         int id= (int) jTable1.getValueAt(row, 0); //Obtengo el ID del amigo
         if (delete(id)) {
            JOptionPane.showMessageDialog(null, "Éxito al eliminar paciente");
            loadModel();
        }else{
             JOptionPane.showMessageDialog(null, "Error al eliminar paciente");
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Razas razas = new Razas();
        razas.setVisible(true);
        razas.setSize(430, 520);
        razas.setLocationRelativeTo(null);
        razas.setTitle("Ingresar Raza");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void guardarAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAddActionPerformed
        try {
            addPaciente();
            JOptionPane.showMessageDialog(null, "El paciente se insertó con exito");
            jTable1.setModel(pacienteDao.cargarModelo());
            add.dispose();
            jnombre.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar paciente");
            Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarAddActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        add.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jduenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jduenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jduenoActionPerformed

    private void jtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtipoActionPerformed

    public String openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(jLabel1);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return "Canceled";
        }
    }

    void openDialog() {
        add.setVisible(true);
        add.setSize(293, 456);
        add.setLocationRelativeTo(null);
    }

//    void openDialogSelect(int id) {
//        view.setSize(293, 456);
//        view.setLocationRelativeTo(null);
//        view.setVisible(true);
//        Paciente paciente = pacienteDao.selectedFriend(id);
//        jnombre.setText(paciente.getNombre());
//        jedad.setText(paciente.getEdad());
//        jsexo.setSelectedItem(paciente.getSexo());
//        jTextField8.setText(paciente.getTipoAnimal_idtipoAnimal());
//
//    }

    

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
            java.util.logging.Logger.getLogger(Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog add;
    private javax.swing.JLabel ani;
    private javax.swing.JLabel cum;
    private com.toedter.calendar.JDateChooser cumpleUp;
    private com.toedter.calendar.JDateChooser cumplea;
    private javax.swing.JLabel des;
    private javax.swing.JLabel due;
    private javax.swing.JButton guardarAdd;
    private javax.swing.JButton guardarUpda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JScrollPane jdescripcion;
    private javax.swing.JScrollPane jdescripcion2;
    private javax.swing.JComboBox<String> jdueno;
    private javax.swing.JComboBox<String> jdueno2;
    private javax.swing.JTextField jnombre;
    private javax.swing.JTextField jnombre2;
    private javax.swing.JComboBox<String> jsexo;
    private javax.swing.JComboBox<String> jsexo2;
    private javax.swing.JComboBox<String> jtipo;
    private javax.swing.JComboBox<String> jtipo2;
    private javax.swing.JLabel mes;
    private javax.swing.JLabel nom;
    private javax.swing.JPanel nombr;
    private javax.swing.JLabel raz;
    private javax.swing.JLabel sex;
    private javax.swing.JDialog update;
    private javax.swing.JDialog view;
    // End of variables declaration//GEN-END:variables
}
