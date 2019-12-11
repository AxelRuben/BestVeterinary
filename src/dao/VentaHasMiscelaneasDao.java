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
import pojo.Venta_has_Miscelaneas;

/**
 *
 * @author blanc
 */
public class VentaHasMiscelaneasDao {
    public int insertar(Venta_has_Miscelaneas pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("INSERT INTO venta_has_miscelaneas(venta_idventa, miscelaneas_idmiscelaneas) VALUES (?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, pojo.getVenta_idventa());
            st.setInt(2, pojo.getArticulo_idarticulo());
            
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
    
    public DefaultTableModel cargarModelo(int id) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Costo","Tipo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select m.idmiscelaneas, m.nombre, m.costo, m.tipo from venta_has_miscelaneas vhm, miscelaneas m where m.idmiscelaneas=vhm.miscelaneas_idmiscelaneas and vhm.venta_idventa=?");
            st.setInt(1, id);
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getString(2).toUpperCase();
                ob[2] = rs.getDouble(3);
                ob[3] = rs.getString(4).toUpperCase();

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
    
//     public Venta_has_Miscelaneas selectedFriend(int id) {
//        Connection con = null;
//        PreparedStatement st = null;
//         Venta_has_Miscelaneas pojo = new Venta_has_Miscelaneas();
//        try {
//            con = Conexion.getConnection();
//            st = con.prepareStatement("select * from venta where id=?");
//            st.setInt(1, id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                pojo = inflaPOJO(rs);
//            }
//        } catch (Exception e) {
//            System.out.println("Error al consultar Dueno " + e);
//        } finally {
//            Conexion.close(con);
//            Conexion.close(st);
//        }
//        return pojo;
//    }
    
    private static Venta_has_Miscelaneas inflaPOJO(ResultSet rs) {

        Venta_has_Miscelaneas POJO = new Venta_has_Miscelaneas();
        try {
            POJO.setVenta_idventa(rs.getInt("venta_idventa"));
            POJO.setArticulo_idarticulo(rs.getInt("miscelaneas_idmiscelaneas"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
