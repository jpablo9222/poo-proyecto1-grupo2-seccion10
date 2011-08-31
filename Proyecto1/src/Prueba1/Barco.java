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
public class Barco {
    private String naviera, pais_naviera, nombre, capitan;
    private float capacidad_maxima;
    private int capacidad_contenedores;
    private ArrayList<Contenedor> contenedores;
    private Ruta ruta;
    
    public Barco(String name, String nav, String pnav, float cap, int capCont,
           Ruta path, String captain)
    {
        naviera = nav; pais_naviera = pnav; capacidad_maxima = cap;
        capacidad_contenedores = capCont; ruta = path; nombre = name;
        capitan = captain;
    }   
       
    public String getNombre()
    {
        return nombre;
    }
    
    public String getCapitan()
    {
        return capitan;
    }
               
    public String getNaviera()
    {
        return naviera;
    }
    
    public String getPaisNaviera()
    {
        return pais_naviera;
    }
    
    public float getCapacidad()
    {
        return capacidad_maxima;
    }        
    
    public int getCapContendores()
    {
        return capacidad_contenedores;
    }
    
    public Ruta getRuta()
    {
        return ruta;
    }        
    
    public ArrayList<Contenedor> getContenedores()
    {
        return contenedores;
    }
}
