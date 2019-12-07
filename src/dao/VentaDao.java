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
import pojo.Venta;

/**
 *
 * @author blanc
 */
public class VentaDao {
    public int insertar(Venta pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("INSERT INTO venta(total, empleado_idempleado, cliente_idcliente) VALUES (?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, pojo.getTotal());
            st.setInt(2, pojo.getEmpleado_idempleado());
            st.setInt(3, pojo.getCliente_idcliente());
            
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
    
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Total", "Empleado","Cliente"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from friend");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Venta pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdventa();
                ob[1] = pojo.getTotal();
                ob[2] = pojo.getEmpleado_idempleado();
                ob[3] = pojo.getCliente_idcliente();

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
     public Venta selectedFriend(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Venta pojo = new Venta();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from venta where id=?");
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
    private static Venta inflaPOJO(ResultSet rs) {

        Venta POJO = new Venta();
        try {
            POJO.setIdventa(rs.getInt("idventa"));
            POJO.setTotal(rs.getDouble("total"));
            POJO.setEmpleado_idempleado(rs.getInt("empleado_idempleado"));
            POJO.setCliente_idcliente(rs.getInt("cliente_idcliente"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
