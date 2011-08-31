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
import java.util.Calendar; //asi se usan fechas
// import java.text.SimpleDateFormat; Para darle formato a la fecha
public class Ruta {
    private int codRuta;
    private ArrayList<Puerto> puertos;
    private ArrayList<Calendar> fecha_arribo;
    
    public Ruta(int cod)
    {
        codRuta = cod; puertos = new ArrayList<Puerto>(); 
        fecha_arribo = new ArrayList<Calendar>();
    }
    
    public ArrayList<Puerto> getPuerto()
    {
        return puertos;
    }
    
    public ArrayList<Calendar> getFechaA()
    {
        return fecha_arribo;
    }
    
    public int getCodRuta()
    {
        return codRuta;
    }
}
