/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba1;

/**
 *
 * @author Juan Pablo
 */
public class Carga {
    private int codCarga;
    private String dueño, descripcion;
    private float peso;
    private Puerto origen, destino;
    
    public Carga(int cod, String owner, String des, float weight, Puerto o, Puerto d)
    {
        codCarga = cod; dueño = owner; descripcion = des; origen = o; destino = d;
    }
    
    public int getCodCarga()
    {
        return codCarga;
    }
    
    public String getDueño()
    {
        return dueño;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
    
    public float getPeso()
    {
        return peso;
    }
    
    public void addPeso(float x)
    {
        peso+=x;
    }
    
    public void setPeso(float x)
    {
        peso = x;
    }
    
    public Puerto getOrigen()
    {
        return origen;
    }
    
    public Puerto getDestino()
    {
        return destino;
    }
}
