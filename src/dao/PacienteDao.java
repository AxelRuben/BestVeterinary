/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import pojo.Cliente;
import pojo.Expediente;
import pojo.Paciente;

/**
 *
 * @author blanc
 */
public class PacienteDao {
        String sex = "";
        ClienteDao clienteDao = new ClienteDao();
    public int insertar(Paciente pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_paciente(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getDescripcion());
            st.setString(3, sexoDM(pojo));
            st.setInt(4, pojo.getCliente_idcliente());
            st.setInt(5, pojo.getTipoAnimal_idtipoAnimal());
            st.setDate(6, pojo.getCumple());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertda "+id);
            }
        } catch (Exception e) {
            System.out.println("Error en el insertar friend " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
     
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from paciente");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su paciente");
            while (rs.next()) {
                Paciente pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo cliente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    public String sexoDM(Paciente pojo) {
        String sexA = pojo.getSexo();
        String sex = "";
        if (sexA.equalsIgnoreCase("Macho")) {
            sex = "M";
        } else if (sexA.equalsIgnoreCase("Hembra")) {
            sex = "H";
        }
        return sex;
    }
    
     public boolean actualizar_paciente(Paciente POJO) {
        
        Connection con = null;
        PreparedStatement st = null;
        Paciente paciente = POJO;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_paciente(?,?,?,?,?,?,?,?)");
            st.setInt(1, paciente.getIdpaciente());
            st.setString(2, paciente.getNombre());
            st.setString(3, paciente.getDescripcion());
            st.setString(4, sexoDM(POJO));
            st.setInt(5, paciente.getCliente_idcliente());
            st.setInt(6, paciente.getTipoAnimal_idtipoAnimal());
            st.setDate(7, paciente.getCumple());
            st.setBoolean(8, paciente.isActivo());

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Paciente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
     
     public boolean delete_paciente(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL desactive_paciente(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al dar de baja al Paciente: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
     
    public Paciente selectedPaciente(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Paciente pojo = new Paciente();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_paciente(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Paciente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    
    
    public DefaultTableModel cargarModeloA(boolean activo) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Dueño", "Tipo", "Estado"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select p.idpaciente, p.nombre, c.nombre, tp.tipo, p.activo from paciente p, cliente c, tipoanimal tp where c.idCliente=p.cliente_idcliente and tp.idtipoAnimal=p.tipoAnimal_idtipoAnimal and p.activo=?;");
            st.setBoolean(1, activo);
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[5];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getString(2).toUpperCase();
                ob[2] = rs.getString(3).toUpperCase();
                ob[3] = rs.getString(4).toUpperCase();
                if (rs.getBoolean(5)) {
                ob[4] = "Activo";
                }else{
                ob[4] = "Inactivo";
                }

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Paciente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Nombre", "Dueño", "Tipo", "Actividad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select p.idpaciente, p.nombre, c.nombre, tp.tipo, p.activo from paciente p, cliente c, tipoanimal tp where c.idCliente=p.cliente_idcliente and tp.idtipoAnimal=p.tipoAnimal_idtipoAnimal;");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[5];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getString(2).toUpperCase();
                ob[2] = rs.getString(3).toUpperCase();
                ob[3] = rs.getString(4).toUpperCase();
                if (rs.getBoolean(5)) {
                ob[4] = "Activo";
                }else{
                ob[4] = "Inactivo";
                }

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Paciente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
   
    private static Paciente inflaPOJO(ResultSet rs) {

        Paciente POJO = new Paciente();;
        try {
            POJO.setIdpaciente(rs.getInt("idpaciente"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setDescripcion(rs.getString("descripcion"));
            POJO.setSexo(rs.getString("sexo"));
            POJO.setCliente_idcliente(rs.getInt("cliente_idcliente"));
            POJO.setTipoAnimal_idtipoAnimal(rs.getInt("tipoAnimal_idtipoAnimal"));
            POJO.setCumple(rs.getDate("cumpleanios"));
            POJO.setActivo(rs.getBoolean("activo"));
            POJO.setAct(rs.getInt("activo"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Paciente: " + ex);
        }
        return POJO;
    }
}
