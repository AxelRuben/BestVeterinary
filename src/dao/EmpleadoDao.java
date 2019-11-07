/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import pojo.Cliente;
import pojo.Empleado;

/**
 *
 * @author blanc
 */
public class EmpleadoDao {
    String sex;
    public int insertar(Empleado pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_empleado(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, sexoDM(pojo));
            st.setString(3, pojo.getEspecialidad());
            st.setInt(4, pojo.getEdad());
            st.setString(5, pojo.getHorario());
            
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Empleado " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
    public boolean actualizar_empleado(Empleado POJO) {
        
        Connection con = null;
        PreparedStatement st = null;
        Empleado empleado = POJO;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_empleado(?,?,?,?,?,?)");
            st.setInt(1, empleado.getIdempleado());
            st.setString(2, empleado.getNombre());
            st.setString(3, sexoDM(POJO));
            st.setString(4, empleado.getEspecialidad());
            st.setInt(5, empleado.getEdad());
            st.setString(6, empleado.getHorario());

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Cliente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public boolean delete_empleado(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_empleado(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Empleado: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    
    public String sexoDM(Empleado pojo) {
        String sexA = pojo.getSexo();
        String sex = "";
        if (sexA.equalsIgnoreCase("Masculino")) {
            sex = "M";
        } else if (sexA.equalsIgnoreCase("Femenino")) {
            sex = "F";
        }
        return sex;
    }
    
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Turno"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from empleado");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Empleado pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdempleado();
                ob[1] = pojo.getNombre();
                ob[2] = pojo.getHorario();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Empleado " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Empleado selectedEmpleado(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Empleado pojo = new Empleado();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_empleado(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Empleado " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Empleado inflaPOJO(ResultSet rs) {

        Empleado POJO = new Empleado();
        try {
            POJO.setIdempleado(rs.getInt("idempleado"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setSexo(rs.getString("sexo"));
            POJO.setEspecialidad(rs.getString("especialidad"));
            POJO.setEdad(rs.getInt("edad"));
            POJO.setHorario(rs.getString("horario"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Empleado: " + ex);
        }
        return POJO;
    }
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from empleado");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione al Empleado");
            while (rs.next()) {
                Empleado pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo de Empleado " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
}
