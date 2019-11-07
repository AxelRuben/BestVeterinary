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
public class Venta_has_Miscelaneas {
    private int venta_idventa;
    private int articulo_idarticulo;

    public Venta_has_Miscelaneas(int venta_idventa, int articulo_idarticulo) {
        this.venta_idventa = venta_idventa;
        this.articulo_idarticulo = articulo_idarticulo;
    }
    

    public Venta_has_Miscelaneas() {
    }

    public int getVenta_idventa() {
        return venta_idventa;
    }

    public void setVenta_idventa(int venta_idventa) {
        this.venta_idventa = venta_idventa;
    }

    public int getArticulo_idarticulo() {
        return articulo_idarticulo;
    }

    public void setArticulo_idarticulo(int articulo_idarticulo) {
        this.articulo_idarticulo = articulo_idarticulo;
    }
    
}
