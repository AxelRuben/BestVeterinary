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
import pojo.TipoAnimal;

/**
 *
 * @author blanc
 */
public class TipoAnimalDao {
    public int insertar(TipoAnimal pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_animal(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getTipo());
            st.setString(2, pojo.getImagen());
            st.setString(3, pojo.getRaza());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertd "+id);
            }
        } catch (Exception e) {
            System.out.println("Error while inserting a Tipo Animal " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
    public boolean actualizar_TipoAnimal(TipoAnimal POJO) {
        Connection con = null;
        PreparedStatement st = null;
        TipoAnimal tipoAnimal = POJO;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_animal(?,?,?,?)");
            
            st.setInt(1, tipoAnimal.getIdtipoAnimal());
            st.setString(2, tipoAnimal.getTipo());
            st.setString(3, tipoAnimal.getImagen());
            st.setString(4, tipoAnimal.getRaza());
            id = st.executeUpdate();

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Raza " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public boolean delete_raza(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_raza(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Raza: " + e);
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
        String encabezados[] = {"Id", "Tipo", "Raza"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from tipoAnimal");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                TipoAnimal pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdtipoAnimal();
                ob[1] = pojo.getTipo().toUpperCase();
                ob[2] = pojo.getRaza().toUpperCase();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Tipo Animal " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public TipoAnimal selectedAnimal(int id) {
        Connection con = null;
        PreparedStatement st = null;
         TipoAnimal pojo = new TipoAnimal();;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_animal(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Tipo Animal " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from tipoanimal");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione la Raza");
            while (rs.next()) {
                TipoAnimal pojo = inflaPOJO(rs);
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
    
    private static TipoAnimal inflaPOJO(ResultSet rs) {

        TipoAnimal POJO = new TipoAnimal();
        try {
            POJO.setIdtipoAnimal(rs.getInt("idtipoAnimal"));
            POJO.setTipo(rs.getString("tipo"));
            POJO.setRaza(rs.getString("raza"));
            POJO.setImagen(rs.getString("imagen"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Tipo Animal: " + ex);
        }
        return POJO;
    }
}
