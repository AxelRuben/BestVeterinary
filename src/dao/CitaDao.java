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
import javax.swing.table.DefaultTableModel;
import pojo.Cita;

/**
 *
 * @author blanc
 */
public class CitaDao {
    public int insertar(Cita pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_cita(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDate(1, pojo.getFecha_hora());
            st.setString(2, pojo.getAsunto());
            st.setInt(3, pojo.getCliente_idcliente());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Cita " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    public boolean actualizar_cita(Cita POJO) {
        Connection con = null;
        PreparedStatement st = null;
        Cita cita = POJO;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_cita(?,?,?,?)");
            
            st.setInt(1, cita.getIdcita());
            st.setDate(2, cita.getFecha_hora());
            st.setString(3, cita.getAsunto());
            st.setInt(4, cita.getCliente_idcliente());
            id = st.executeUpdate();

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Cita " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public boolean delete_cita(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_cita(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Cita: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Fecha", "Asunto", "Cliente"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select ci.idcitas, ci.ultima_actualizacion, ci.asunto, c.nombre from cita ci, cliente c where ci.cliente_idcliente=c.idcliente;");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getDate(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Cita " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Cita selectedCita(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Cita pojo = new Cita();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_cita(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Cita " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Cita inflaPOJO(ResultSet rs) {

        Cita POJO = new Cita();
        try {
            POJO.setIdcita(rs.getInt("idcitas"));
            POJO.setFecha_hora(rs.getDate("fecha_hora"));
            POJO.setAsunto(rs.getString("asunto"));
            POJO.setCliente_idcliente(rs.getInt("cliente_idcliente"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
