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
public class Venta {
    private int idventa;
    private double total;
    private int empleado_idempleado;
    private int cliente_idcliente;
    private double cambio;
    private double pago;

    public Venta() {
    }

    public Venta(int idventa, double total, int empleado_idempleado, int cliente_idcliente, double cambio, double pago) {
        this.idventa = idventa;
        this.total = total;
        this.empleado_idempleado = empleado_idempleado;
        this.cliente_idcliente = cliente_idcliente;
        this.cambio = cambio;
        this.pago = pago;
    }
public Venta(double total, int empleado_idempleado, int cliente_idcliente, double cambio, double pago) {

        this.total = total;
        this.empleado_idempleado = empleado_idempleado;
        this.cliente_idcliente = cliente_idcliente;
        this.cambio = cambio;
        this.pago = pago;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEmpleado_idempleado() {
        return empleado_idempleado;
    }

    public void setEmpleado_idempleado(int empleado_idempleado) {
        this.empleado_idempleado = empleado_idempleado;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }
  
    
    
}
