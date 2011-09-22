package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.io.Serializable;
import java.util.ArrayList;
public class Contenedor implements Serializable{
    private float capacidad_maxima, cargaActual;
    private ArrayList<Carga> cargas;
    private int codContenedor = 2011001;
    
   /**
    * Método Constructor.
    * Crea un Contenedor de un Barco.
    * @param capacidad Capacidad Máxima de Peso del Contenedor.
    */
    public Contenedor(float capacidad)
    {
        codContenedor += 1; capacidad_maxima = capacidad; cargaActual = 0;
        cargas = new ArrayList<Carga>();
    }
    
   /**
    * Método que Devuelve la Capacidad Máxima de Peso del Contenedor.
    * @return capacidad_maxima Capacidad Máxima de Peso del Contenedor.
    */
    public float getCapacidad()
    {
        return capacidad_maxima;
    } 
    
   /**
    * Método que Devuelve el Código del Contenedor.
    * @return codContenedor Código del Contenedor.
    */
    public int getCodContendor()
    {
        return codContenedor;
    }
    
   /**
    * Método que Devuelve el Peso Actual del Contenedor.
    * @return cargaActual Peso Actual del Contenedor.
    */
    public float getCargaActual()
    {
        return cargaActual;
    }
    
   /**
    * Método que Establece el Peso Actual del Contenedor.
    * @param x Peso Actual del Contenedor.
    */
    public void setCargaActual(float x)
    {
        cargaActual = x;
    }
    
   /**
    * Método que Devuelve la Lista de Cargas Contenidas en el Contenedor.
    * @return cargas Lista de Cargas Contenidas en el Contenedor.
    */
    public ArrayList<Carga> getCarga()
    {
        return cargas;
    }
}
