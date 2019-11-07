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
public class Servicio {
    private int servicio_idservicio;
    private String nombre;
    private String descripcion;

    public Servicio() {
    }

    public Servicio(int servicio_idservicio, String nombre, String descripcion) {
        this.servicio_idservicio = servicio_idservicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Servicio(String nombre, String descripcion) {
      
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getServicio_idservicio() {
        return servicio_idservicio;
    }

    public void setServicio_idservicio(int servicio_idservicio) {
        this.servicio_idservicio = servicio_idservicio;
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
    
}
