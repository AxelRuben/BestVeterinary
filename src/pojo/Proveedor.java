/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author blanc
 */
public class Proveedor {
    private int idproveedor;
    private String nombre;
    private String contacto;

    public Proveedor(int idproveedor, String nombre, String contacto) {
        this.idproveedor = idproveedor;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public Proveedor(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public Proveedor() {
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
