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
    static SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    
    static void IngresarPuerto(){
        String nombre, pais, coordenadas;
        System.out.println("Ingreso de Puertos");
        System.out.println("\nNombre del Puerto: ");
        teclado.nextLine(); nombre = teclado.nextLine();
        System.out.println("Pais del Puerto: ");
        pais = teclado.nextLine();
        System.out.println("Coordenadas del Puerto: ");
        coordenadas = teclado.nextLine();
        
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
        teclado.nextLine(); nombre = teclado.nextLine();
        System.out.println("Capitan del Barco: ");
        capitan = teclado.nextLine();
        System.out.println("Naviera Propietaria del Barco: ");
        naviera = teclado.nextLine();
        System.out.println("Pais de la Naviera: ");
        pnaviera = teclado.nextLine();
        System.out.println("Capacidad Maxima en toneladas Métricas del Barco: ");
        capacidadMaxima = teclado.nextFloat();
        System.out.println("Capacidad Maxima de Contenedores en el Barco: ");
        capacidadCont = teclado.nextInt();
        ruta = IngresarRuta();
        Barco barco = new Barco(nombre, naviera, pnaviera, capacidadMaxima,
                                capacidadCont, ruta, capitan);
        barcosExistentes.add(barco);
    }
    
    static float estimarPeso(Barco barco, Puerto o){ 
        float x = 0; int y = 0, z = 0;
        for (Puerto puertito:barco.getRuta().getPuerto()){
            if (puertito.getNombrePuerto().equals(o.getNombrePuerto()))
                y = barco.getRuta().getPuerto().indexOf(puertito);
        }
        
        for (Carga producto:barco.getCargaProg()){
            for (Puerto puertito:barco.getRuta().getPuerto()){
                if (producto.getDestino().getNombrePuerto().equals(puertito.getNombrePuerto()))
                    z = barco.getRuta().getPuerto().indexOf(puertito);
            }
            x+=producto.getPeso();
            if (barco.getRuta().getFechaA().get(z).before(barco.getRuta().getFechaA().get(y))){
                x-=producto.getPeso();
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
        
        System.out.println("\tIngreso de Carga\n");
        System.out.println("Codigo de la Carga: ");
        codCarga = teclado.nextInt();
        System.out.println("Dueño de la Carga: ");
        dueño = teclado.next();
        System.out.println("Descripcion de la Carga: ");
        descripcion = teclado.next();
        
        do{
            System.out.println("Nombre del Puerto de Origen: ");
            puerto = teclado.next();
            if (comparaPuerto(puerto)==-1)
                System.out.println("Lo sentimos, ese puerto no Existe.");
        } while (comparaPuerto(puerto)==-1);
        origen.copy(puertosExistentes.get(comparaPuerto(puerto)));
        
        do{
            System.out.println("Nombre del Puerto de Destino: ");
            puerto = teclado.next();
            if (comparaPuerto(puerto)==-1)
                System.out.println("Lo sentimos, ese puerto no Existe.");
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
        
        if (temp.isEmpty()){
            System.out.println("Lo Sentimos, Ningun Barco Cumple Con Los Puertos de la Carga");
            return;
        } 
        System.out.println("Barcos Recomendados:\n");
        for (Barco barquito:temp){
            i+=1;
            System.out.println(i+".) " + barquito.getNombre() + ", Espacio Disponible: " + (barquito.getCapacidad()-estimarPeso(barquito, origen)));
        }
        while (y<=0||y>temp.size()){
            System.out.println("Seleccione el Barco que mejor le Parezca: ");
            y = teclado.nextInt();
            if (y<=0||y>temp.size())
                System.out.println("Esa no es una opcion valida.");
        }
        do{
            System.out.println("Peso de la Carga: ");
            peso = teclado.nextFloat();
            pesoEstimado = estimarPeso(temp.get(y-1),origen);
            if (peso>(temp.get(y-1).getCapacidad()-pesoEstimado))
                System.out.println("Lo siento, el barco no puede soportar tanta carga");
        } while (peso>(temp.get(y-1).getCapacidad()-pesoEstimado));
        
        Carga carga = new Carga(codCarga, dueño, descripcion, peso, origen, destino);
        for (Barco barco1:barcosExistentes){
            if (barco1.getNombre().equals(temp.get(y-1).getNombre())){
                barco1.getCargaProg().add(carga);
            }
        }
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
                i = 0;
                System.out.println("Puertos Existentes:");
                for (Puerto x:puertosExistentes){
                    i = i+1;
                    System.out.println(i+".) " + x.getNombrePuerto());
                }
                y = 0;
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
                    fecha.set(año, mes, dia, 0, 0, 0);
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
                        fecha.set(año, mes, dia, 0, 0, 0);
                    } while (fecha.before(ruta.getFechaA().get(ruta.getFechaA().size()-1)));
                }
                ruta.getFechaA().add(fecha);  
            }
        }
        return ruta;
    }
    
    static void consolidarCarga(Calendar fecha){
        int x; float y;
        ArrayList<Carga> temp = new ArrayList<Carga>();
        for (Barco barco:barcosExistentes){
            if (! barco.getCargaProg().isEmpty()){
                for (Calendar fecha2:barco.getRuta().getFechaA()){
                    if (fecha.after(fecha2)||fecha.equals(fecha2)){
                        x = barco.getRuta().getFechaA().indexOf(fecha2);
                        System.out.println("\nFecha: " + date_format.format(fecha.getTime())+" - Puerto: " + barco.getRuta().getPuerto().get(x).getNombrePuerto());
                        // Descargue
                        for (Carga carga:barco.getCargaProg()){
                            if (carga.getDestino().getNombrePuerto().equals(barco.getRuta().getPuerto().get(x).getNombrePuerto())){
                                System.out.println("Descargue:");
                                System.out.println("- Codigo: " + carga.getCodCarga() + ", Descripcion: " + carga.getDescripcion() + ".");
                                System.out.println("  Peso: " + carga.getPeso() + ", Propietario: " + carga.getDueño() + ".");
                                for (Contenedor cont:barco.getContenedores()){
                                    for(Carga charge:cont.getCarga()){
                                        if (charge.getCodCarga()==carga.getCodCarga()){
                                            cont.getCarga().remove(cont.getCarga().indexOf(charge));
                                            cont.setCargaActual(cont.getCargaActual()-charge.getPeso());
                                        }
                                    }
                                }
                            }
                        }
                        // Consolidar Carga de Nuevo
                        for (Contenedor cont:barco.getContenedores()){
                            for (Carga charge:cont.getCarga()){
                                temp.add(charge);
                            }
                            cont.getCarga().clear(); cont.setCargaActual(0);
                        }
                        if (temp.size()>=2){
                            for (int i=0; i<=(temp.size()-2); i++){
                                if (temp.get(i).getCodCarga()==temp.get(i+1).getCodCarga()){
                                    temp.get(i).addPeso(temp.get(i+1).getPeso());
                                    temp.remove(i+1);
                                }
                            }
                        }
                        int f = 0;
                        for (Carga charge:temp){
                            y = (barco.getContenedores().get(f).getCapacidad()-barco.getContenedores().get(f).getCargaActual());
                            if (charge.getPeso()>y){
                                Carga carga1 = new Carga (charge.getCodCarga(), charge.getDueño(), charge.getDescripcion(), (charge.getPeso()-y), charge.getOrigen(), charge.getDestino());
                                charge.setPeso(y);
                                barco.getContenedores().get(f).getCarga().add(charge);
                                f+=1;
                                barco.getContenedores().get(f).getCarga().add(carga1);
                            } else {
                                barco.getContenedores().get(f).getCarga().add(charge);
                            }
                        }
                        // Cargue
                        f = 0;
                        for (Carga carga:barco.getCargaProg()){
                            if (carga.getOrigen().getNombrePuerto().equals(barco.getRuta().getPuerto().get(x).getNombrePuerto())){
                                System.out.println("Cargue:");
                                System.out.println("- Codigo: " + carga.getCodCarga() + ", Descripcion: " + carga.getDescripcion() + ".");
                                System.out.println("  Peso: " + carga.getPeso() + ", Propietario: " + carga.getDueño() + ".");
                                
                                if (barco.getContenedores().isEmpty()){
                                    Contenedor cont1 = new Contenedor (barco.getCapacidad()/barco.getCapContendores());
                                    barco.getContenedores().add(cont1);   
                                }
                                y = (barco.getContenedores().get(f).getCapacidad()-barco.getContenedores().get(f).getCargaActual());
                                if (carga.getPeso()>y){
                                    Carga carga1 = new Carga (carga.getCodCarga(), carga.getDueño(), carga.getDescripcion(), (carga.getPeso()-y), carga.getOrigen(), carga.getDestino());
                                    carga.setPeso(y);
                                    barco.getContenedores().get(f).getCarga().add(carga);
                                    barco.getContenedores().get(f).setCargaActual(barco.getContenedores().get(f).getCapacidad());
                                    f+=1;
                                    if ((barco.getContenedores().size()-1)==f){
                                        Contenedor cont1 = new Contenedor (barco.getCapacidad()/barco.getCapContendores());
                                        barco.getContenedores().add(cont1);
                                    }
                                    barco.getContenedores().get(f).getCarga().add(carga1);
                                    barco.getContenedores().get(f).setCargaActual((carga.getPeso()-y));
                                } else {
                                    barco.getContenedores().get(f).getCarga().add(carga);
                                    barco.getContenedores().get(f).setCargaActual((carga.getPeso()+y));
                                }
                            }
                        }
                        barco.getRuta().getFechaA().remove(x);
                        barco.getRuta().getPuerto().remove(x);
                    }  
                }
            }
        }
        
    }

    static void arriboPuerto(){
        int año, mes, dia;
        Calendar fechaMenor = Calendar.getInstance();
        Calendar fecha1 = Calendar.getInstance();
        fechaMenor.set(3000, 12, 1, 0, 0, 0);
        System.out.println("\tArribo a un Puerto");
        for (int x=0; x<barcosExistentes.size(); x++){
            if (barcosExistentes.get(x).getRuta().getFechaA().get(0).before(fechaMenor)){
                fechaMenor = barcosExistentes.get(x).getRuta().getFechaA().get(0);
            }
        }
        System.out.println("En esta sección se Calculan todos los Cargues y Descargues automáticamente");
        System.out.println("\nIngrese la Fecha Actual: ");
        
        System.out.println("\nDia: ");
        dia = teclado.nextInt();
        System.out.println("Mes: ");
        mes = teclado.nextInt();
        System.out.println("Año: ");
        año = teclado.nextInt();
        fecha1.set(año, mes, dia, 0, 0, 0);
        if (fecha1.before(fechaMenor)){
            System.out.println("Lo sentimos, a tal Fecha no hay actividad alguna.");
        } else {
            consolidarCarga(fecha1);
            System.out.println("Chilerisimo :D");
        }
        
    }
    
    static void c1(){
        int i = 0;
        System.out.println("\nPuertos Registrados: ");
        for (Puerto x:puertosExistentes){
            i = i+1;
            System.out.println(i+".) " + x.getNombrePuerto());
        }
    }
    static void consultaInformacion(){
        int op;
        do {
        System.out.println("¿Que Informacion Desea Consultar?\n");
        System.out.println("1.) Puertos registrados.");
        System.out.println("2.) Cantidad de contenedores ya cargados en un barco, cuyo desino es un puerto específico.");
        System.out.println("3.) La ruta que seguirá un barco, indicado los puertos y contenedores que actualmente hay que desembarcar en cada uno de esos puertos.");
        System.out.println("4.) Cantidad de barcos que hay en un puerto en un momento dado.");
        System.out.println("5.) La programación de arribos de barcos a un puerto en un periodo de tiempo dado.");
        System.out.println("6.) Consulta de las personas o empresas a quienes se les envía carga en un puerto específico.");
        System.out.println("7.) Regresar a Menu Principal");
        op = teclado.nextInt();
        if (op<=0||op>7)
            System.out.println("Lo Sentimos, esa Opcion no Existe.\nIngrese otra de Nuevo.");
        } while (op<=0||op>7);
        
        switch (op){
               case 1:
                   break;
               case 2:
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 5:
                   break;
               case 6:
                   break;
               case 7:
                   System.out.println("Gracias por utilizar nuestro Programa");
                   System.out.println("Nos vemos");
                   break;
        }
    }
    
    public static void main(String[] args){
        int opcion=0;
        System.out.println("\tBienvenido");
        while (opcion!=5){
           System.out.println("\n\tMenu\n");
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
                   arriboPuerto();
                   break;
               case 5:
                   System.out.println("Gracias por utilizar nuestro Programa");
                   System.out.println("Nos vemos");
                   break;
           }
        }
    }
    
}
