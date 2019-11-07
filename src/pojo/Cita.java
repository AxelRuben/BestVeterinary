/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Date;

/**
 *
 * @author blanc
 */
public class Cita {
    private int idcita;
    private Date fecha_hora;
    private String asunto;
    private int cliente_idcliente;

    public Cita() {
    }

    public Cita(int idcita, Date fecha_hora, String asunto, int cliente_idcliente) {
        this.idcita = idcita;
        this.fecha_hora = fecha_hora;
        this.asunto = asunto;
        this.cliente_idcliente = cliente_idcliente;
    }

    public Cita(Date fecha_hora, String asunto, int cliente_idcliente) {
        this.fecha_hora = fecha_hora;
        this.asunto = asunto;
        this.cliente_idcliente = cliente_idcliente;
    }
    
    

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }
    
    
}
