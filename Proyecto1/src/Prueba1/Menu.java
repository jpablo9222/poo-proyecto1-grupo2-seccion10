/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Menu {
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Puerto> puertosExistentes = new ArrayList<Puerto>();
    static ArrayList<Barco> barcosExistentes = new ArrayList<Barco>();
    static ArrayList<Contenedor> contenedoresExistentes = new ArrayList<Contenedor>();
    static ArrayList<Carga> cargasExistentes = new ArrayList<Carga>();
    
    static void IngresarPuerto(){
        String nombre, pais, coordenadas;
        System.out.println("Ingreso de Puertos");
        System.out.println("Nombre del Puerto: ");
        nombre = teclado.next();
        System.out.println("Pais del Puerto: ");
        pais = teclado.next();
        System.out.println("Coordenadas del Puerto: ");
        coordenadas = teclado.next();
        
        Puerto puerto = new Puerto (nombre, pais, coordenadas);
        puertosExistentes.add(puerto);
        System.out.println("Puerto Ingresado Exitosamente");
    }
    
    static void IngresarBarco(){
        String naviera, pnaviera, nombre, capitan;
        float capacidadMaxima;
        int capacidadCont;
        Ruta ruta;
        
        System.out.println("Ingreso de Barcos\n");
        System.out.println("Nombre del Barco: ");
        nombre = teclado.next();
        System.out.println("Capitan del Barco: ");
        capitan = teclado.next();
        System.out.println("Naviera Propietaria del Barco: ");
        naviera = teclado.next();
        System.out.println("Pais de la Naviera: ");
        pnaviera = teclado.next();
        System.out.println("Capacidad Maxima en toneladas Métricas del Barco: ");
        capacidadMaxima = teclado.nextFloat();
        System.out.println("Capacidad Maxima de Contenedores en el Barco: ");
        capacidadCont = teclado.nextInt();
        ruta = IngresarRuta();
        Barco barco = new Barco(nombre, naviera, pnaviera, capacidadMaxima,
                                capacidadCont, ruta, capitan);
        barcosExistentes.add(barco);
    }
    
    static float estimarPeso(Barco barco, Puerto origen){ 
        float x=0; int y, z;
        y = barco.getRuta().getPuerto().indexOf(origen);
        for (Contenedor cont:barco.getContenedores()){
            for (Carga producto:cont.getCarga()){
                z = barco.getRuta().getPuerto().indexOf(producto.getDestino());
                x+=producto.getPeso();
                if (barco.getRuta().getFechaA().get(z).before(barco.getRuta().getFechaA().get(y))){
                    x-=producto.getPeso();
                }
            }
        }
        return x;
    }
    
    static int comparaPuerto(String puerto){
        for (Puerto x:puertosExistentes){
            if (puerto.equals(x.getNombrePuerto())){
                return puertosExistentes.indexOf(x);
            }
        }
        return -1;
    }
    
    static void IngresarCarga(){
        int codCarga, i=0, y=0; String dueño, descripcion, puerto;
        float peso, pesoEstimado; boolean b1, b2;
        Puerto origen = new Puerto("","","");
        Puerto destino = new Puerto("","","");
        ArrayList<Barco> temp = new ArrayList<Barco>();
        Barco boat;
        
        System.out.println("\tIngreso de Carga");
        System.out.println("Codigo de la Carga: ");
        codCarga = teclado.nextInt();
        System.out.println("Dueño de la Carga: ");
        dueño = teclado.next();
        System.out.println("Descripcion de la Carga: ");
        descripcion = teclado.next();
        System.out.println("Peso de la Carga: ");
        peso = teclado.nextFloat();
        do{
            System.out.println("Puerto de Origen: ");
            puerto = teclado.next();
        } while (comparaPuerto(puerto)==-1);
        origen.copy(puertosExistentes.get(comparaPuerto(puerto)));
        do{
            System.out.println("Puerto de Destino: ");
            puerto = teclado.next();
        } while (comparaPuerto(puerto)==-1);
        destino.copy(puertosExistentes.get(comparaPuerto(puerto)));
        
        for (Barco barco:barcosExistentes){
            b1 = false; b2 = false;
            for (Puerto puertito:barco.getRuta().getPuerto()){
                if (puertito.getNombrePuerto().equals(origen.getNombrePuerto()))
                    b1 = true;
                if (puertito.getNombrePuerto().equals(destino.getNombrePuerto()))
                    b2 = true;
            }
            if (b1 && b2)
                temp.add(barco);
        }
        
        System.out.println("Barcos Recomendados:");
        for (Barco barquito:temp){
            i+=1;
            System.out.println(i+".) " + barquito.getNombre() + ", Espacio Disponible: " + (barquito.getCapacidad()-estimarPeso(barquito, origen)));
        }
        while (y<=0||y>temp.size()){
            System.out.println("Seleccione el Barco que mejor le Parezca: ");
            y = teclado.nextInt();
        }
        do{
            System.out.println("Peso de la Carga: ");
            peso = teclado.nextFloat();
            pesoEstimado = estimarPeso(temp.get(y-1),origen);
            if (peso>(temp.get(y-1).getCapacidad()-pesoEstimado))
                System.out.println("Lo siento, el barco no puede soportar tanta carga");
        } while (peso>(temp.get(y-1).getCapacidad()-pesoEstimado));
        
    }
       
    static Ruta IngresarRuta(){
        Ruta ruta; Calendar fecha;
        String op = "s"; int i=0, y=0, codigo, dia, mes, año; 
        
        System.out.println("\tIngreso de Ruta");
        System.out.println("Codigo de la Ruta: ");
        codigo = teclado.nextInt();
        ruta = new Ruta(codigo);
        while (op.equalsIgnoreCase("S")){
            op = " ";
            while( ! op.equalsIgnoreCase("S") && ! op.equalsIgnoreCase("N") ){
                System.out.println("\n¿Desea Agregar un Puerto a la Ruta? S/N");
                op = teclado.next();
            }

            if (op.equalsIgnoreCase("S")){
                System.out.println("Puertos Existentes:");
                for (Puerto x:puertosExistentes){
                    i = i+1;
                    System.out.println(i+".) " + x.getNombrePuerto());
                }
                while (y<=0||y>puertosExistentes.size()){
                    System.out.println("Seleccione el Puerto a Agregar: ");
                    y = teclado.nextInt();
                }
                ruta.getPuerto().add(puertosExistentes.get(y-1));
                System.out.println("Fecha Estimada de Arribo:");
                if (ruta.getFechaA().isEmpty()){
                    System.out.println("\nDia: ");
                    dia = teclado.nextInt();
                    System.out.println("Mes: ");
                    mes = teclado.nextInt();
                    System.out.println("Año: ");
                    año = teclado.nextInt();
                    fecha = Calendar.getInstance();
                    fecha.set(año, dia, mes, 0, 0, 0);
                    ruta.getFechaA().add(fecha);
                } else {
                    do{
                        System.out.println("\nDia: ");
                        dia = teclado.nextInt();
                        System.out.println("Mes: ");
                        mes = teclado.nextInt();
                        System.out.println("Año: ");
                        año = teclado.nextInt();
                        fecha = Calendar.getInstance();
                        fecha.set(año, dia, mes, 0, 0, 0);
                    } while (fecha.after(ruta.getFechaA().get(-1)));
                }
                ruta.getFechaA().add(fecha);  
            }
        }
        return ruta;
    }
    
    public static void main(String[] args){
        int opcion=0;
        while (opcion!=5){
           System.out.println("\tBienvenido\n");
           System.out.println("\tMenu\n");
           System.out.println("1. Agregar un Puerto");
           System.out.println("2. Agregar un Barco");
           System.out.println("3. Programar envio de Carga");
           System.out.println("4. Arribar a un Puerto");
           System.out.println("5. Salir");
           opcion = teclado.nextInt();
           
           switch (opcion){
               case 1:
                   IngresarPuerto();
                   break;
               case 2:
                   IngresarBarco();
                   break;
               case 3:
                   IngresarCarga();
                   break;
               case 4:
                   break;
               case 5:
                   System.out.println("Gracias por utilizar nuestro Programa");
                   System.out.println("Nos vemos");
                   break;
           }
        }
    }
    
}
