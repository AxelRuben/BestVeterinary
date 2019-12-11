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
public class Expediente {
   private int idexpediente;
   private int paciente_idpaciente;
   private String chip;
   private String peso;
   private String altura;
   private String vacunas;
   private String medicamentos;
   private String frec_cardiaca;
   private String frec_respitatoria;
   private String pulso;
   private String obervaciones;

    public Expediente() {
    }

   
   
    public Expediente(int idexpediente, int paciente_idpaciente, String chip, String peso, String altura, String vacunas, String medicamentos, String frec_cardiaca, String frec_respitatoria, String pulso, String obervaciones) {
        this.idexpediente = idexpediente;
        this.paciente_idpaciente = paciente_idpaciente;
        this.chip = chip;
        this.peso = peso;
        this.altura = altura;
        this.vacunas = vacunas;
        this.medicamentos = medicamentos;
        this.frec_cardiaca = frec_cardiaca;
        this.frec_respitatoria = frec_respitatoria;
        this.pulso = pulso;
        this.obervaciones = obervaciones;
    }

    public Expediente(int paciente_idpaciente, String chip, String peso, String altura, String vacunas, String medicamentos, String frec_cardiaca, String frec_respitatoria, String pulso, String obervaciones) {
        this.paciente_idpaciente = paciente_idpaciente;
        this.chip = chip;
        this.peso = peso;
        this.altura = altura;
        this.vacunas = vacunas;
        this.medicamentos = medicamentos;
        this.frec_cardiaca = frec_cardiaca;
        this.frec_respitatoria = frec_respitatoria;
        this.pulso = pulso;
        this.obervaciones = obervaciones;
    }

    public int getIdexpediente() {
        return idexpediente;
    }

    public void setIdexpediente(int idexpediente) {
        this.idexpediente = idexpediente;
    }

    public int getPaciente_idpaciente() {
        return paciente_idpaciente;
    }

    public void setPaciente_idpaciente(int paciente_idpaciente) {
        this.paciente_idpaciente = paciente_idpaciente;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getVacunas() {
        return vacunas;
    }

    public void setVacunas(String vacunas) {
        this.vacunas = vacunas;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getFrec_cardiaca() {
        return frec_cardiaca;
    }

    public void setFrec_cardiaca(String frec_cardiaca) {
        this.frec_cardiaca = frec_cardiaca;
    }

    public String getFrec_respitatoria() {
        return frec_respitatoria;
    }

    public void setFrec_respitatoria(String frec_respitatoria) {
        this.frec_respitatoria = frec_respitatoria;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getObervaciones() {
        return obervaciones;
    }

    public void setObervaciones(String obervaciones) {
        this.obervaciones = obervaciones;
    }
    
   @Override
    public String toString() {
        return getObervaciones();
    }
}
