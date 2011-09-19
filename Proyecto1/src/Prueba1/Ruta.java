package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.util.ArrayList;
import java.util.Calendar;
public class Ruta {
    private int codRuta;
    private ArrayList<Puerto> puertos;
    private ArrayList<Calendar> fecha_arribo;
    
   /**
    * Método Constructor.
    * Crea la Ruta a Seguir de Un Barco.
    * @param cod Código de la Ruta.
    */
    public Ruta(int cod)
    {
        codRuta = cod; puertos = new ArrayList<Puerto>(); 
        fecha_arribo = new ArrayList<Calendar>();
    }
    
   /**
    * Método que Devuelve la Lista con los Puertos a Visitar.
    * @return puertos Lista con los Puertos a Visitar.
    */
    public ArrayList<Puerto> getPuerto()
    {
        return puertos;
    }
    
   /**
    * Método que Devuelve la Lista con las Fechas Programadas de Arribo a los Puertos a Visitar.
    * @return fecha_arribo Lista con las Fechas Programadas de Arribo a los Puertos a Visitar.
    */
    public ArrayList<Calendar> getFechaA()
    {
        return fecha_arribo;
    }
    
   /**
    * Método que Devuelve el Código de la Ruta.
    * @return codRuta Código de la Ruta.
    */
    public int getCodRuta()
    {
        return codRuta;
    }
}
