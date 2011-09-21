package Prueba1;

/**
 *
 * @author Juan Pablo
 */
public class Puerto {
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
    * MÃ©todo que Devuelve la Coordenada de UbicaciÃ³n del Puerto.
    * @return coordenada Coordenada de UbicaciÃ³n del Puerto.
    */
    public String getCoordenadaPuerto()
    {
        String coordenada="Latitud: "+ coordenadaNS+NS + "\t Longitud: "+coordenadaEO+EO;
        return coordenada;
    }
    
   /**
    * MÃ©todo que Copia la InformaciÃ³n de Otro Puerto.
    * @param puertito Puerto a Copiar.
    */
    public void copy(Puerto puertito)
    {
        nombre = puertito.nombre; pais = puertito.pais; 
        coordenadaNS = puertito.coordenadaNS; NS = puertito.NS;
        coordenadaEO = puertito.coordenadaEO; EO = puertito.EO;
    }
}
