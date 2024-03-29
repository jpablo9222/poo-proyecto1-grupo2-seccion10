package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.io.Serializable;
public class Puerto implements Serializable{
    private String nombre, pais, EO,NS;
    private float coordenadaNS, coordenadaEO;
    
   /**
    * Método Constructor.   
    * Crea un Puertoo con la información proporcionada.
    * @param name Nombre del Puerto.
    * @param country Pais de Donde se Ubica el Puerto.
    * @param coorNS Coordenadas en la Latitud.
    * @param coorEO Coordenada en la Longitud.
    * @param EOs Orientación en la Longitud.
    * @param NSs Orientación en la Latitud.
    */
    public Puerto(String name, String country, float coorNS, float coorEO, String EOs, String NSs)
    {
        nombre = name; pais = country; coordenadaNS=coorNS; coordenadaEO=coorEO;EO=EOs; NS=NSs; 
    }
    
   /**
    * Método que Devuelve el Nombre del Puerto.
    * @return nombre Nombre del Puerto.
    */
    public String getNombrePuerto()
    {
        return nombre;
    }
    
   /**
    * Método que Devuelve el Pais Donde se Ubica el Puerto.
    * @return pais Pais Donde se Ubica el Puerto.
    */
    public String getPaisPuerto()
    {
        return pais;
    }
    
   /**
    * Método que Devuelve la Coordenada de Ubicación del Puerto.
    * @return coordenada Coordenada de Ubicación del Puerto.
    */
    public String getCoordenadaPuerto()
    {
        String coordenada="Latitud: "+ coordenadaNS+NS + "\t Longitud: "+coordenadaEO+EO;
        return coordenada;
    }
    
   /**
    * Método que Copia la Información de Otro Puerto.
    * @param puertito Puerto a Copiar.
    */
    public void copy(Puerto puertito)
    {
        nombre = puertito.nombre; pais = puertito.pais; 
        coordenadaNS = puertito.coordenadaNS; NS = puertito.NS;
        coordenadaEO = puertito.coordenadaEO; EO = puertito.EO;
    }
  
    @Override
   /**
    * Método que Devuelve el Mensaje con la información de un Puerto.
    * @return info Mensaje con la Información de los Atributos de Un Puerto.
    */
    public String toString(){
        String info= "El Puerto "+nombre+ " se Ubica en el Pais: "+pais+" con las coordenadas: "+coordenadaNS+NS+ " y "+ coordenadaEO+EO;
        return info;
    }
}
