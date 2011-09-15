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
public class Puerto {
    private String nombre, pais, coordenada;
    private ArrayList<Barco> barcosArribar;
    private ArrayList<Carga> cargasArribar;
    
    public Puerto(String name, String country, String coor)
    {
        nombre = name; pais = country; coordenada = coor;
        barcosArribar = new ArrayList<Barco>();
        cargasArribar = new ArrayList<Carga>();
    }
    
    public String getNombrePuerto()
    {
        return nombre;
    }
    
    public String getPaisPuerto()
    {
        return pais;
    }
    
    public String getCoordenadaPuerto()
    {
        return nombre;
    }
    
    public void copy(Puerto puertito)
    {
        nombre = puertito.nombre; pais = puertito.pais; 
        coordenada = puertito.coordenada;
    }
    
    public void addBarco(Barco barquito)
    {
        barcosArribar.add(barquito);
    }
    
    public ArrayList<Barco> getBarcos()
    {
        return barcosArribar;
    }
    
    public void addCarga(Carga charge)
    {
        cargasArribar.add(charge);
    }
    
    public ArrayList<Carga> getCargas()
    {
        return cargasArribar;
    }
}
