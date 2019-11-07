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
public class Empleado {
    private int idempleado;
    private String nombre;
    private String sexo;
    private String especialidad;
    private int edad;
    private String horario;

    public Empleado(int idempleado, String nombre, String sexo, String especialidad, int edad, String horario) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.sexo = sexo;
        this.especialidad = especialidad;
        this.edad = edad;
        this.horario = horario;
    }

    public Empleado(String nombre, String sexo, String especialidad, int edad, String horario) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.especialidad = especialidad;
        this.edad = edad;
        this.horario = horario;
    }

    public Empleado() {
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
    
}
