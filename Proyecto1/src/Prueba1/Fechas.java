/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Fechas {
    public static void main(String [] args){
        Calendar fecha = Calendar.getInstance();
        Calendar fecha1 = Calendar.getInstance();
        fecha.set(2011, 25, 8, 0, 0, 0);
        fecha.set(2011, 10, 1, 0, 0, 0);
        System.out.println(fecha.getTime());
        fecha.add(Calendar.DAY_OF_MONTH, 29);
        System.out.println(fecha.getTime());
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
	System.out.println(date_format.format(fecha.getTime()));
        System.out.println(date_format.format(fecha1.getTime()));
        
        if (fecha.after(fecha1))
            System.out.println("La fecha 1 es mayor");
    }
    
}
