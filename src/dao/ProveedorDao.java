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
import pojo.Proveedor;
import pojo.TipoAnimal;

/**
 *
 * @author blanc
 */
public class ProveedorDao {
    public int insertar(Proveedor pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_proveedor(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getContacto());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertd "+id);
            }
        } catch (Exception e) {
            System.out.println("Error while inserting a Proveedor " + e);

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
            st = con.prepareStatement("select * from proveedor");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su Proveedor");
            while (rs.next()) {
                Proveedor pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo Proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public boolean actualizar(Proveedor POJO) {
        Connection con = null;
        PreparedStatement st = null;
        Proveedor proveedor = POJO;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_proveedor(?,?,?)");
            
            st.setInt(1, proveedor.getIdproveedor());
            st.setString(2, proveedor.getNombre());
            st.setString(3, proveedor.getContacto());
            id = st.executeUpdate();

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Proveedor " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public boolean delete_proveedor(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_proveedor(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Proveedor: " + e);
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
        String encabezados[] = {"Id", "Nombre","Contacto"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from proveedor");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Proveedor pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdproveedor();
                ob[1] = pojo.getNombre();
                ob[2] = pojo.getContacto();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    private static Proveedor inflaPOJO(ResultSet rs) {

        Proveedor POJO = new Proveedor();
        try {
            POJO.setIdproveedor(rs.getInt("idproveedor"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setContacto(rs.getString("contacto"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Tipo Animal: " + ex);
        }
        return POJO;
    }
}

