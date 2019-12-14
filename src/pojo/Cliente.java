/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Date;


public class Cliente {
    private int idcliente;
    private String nombre;
    private String sexo;
    private Date cumpleani;
    private String contacto;
    private String correo;
    private String direccion;
    private boolean activo;

    public Cliente(int idcliente, String nombre, String sexo, Date cumpleani, String contacto, String correo, String direccion, boolean activo) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.sexo = sexo;
        this.cumpleani = cumpleani;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
        this.activo = activo;
    }
    
    public Cliente(String nombre, String sexo, Date cumpleani, String contacto, String correo, String direccion, boolean activo) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.cumpleani = cumpleani;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
        this.activo = activo;
    }
    
    public Cliente(String nombre, String sexo, Date cumpleani, String contacto, String correo, String direccion) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.cumpleani = cumpleani;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Cliente() {
    }

   
    @Override
    public String toString() {
        return getNombre();
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getCumpleani() {
        return cumpleani;
    }

    public void setCumpleani(Date cumpleani) {
        this.cumpleani = cumpleani;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
