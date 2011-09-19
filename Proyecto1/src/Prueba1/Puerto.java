package Prueba1;

/**
 *
 * @author Juan Pablo
 */
public class Puerto {
    private String nombre, pais, coordenada;
    
   /**
    * Método Constructor.   
    * Crea un Puertoo con la información proporcionada.
    * @param name Nombre del Puerto.
    * @param country Pais de Donde se Ubica el Puerto.
    * @param coor Coordenadas de Ubicación del Puerto.
    */
    public Puerto(String name, String country, String coor)
    {
        nombre = name; pais = country; coordenada = coor;
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
        return coordenada;
    }
    
   /**
    * Método que Copia la Información de Otro Puerto.
    * @param puertito Puerto a Copiar.
    */
    public void copy(Puerto puertito)
    {
        nombre = puertito.nombre; pais = puertito.pais; 
        coordenada = puertito.coordenada;
    }
}
