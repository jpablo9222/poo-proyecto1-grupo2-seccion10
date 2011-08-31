/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.util.ArrayList;
public class Contenedor {
    private float capacidad_maxima;
    private ArrayList<Carga> cargas;
    private int codContenedor;
    
    public Contenedor(int cod, float capacidad)
    {
        codContenedor = cod; capacidad_maxima = capacidad;
    }
    
    public float getCapacidad()
    {
        return capacidad_maxima;
    } 
    
    public int getCodContendor()
    {
        return codContenedor;
    }
    
    public ArrayList<Carga> getCarga()
    {
        return cargas;
    }
}
