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
import pojo.Miscelaneas;

/**
 *
 * @author blanc
 */
public class MiscelaneasDao {
    public int insertar(Miscelaneas pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        String tipo = pojo.getTipo();
        try {
            con = Conexion.getConnection();
            if (tipo.equals("Medicamento")) {
            st = con.prepareStatement("insert into miscelaneas(nombre, costo, proveedor_idproveedor, stock, tipo, caducidadMOA) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setDouble(2, pojo.getCosto());
            st.setInt(3, pojo.getProveedor_idproveedor());
            st.setInt(4, pojo.getStock());
            st.setString(5, tipo);
            st.setDate(6, pojo.getCaducidadMOA());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
            } else if (tipo.equals("Articulos")) {
                    st = con.prepareStatement("insert into miscelaneas(nombre, costo, proveedor_idproveedor, stock, tipo, caducidadMOA) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setDouble(2, pojo.getCosto());
            st.setInt(3, pojo.getProveedor_idproveedor());
            st.setInt(4, pojo.getStock());
            st.setString(5, tipo);
            st.setDate(6, pojo.getCaducidadMOA());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
                } else if (tipo.equals("Servicios")) {
                    st = con.prepareStatement("insert into miscelaneas(nombre, costo, tipo, descripcionS) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setDouble(2, pojo.getCosto());
            st.setString(3, tipo);
            st.setString(4, pojo.getDescripcionS());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
                } else {
                System.out.println("Tipo de Miscelaneas incorrecto");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Dueño " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
    public boolean actualizar_miscelaneas(Miscelaneas POJO) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        Miscelaneas mis = POJO;
        String tipo = mis.getTipo();
        
            con = Conexion.getConnection();
            if (tipo.equals("Medicamento")) {
                try{
            st = con.prepareStatement("UPDATE miscelaneas SET nombre=?, costo=?, proveedor_idproveedor=?, stock=?, tipo=?, caducidadMOA=? WHERE idmiscelaneas=?", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, mis.getNombre());
            st.setDouble(2, mis.getCosto());
            st.setInt(3, mis.getProveedor_idproveedor());
            st.setInt(4, mis.getStock());
            st.setString(5, tipo);
            st.setDate(6, mis.getCaducidadMOA());
            st.setInt(7, mis.getIdmiscelaneas());
            
             int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
                }catch (Exception e) {
            System.out.println("Error al actualizar Expediente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        return true;
            }
            } else if (tipo.equals("Articulos")) {
                try{
                    st = con.prepareStatement("UPDATE miscelaneas SET nombre=?, costo=?, proveedor_idproveedor=?, stock=?, tipo=?, caducidadMOA=? WHERE idmiscelaneas=?", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, mis.getNombre());
            st.setDouble(2, mis.getCosto());
            st.setInt(3, mis.getProveedor_idproveedor());
            st.setInt(4, mis.getStock());
            st.setString(5, tipo);
            st.setDate(6, mis.getCaducidadMOA());
            st.setInt(7, mis.getIdmiscelaneas());
            
             int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
                }catch (Exception e) {
            System.out.println("Error al actualizar Expediente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        return true;
            }
                } else if (tipo.equals("Servicios")) {
                    try{
                    st = con.prepareStatement("UPDATE miscelaneas SET nombre=?, costo=?, proveedor_idproveedor=?, stock=?, tipo=?, descripcionS=? WHERE idmiscelaneas=?", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, mis.getNombre());
            st.setDouble(2, mis.getCosto());
            st.setInt(3, mis.getProveedor_idproveedor());
            st.setInt(4, mis.getStock());
            st.setString(5, tipo);
            st.setString(6, mis.getDescripcionS());
            st.setInt(7, mis.getIdmiscelaneas());
            
             int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
                }catch (Exception e) {
            System.out.println("Error al actualizar Miscelaneas " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        return true;
            }
                } else {
                System.out.println("Tipo de Miscelaneas incorrecto");
            }
        return true;
    }
    
    public boolean delete_miscelanea(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_miscelanea(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Producto: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
 
    
//    public int insertar(Miscelaneas pojo) throws SQLException {
//        Connection con = null;
//        PreparedStatement st = null;
//        int id = 0;
//        try {
//            con = Conexion.getConnection();
//            st = con.prepareStatement("call insert_cliente(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
//            st.setString(1, pojo.getNombre());
//            st.setString(2, sexoD(pojo));
//            st.setInt(3, pojo.getEdad());
//            st.setString(4, pojo.getContacto());
//            st.setString(5, pojo.getCorreo());
//            st.setString(6, pojo.getDireccion());
//            
//            id = st.executeUpdate();
//            ResultSet rs = st.getGeneratedKeys();
//            if (rs.next()) {
//                id = rs.getInt(1);
//                System.out.println("ID insertada "+id);
//            }
//        } catch (Exception e) {
//            System.out.println("Error al insertar Dueño " + e);
//
//        } finally {
//            Conexion.close(con);
//            Conexion.close(st);
//        }
//        return id;
//    }
    
    public DefaultTableModel cargarModeloIn() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre","Tipo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from miscelaneas");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Miscelaneas pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdmiscelaneas();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getTipo();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Miscelaneas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public DefaultTableModel cargarModelo(String tipo) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Tipo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from miscelaneas where tipo=?");
            st.setString(1, tipo);
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Miscelaneas pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdmiscelaneas();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getTipo();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Miscelaneas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public DefaultTableModel cargarModeloV() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Tipo", "Costo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from miscelaneas");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Miscelaneas pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdmiscelaneas();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getTipo();
                ob[3] = pojo.getCosto();
                

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Miscelaneas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Miscelaneas selectedMiscelaneas(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Miscelaneas pojo = new Miscelaneas();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from miscelaneas where idMiscelaneas=?");
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
    private static Miscelaneas inflaPOJO(ResultSet rs) {

        Miscelaneas POJO = new Miscelaneas();
        try {
            POJO.setIdmiscelaneas(rs.getInt("idmiscelaneas"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setCosto(rs.getInt("costo"));
            POJO.setProveedor_idproveedor(rs.getInt("proveedor_idproveedor"));
            POJO.setStock(rs.getInt("stock"));
            POJO.setImagen(rs.getString("imagen"));
            POJO.setTipo(rs.getString("tipo"));
            POJO.setCaducidadMOA(rs.getDate("caducidadMOA"));
            POJO.setDescripcionS(rs.getString("descripcionS"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
