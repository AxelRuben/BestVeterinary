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
public class TipoAnimal {
    private int idtipoAnimal;
    private String tipo;
    private String imagen;
    private String raza;

    public TipoAnimal(int idtipoAnimal, String tipo, String imagen, String raza) {
        this.idtipoAnimal = idtipoAnimal;
        this.tipo = tipo;
        this.imagen = imagen;
        this.raza = raza;
    }

    public TipoAnimal(String tipo, String imagen, String raza) {
        this.tipo = tipo;
        this.imagen = imagen;
        this.raza = raza;
    }

    public TipoAnimal() {
    
    }

    public int getIdtipoAnimal() {
        return idtipoAnimal;
    }

    public void setIdtipoAnimal(int idtipoAnimal) {
        this.idtipoAnimal = idtipoAnimal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return getRaza();
    }

    

   
    
}
