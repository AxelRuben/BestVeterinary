/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;


public class Cliente {
    private int idcliente;
    private String nombre;
    private String sexo;
    private int edad;
    private String contacto;
    private String correo;
    private String direccion;

    public Cliente(int idcliente, String nombre, String sexo, int edad, String contacto, String correo, String direccion) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Cliente(String nombre, String sexo, int edad, String contacto, String correo, String direccion) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Cliente() {
     
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    @Override
    public String toString() {
        return getNombre();
    }
    
}
