package Prueba1;

/**
 *
 * @author Juan Pablo
 */
public class Carga {
    private int codCarga;
    private String dueño, descripcion;
    private float peso;
    private Puerto origen, destino;
    
   /**
    * Método Constructor.   
    * Crea una Carga junto a la Información de la Programación de Envio.
    * @param cod Codigo de la Carga.
    * @param owner Empresa Dueña de la Carga.
    * @param d Puerto de Destino de la Carga.
    * @param weight Peso de la Carga.
    * @param o Puerto de Origen de la Carga.
    * @param des Descripción de la Carga.
    */
    public Carga(int cod, String owner, String des, float weight, Puerto o, Puerto d)
    {
        codCarga = cod; dueño = owner; descripcion = des; origen = o; destino = d;
    }
    
   /**
    * Método que Devuelve el Codigo de la Carga.
    * @return codCarga Código de la Carga.
    */
    public int getCodCarga()
    {
        return codCarga;
    }
    
   /**
    * Método que Devuelve la Empresa Dueña de la Carga.
    * @return dueño Empresa Dueña de la Carga.
    */
    public String getDueño()
    {
        return dueño;
    }
    
   /**
    * Método que Devuelve la Descripción de la Carga.
    * @return descripcion Descripción de la Carga.
    */
    public String getDescripcion()
    {
        return descripcion;
    }
    
   /**
    * Método que Devuelve el Peso de la Carga.
    * @return peso Peso de la Carga.
    */
    public float getPeso()
    {
        return peso;
    }
    
   /**
    * Método que Agrega Más Peso a la Carga.
    * @param x Peso a Agregar a la Carga.
    */
    public void addPeso(float x)
    {
        peso+=x;
    }
    
   /**
    * Método que Asigna El Peso a la Carga.
    * @param x Peso de la Carga.
    */
    public void setPeso(float x)
    {
        peso = x;
    }
    
   /**
    * Método que Devuelve el Puerto de Origen de la Carga.
    * @return origen Puerto de Origen de la Carga.
    */
    public Puerto getOrigen()
    {
        return origen;
    }
    
   /**
    * Método que Devuelve el Puerto de Destino de la Carga.
    * @return destino Puerto de Destino de la Carga.
    */
    public Puerto getDestino()
    {
        return destino;
    }
}
