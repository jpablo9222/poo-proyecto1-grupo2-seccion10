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
    private float capacidad_maxima, cargaActual;
    private ArrayList<Carga> cargas;
    private int codContenedor = 2011001;
    
    public Contenedor(float capacidad)
    {
        codContenedor += 1; capacidad_maxima = capacidad; cargaActual = 0;
    }
    
    public float getCapacidad()
    {
        return capacidad_maxima;
    } 
    
    public int getCodContendor()
    {
        return codContenedor;
    }
    
    public float getCargaActual()
    {
        return cargaActual;
    }
    
    public void setCargaActual(float x)
    {
        cargaActual = x;
    }
    
    public ArrayList<Carga> getCarga()
    {
        return cargas;
    }
}
