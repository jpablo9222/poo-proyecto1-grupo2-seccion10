package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.util.ArrayList;
import java.io.Serializable;
public class Barco implements Serializable{
    private String naviera, pais_naviera, nombre, capitan;
    private float capacidad_maxima;
    private int capacidad_contenedores;
    private ArrayList<Contenedor> contenedores;
    private ArrayList<Carga> cargaProgramada;
    private Ruta ruta;
    
   /**
    * Método Constructor.   
    * Crea un Barco con la información proporcionada.
    * @param name Nombre del Barco.
    * @param nav Nombre del Navio Propietario.
    * @param cap Capacidad Máxima de Peso que Soporta el Barco.
    * @param capCont Capacidad Máxima de Contenedores del Barco.
    * @param pais_naviera Pais de Donde Proviene el Barco.
    * @param captain Nombre del Capitan Del Barco.
    * @param path Ruta del Barco.
    */
    public Barco(String name, String nav, String pnav, float cap, int capCont,
           Ruta path, String captain)
    {
        naviera = nav; pais_naviera = pnav; capacidad_maxima = cap;
        capacidad_contenedores = capCont; ruta = path; nombre = name;
        capitan = captain;
        contenedores = new ArrayList<Contenedor>();
        cargaProgramada = new ArrayList<Carga>();
    }   
    
   /**
    * Método que Devuelve el Nombre del Barco.
    * @return nombre Nombre del Barco.
    */
    public String getNombre()
    {
        return nombre;
    }

   /**
    * Método que Devuelve el Nombre del Cápitan del Barco.   
    * @return capitan Nombre del Cápitan del Barco.
    */
    public String getCapitan()
    {
        return capitan;
    }

   /**
    * Método que Devuelve el Nombre de la Naviera Propietaria del Barco.   
    * @return naviera Nombre de la Naviera Propietaria del Barco.
    */
    public String getNaviera()
    {
        return naviera;
    }
    
   /**
    * Método que Devuelve el Pais de la Naviera Propietaria del Barco.   
    * @return pais_naviera Pais de la Naviera Propietaria del Barco.
    */
    public String getPaisNaviera()
    {
        return pais_naviera;
    }
   
   /**
    * Método que Devuelve La Capacidad Máxima de Peso que Soporta el Barco.   
    * @return capacidad_maxima Capacidad Máxima de Peso que Soporta el Barco.
    */    
    public float getCapacidad()
    {
        return capacidad_maxima;
    }        
    
   /**
    * Método que Devuelve La Capacidad Máxima de Contenedores del Barco.
    * @return capacidad_contenedores Capacidad Máxima de Contenedores del Barco.
    */ 
    public int getCapContendores()
    {
        return capacidad_contenedores;
    }
    
   /**
    * Método que Devuelve La Ruta del Barco.
    * @return ruta Ruta del Barco.
    */ 
    public Ruta getRuta()
    {
        return ruta;
    }        
    
   /**
    * Método que Devuelve La Lista de Contenedores del Barco.
    * @return contenedores Lista de Contenedores del Barco.
    */
    public ArrayList<Contenedor> getContenedores()
    {
        return contenedores;
    }
    
   /**
    * Método que Devuelve La Lista de Cargas Programadas del Barco.
    * @return contenedores Lista de Cargas Programadas del Barco.
    */
    public ArrayList<Carga> getCargaProg()
    {
        return cargaProgramada;
    }
}
