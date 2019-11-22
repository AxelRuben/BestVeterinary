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
public class Paciente {
    private int idpaciente;
    private String nombre;
    private String descripcion;
    private String sexo;
    private int cliente_idcliente;
    private int tipoAnimal_idtipoAnimal;
    private Date cumple;
    private boolean activo;
    

    public Paciente() {
    }

    public Paciente(int idpaciente, String nombre, String descripcion, String sexo, int cliente_idcliente, int tipoAnimal_idtipoAnimal, Date cumple, boolean activo) {
        this.idpaciente = idpaciente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sexo = sexo;
        this.cliente_idcliente = cliente_idcliente;
        this.tipoAnimal_idtipoAnimal = tipoAnimal_idtipoAnimal;
        this.cumple = cumple;
        this.activo = activo;
    }

    public Paciente(String nombre, String descripcion, String sexo, int cliente_idcliente, int tipoAnimal_idtipoAnimal, Date cumple) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sexo = sexo;
        this.cliente_idcliente = cliente_idcliente;
        this.tipoAnimal_idtipoAnimal = tipoAnimal_idtipoAnimal;
        this.cumple = cumple;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }

    public int getTipoAnimal_idtipoAnimal() {
        return tipoAnimal_idtipoAnimal;
    }

    public void setTipoAnimal_idtipoAnimal(int tipoAnimal_idtipoAnimal) {
        this.tipoAnimal_idtipoAnimal = tipoAnimal_idtipoAnimal;
    }

    public Date getCumple() {
        return cumple;
    }

    public void setCumple(Date cumple) {
        this.cumple = cumple;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    

}
