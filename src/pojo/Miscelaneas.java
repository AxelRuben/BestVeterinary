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
public class Miscelaneas {
    private int idmiscelaneas;
    private String nombre;
    private double costo;
    private int proveedor_idproveedor;
    private int stock;
    private String imagen;
    private String tipo;
    private Date caducidadMOA;
    private String descripcionS;

    public Miscelaneas(int idmiscelaneas, String nombre, double costo, int proveedor_idproveedor, int stock, String imagen, String tipo, Date caducidadMOA, String descripcionS) {
        this.idmiscelaneas = idmiscelaneas;
        this.nombre = nombre;
        this.costo = costo;
        this.proveedor_idproveedor = proveedor_idproveedor;
        this.stock = stock;
        this.imagen = imagen;
        this.tipo = tipo;
        this.caducidadMOA = caducidadMOA;
        this.descripcionS = descripcionS;
    }
    
    public Miscelaneas(String nombre, double costo, int proveedor_idproveedor, int stock, String imagen, String tipo, Date caducidadMOA, String descripcionS) {
        this.nombre = nombre;
        this.costo = costo;
        this.proveedor_idproveedor = proveedor_idproveedor;
        this.stock = stock;
        this.imagen = imagen;
        this.tipo = tipo;
        this.caducidadMOA = caducidadMOA;
        this.descripcionS = descripcionS;
    }
    public Miscelaneas(String nombre, double costo, String tipo, String descripcionS) {
        this.nombre = nombre;
        this.costo = costo;
        this.tipo = tipo;
        this.descripcionS = descripcionS;
    }
    public Miscelaneas(String nombre, double costo, int proveedor_idproveedor, int stock, String tipo, Date caducidadMOA) {
        this.nombre = nombre;
        this.costo = costo;
        this.proveedor_idproveedor = proveedor_idproveedor;
        this.stock = stock;
        this.tipo = tipo;
        this.caducidadMOA = caducidadMOA;
    }
    public Miscelaneas(String nombre, double costo, int proveedor_idproveedor, int stock, String tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.proveedor_idproveedor = proveedor_idproveedor;
        this.stock = stock;
        this.tipo = tipo;
    }
    public Miscelaneas(int idmiscelaneas, String nombre, double costo, String tipo, String descripcionS) {
        this.idmiscelaneas=idmiscelaneas;
        this.nombre = nombre;
        this.costo = costo;
        this.tipo = tipo;
        this.descripcionS = descripcionS;
    }
    public Miscelaneas(int idmiscelaneas,String nombre, double costo, int proveedor_idproveedor, int stock, String tipo, Date caducidadMOA) {
        this.idmiscelaneas=idmiscelaneas;
        this.nombre = nombre;
        this.costo = costo;
        this.proveedor_idproveedor = proveedor_idproveedor;
        this.stock = stock;
        this.tipo = tipo;
        this.caducidadMOA = caducidadMOA;
    }

    
    public Miscelaneas() {
    }

    public int getIdmiscelaneas() {
        return idmiscelaneas;
    }

    public void setIdmiscelaneas(int idmiscelaneas) {
        this.idmiscelaneas = idmiscelaneas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getProveedor_idproveedor() {
        return proveedor_idproveedor;
    }

    public void setProveedor_idproveedor(int proveedor_idproveedor) {
        this.proveedor_idproveedor = proveedor_idproveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getCaducidadMOA() {
        return caducidadMOA;
    }

    public void setCaducidadMOA(Date caducidadMOA) {
        this.caducidadMOA = caducidadMOA;
    }

    public String getDescripcionS() {
        return descripcionS;
    }

    public void setDescripcionS(String descripcionS) {
        this.descripcionS = descripcionS;
    }


    
    
    
}
