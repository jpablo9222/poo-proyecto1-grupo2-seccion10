/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba1;

/**
 *
 * @author Juan Pablo
 */
public class Puerto {
    private String nombre, pais, coordenada;
    
    public Puerto(String name, String country, String coor)
    {
        nombre = name; pais = country; coordenada = coor;
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
}
