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
import pojo.Expediente;

/**
 *
 * @author blanc
 */
public class ExpedienteDao {
    public int insertar(Expediente pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_expediente(?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, pojo.getPaciente_idpaciente());
            st.setString(2, pojo.getChip());
            st.setString(3, pojo.getPeso());
            st.setString(4, pojo.getAltura());
            st.setString(5, pojo.getVacunas());
            st.setString(6, pojo.getMedicamentos());
            st.setString(7, pojo.getFrec_cardiaca());
            st.setString(8, pojo.getFrec_respitatoria());
            st.setString(9, pojo.getPulso());
            st.setString(10, pojo.getObervaciones());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Expediente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
   public boolean actualizar_expediente(Expediente POJO) {
        
        Connection con = null;
        PreparedStatement st = null;
        Expediente expediente = POJO;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_expediente(?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, expediente.getIdexpediente());
            st.setInt(2, expediente.getPaciente_idpaciente());
            st.setString(3, expediente.getChip());
            st.setString(4, expediente.getPeso());
            st.setString(5, expediente.getAltura());
            st.setString(6, expediente.getVacunas());
            st.setString(7, expediente.getMedicamentos());
            st.setString(8, expediente.getFrec_cardiaca());
            st.setString(9, expediente.getFrec_respitatoria());
            st.setString(10, expediente.getPulso());
            st.setString(11, expediente.getObervaciones());

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Expediente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
   public boolean delete_expediente(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_expediente(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Expediente: " + e);
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
        String encabezados[] = {"Id", "Paciente"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select ex.idexpediente, p.nombre from expediente ex, paciente p where ex.paciente_idpaciente=p.idpaciente;");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[2];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getString(2).toUpperCase();

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Expediente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Expediente selectedExpediente(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Expediente pojo = new Expediente();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from expediente where idexpediente=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Expediente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Expediente inflaPOJO(ResultSet rs) {

        Expediente POJO = new Expediente();
        try {
            POJO.setIdexpediente(rs.getInt("idExpediente"));
            POJO.setPaciente_idpaciente(rs.getInt("paciente_idpaciente"));
            POJO.setChip(rs.getString("chip"));
            POJO.setPeso(rs.getString("peso"));
            POJO.setAltura(rs.getString("altura"));
            POJO.setVacunas(rs.getString("vacunas"));
            POJO.setMedicamentos(rs.getString("medicamentos"));
            POJO.setFrec_cardiaca(rs.getString("frec_cardiaca"));
            POJO.setFrec_respitatoria(rs.getString("frec_respiratoria"));
            POJO.setPulso(rs.getString("pulso"));
            POJO.setObervaciones(rs.getString("observaciones"));
            //POJO.setImage(rs.getString("phone"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Expediente: " + ex);
        }
        return POJO;
    }
}
