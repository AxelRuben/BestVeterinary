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
import pojo.Cliente;

public class UsuarioDao {
    public int insertar(Cliente pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_cliente(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, sexoD(pojo));
            st.setInt(3, pojo.getEdad());
            st.setString(4, pojo.getContacto());
            st.setString(5, pojo.getCorreo());
            st.setString(6, pojo.getDireccion());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Dueño " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
    public String sexoD(Cliente pojo) {
        String sexA = pojo.getSexo();
        String sex = "";
        if (sexA.equalsIgnoreCase("Femenino")) {
            sex = "F";
        } else if (sexA.equalsIgnoreCase("Masculino")) {
            sex = "M";
        }
        return sex;
    }
    
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Name", "Phone"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from friend");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Cliente pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdcliente();
                ob[1] = pojo.getNombre();
                ob[2] = pojo.getContacto();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Dueño " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Cliente selectedFriend(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Cliente pojo = new Cliente();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_friend(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Dueno " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Cliente inflaPOJO(ResultSet rs) {

        Cliente POJO = new Cliente();
        try {
            POJO.setIdcliente(rs.getInt("idcliente"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setSexo(rs.getString("sexo"));
            POJO.setEdad(rs.getInt("edad"));
            POJO.setContacto(rs.getString("contacto"));
            POJO.setCorreo(rs.getString("correo"));
            POJO.setDireccion(rs.getString("direccion"));
            //POJO.setImage(rs.getString("phone"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
