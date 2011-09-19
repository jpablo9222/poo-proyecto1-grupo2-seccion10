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
    static ArrayList<Carga> cargasExistentes = new ArrayList<Carga>();
    static ArrayList<Ruta> rutasExistentes = new ArrayList<Ruta>();
    static SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    
    public static int ingresarInt() {
        Integer x = null;
        while (x == null) {
            String y = teclado.nextLine();
            try {
                x = Integer.parseInt(y);
            } catch (Exception e) {
                x = null;
                System.out.println("Error... Por Favor Ingrese Un Numero Entero.");
            }
        }
        return x;
    }

    public static float ingresarFloat() {
        Float x = null;
        while (x == null) {
            String y = teclado.nextLine();
            try {
                x = Float.parseFloat(y);
            } catch (Exception e) {
                x = null;
                System.out.println("Error... Por Favor Ingrese Un Numero.");
            }
        }
        return x;
    }
    
    static void IngresarPuerto(){
        String nombre, pais, coordenadas; boolean x;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nIngreso de Puertos");
        do{
            System.out.println("\nNombre del Puerto: ");
            nombre = teclado.nextLine();
            x = false;
            for(Puerto puertito:puertosExistentes){
                if(puertito.getNombrePuerto().equalsIgnoreCase(nombre)){
                    System.out.println("Lo Sentimos, Ya Existe Un Puerto Con Ese Nombre.");
                    System.out.println("Ingrese Otro Nombre De Nuevo.");
                    x = true;
                }
            }
        }while(x);
        System.out.println("Pais del Puerto: ");
        pais = teclado.nextLine();
        do{
            System.out.println("Coordenadas del Puerto: ");
            coordenadas = teclado.nextLine();
            x = false;
            for(Puerto puertito:puertosExistentes){
                if(puertito.getCoordenadaPuerto().equalsIgnoreCase(coordenadas)){
                    System.out.println("Lo Sentimos, Ya Existe Un Puerto Con Esas Coordenadas.");
                    System.out.println("Ingrese Otras Coordenadas De Nuevo.");
                    x = true;
                }
            }
        }while(x);
        
        Puerto puerto = new Puerto (nombre, pais, coordenadas);
        puertosExistentes.add(puerto);
        System.out.println("Puerto Ingresado Exitosamente");
    }
    
    static void IngresarBarco(){
        String naviera, pnaviera, nombre, capitan;
        float capacidadMaxima;
        int capacidadCont;
        Ruta ruta; boolean x;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nIngreso de Barcos\n");
        do{
            System.out.println("Nombre del Barco: ");
            nombre = teclado.nextLine();
            x = false;
            for(Barco barquito:barcosExistentes){
                if(barquito.getNombre().equalsIgnoreCase(nombre)){
                    System.out.println("Lo Sentimos, Ya Existe Un Barco Con Ese Nombre.");
                    System.out.println("Ingrese Otro Nombre De Nuevo.");
                    x = true;
                }
            }
        }while(x);
        System.out.println("Capitan del Barco: ");
        capitan = teclado.nextLine();
        System.out.println("Naviera Propietaria del Barco: ");
        naviera = teclado.nextLine();
        System.out.println("Pais de la Naviera: ");
        pnaviera = teclado.nextLine();
        System.out.println("Capacidad Maxima en toneladas Métricas del Barco: ");
        capacidadMaxima = ingresarFloat();
        System.out.println("Capacidad Maxima de Contenedores en el Barco: ");
        capacidadCont = ingresarInt();
        ruta = IngresarRuta();
        Barco barco = new Barco(nombre, naviera, pnaviera, capacidadMaxima,
                                capacidadCont, ruta, capitan);
        barcosExistentes.add(barco);
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
        int codCarga, i=0, y=0, ind, ind1 = 0, ind2 = 0; String dueño, descripcion, puerto;
        float peso, pesoEstimado; boolean b1, b2,x;
        Puerto origen = new Puerto("","","");
        Puerto destino = new Puerto("","","");
        ArrayList<Integer> temp = new ArrayList<Integer>();
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nIngreso de Carga\n");
        do{
            System.out.println("Codigo de la Carga: ");
            codCarga = ingresarInt();
            x = false;
            for(Carga carguita:cargasExistentes){
                if(carguita.getCodCarga()==codCarga){
                    System.out.println("Lo Sentimos, Ya Existe Una Carga Con Ese Codigo.");
                    System.out.println("Ingrese Nuevamente Otro Codigo para la Carga.");
                    x = true;
                }
            }
        }while(x);
        System.out.println("Dueño de la Carga: ");
        dueño = teclado.nextLine();
        System.out.println("Descripcion de la Carga: ");
        descripcion = teclado.nextLine();
        
        do{
            System.out.println("Nombre del Puerto de Origen: ");
            puerto = teclado.nextLine();
            if (comparaPuerto(puerto)==-1)
                System.out.println("Lo sentimos, ese puerto no Existe.");
        } while (comparaPuerto(puerto)==-1);
        origen.copy(puertosExistentes.get(comparaPuerto(puerto)));
        
        do{
            System.out.println("Nombre del Puerto de Destino: ");
            puerto = teclado.nextLine();
            if (comparaPuerto(puerto)==-1)
                System.out.println("Lo sentimos, ese puerto no Existe.");
        } while (comparaPuerto(puerto)==-1);
        destino.copy(puertosExistentes.get(comparaPuerto(puerto)));
        
        for (Barco barco:barcosExistentes){
            b1 = false; b2 = false;
            for (Puerto puertito:barco.getRuta().getPuerto()){
                ind = barco.getRuta().getPuerto().indexOf(puertito);
                if ((puertito.getNombrePuerto().equals(origen.getNombrePuerto()))){
                    b1 = true; ind1 = ind;
                }   
                if ((puertito.getNombrePuerto().equals(destino.getNombrePuerto()))){
                    b2 = true; ind2 = ind;
                }      
            }
            if (b1 && b2){
                if (ind2>ind1){
                    temp.add(barcosExistentes.indexOf(barco));
                }     
            }
        }
        
        if (temp.isEmpty()){
            System.out.println("Lo Sentimos, Ningun Barco Cumple Con Los Puertos de la Carga");
            return;
        } 
        System.out.println("Barcos Recomendados:\n");
        for (Integer b:temp){
            i+=1;
            pesoEstimado=0;
            for (Carga producto:barcosExistentes.get(b).getCargaProg()){
                   pesoEstimado+=producto.getPeso();
            } 
            System.out.println(i+".) " + barcosExistentes.get(b).getNombre() + ", Espacio Disponible: " + (barcosExistentes.get(b).getCapacidad()-pesoEstimado));
        }
        while (y<=0||y>temp.size()){
            System.out.println("Seleccione el Barco que mejor le Parezca: ");
            y = ingresarInt();
            if (y<=0||y>temp.size())
                System.out.println("Esa no es una opcion valida.");
        }
        pesoEstimado=0;
        for (Carga producto:barcosExistentes.get(temp.get(y-1)).getCargaProg()){
            pesoEstimado+=producto.getPeso();
        }
        do{
            System.out.println("Peso de la Carga: ");
            peso = ingresarFloat(); 
            if (peso>barcosExistentes.get(temp.get(y-1)).getCapacidad()-pesoEstimado)
                System.out.println("Lo siento, el barco no puede soportar tanta carga");
        } while (peso>(barcosExistentes.get(temp.get(y-1)).getCapacidad()-pesoEstimado));
        
        Carga carga = new Carga(codCarga, dueño, descripcion, peso, origen, destino);
        for (Barco barco1:barcosExistentes){
            if (barco1.getNombre().equals(barcosExistentes.get(temp.get(y-1)).getNombre())){
                barco1.getCargaProg().add(carga);
                cargasExistentes.add(carga);
            }
        }
    }
       
    static Ruta IngresarRuta(){
        Ruta ruta; Calendar fecha; boolean x;
        String op = "s"; int y=0, codigo; 
        
        System.out.println("\n\tIngreso de Ruta");
        do{
            System.out.println("Codigo de la Ruta: ");
            codigo = ingresarInt();
            x = false;
            for(Ruta rutita:rutasExistentes){
                if(rutita.getCodRuta()==codigo){
                    System.out.println("Lo Sentimos, Ya Existe Una Ruta Con Ese Codigo.");
                    System.out.println("Ingrese Nuevamente un Codigo Para la Ruta.");
                    x = true;
                }
            }
        }while(x);
        ruta = new Ruta(codigo);
        while (op.equalsIgnoreCase("S")){
            op = " ";
            while( ! op.equalsIgnoreCase("S") && ! op.equalsIgnoreCase("N") ){
                System.out.println("\n¿Desea Agregar un Puerto a la Ruta? S/N");
                op = teclado.nextLine();
                if (!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"))
                    System.out.println("Lo Sentimos, Esa No es Una Opcion Valida.");
            }

            if (op.equalsIgnoreCase("S")){
                System.out.println("Puertos Existentes:");
                listaPuertos();
                y = 0;
                while (y<=0||y>puertosExistentes.size()){
                    System.out.println("Seleccione el Puerto a Agregar: ");
                    y = ingresarInt();
                    if (y<=0||y>puertosExistentes.size())
                        System.out.println("Lo sentimos, ese Puerto no Existe");  
                }
                ruta.getPuerto().add(puertosExistentes.get(y-1));
                System.out.println("\nFecha Estimada de Arribo:");
                if (ruta.getFechaA().isEmpty()){
                    fecha = ingresarFecha();
                    ruta.getFechaA().add(fecha);
                } else {
                    do{
                        fecha = ingresarFecha();
                        if (!fecha.after(ruta.getFechaA().get(ruta.getFechaA().size()-1)))
                            System.out.println("Lo Sentimos, Debe Ingresar Una Fecha Posterior a la Parada Anterior.\n");
                    } while (!fecha.after(ruta.getFechaA().get(ruta.getFechaA().size()-1)));
                }
                ruta.getFechaA().add(fecha);  
            }
        }
        rutasExistentes.add(ruta);
        return ruta;
    }

    static void arriboPuerto(){
        int x; float y=0;
        ArrayList<Carga> temp = new ArrayList<Carga>();
        Calendar fechaMenor = Calendar.getInstance();
        Calendar fecha = Calendar.getInstance();
        fechaMenor.set(3000, 12, 1, 0, 0, 0);
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nArribo a un Puerto");
        for (int w=0; w<barcosExistentes.size(); w++){
            if (barcosExistentes.get(w).getRuta().getFechaA().get(0).before(fechaMenor)){
                fechaMenor = barcosExistentes.get(w).getRuta().getFechaA().get(0);
            }
        }
        System.out.println("En esta sección se Calculan todos los Cargues y Descargues automáticamente");
        System.out.println("\nIngrese la Fecha Actual: ");
        
        fecha = ingresarFecha();
        if (fecha.before(fechaMenor)){
            System.out.println("Lo sentimos, a tal Fecha no hay actividad alguna.");
        } else {
            for (Barco barco:barcosExistentes){
                if (!barco.getCargaProg().isEmpty()){
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
                                    barco.getContenedores().get(f).setCargaActual(barco.getContenedores().get(f).getCapacidad());
                                    f+=1;
                                    barco.getContenedores().get(f).getCarga().add(carga1);
                                    barco.getContenedores().get(f).setCargaActual(carga1.getPeso());
                                } else {
                                    barco.getContenedores().get(f).getCarga().add(charge);
                                    barco.getContenedores().get(f).setCargaActual(barco.getContenedores().get(f).getCargaActual()+charge.getPeso());
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
                                    y = ((barco.getContenedores().get(f).getCapacidad())-(barco.getContenedores().get(f).getCargaActual()));
                                    //try{
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
                                        barco.getContenedores().get(f).setCargaActual(carga1.getPeso());
                                    } else {
                                        barco.getContenedores().get(f).getCarga().add(carga);
                                        barco.getContenedores().get(f).setCargaActual(barco.getContenedores().get(f).getCargaActual()+carga.getPeso());
                                    }
                                //} catch (Exception e){
                                    
                                //}
                                }    
                            }
                            barco.getRuta().getFechaA().remove(x);
                            barco.getRuta().getPuerto().remove(x);
                        }  
                    }
                }
            }
            System.out.println("Chilerisimo :D");
        }
        
    }
    
    static void listaPuertos(){
        int i = 0;
        for (Puerto x:puertosExistentes){
            i = i+1;
            System.out.println(i+".) " + x.getNombrePuerto());
        }
    }
    
    static void c1(){
        System.out.println("\nPuertos Registrados: ");
        listaPuertos();
    }
    
    static void c2(){
        int y = 0,x = 0;
        System.out.println("Puertos Existentes");
        listaPuertos();
        while (y<=0||y>puertosExistentes.size()){
            System.out.println("¿Cual puerto desea analizar?");
            y = ingresarInt();
            if (y<=0||y>puertosExistentes.size())
                System.out.println("Lo sentimos, ese Puerto no Existe");          
        }
        for (Barco barco:barcosExistentes){
            for (Contenedor cont:barco.getContenedores()){
                for (Carga carga:cont.getCarga()){
                    if (carga.getDestino().getNombrePuerto().equals(puertosExistentes.get(y-1).getNombrePuerto()))
                        x+=1;   
                }      
            }
            if (x>0)
                System.out.println("Hay " + x + " contenedores cargados del Barco " + barco.getNombre() + ".");
            x=0;
        }
    }
    
    static void c3(){
        int i = 0, y = 0, z=0, f=0;
        System.out.println("Barcos Existentes");
        for (Barco x:barcosExistentes){
            i = i+1;
            System.out.println(i+".) " + x.getNombre());
        }
        while (y<=0||y>barcosExistentes.size()){
            System.out.println("¿Cual barco desea analizar?");
            y = ingresarInt();
            if (y<=0||y>barcosExistentes.size())
                System.out.println("Lo sentimos, ese Puerto no Existe");          
        }
        System.out.println("\nRuta del Barco "+barcosExistentes.get(y-1).getNombre());
        for (Puerto puerto:barcosExistentes.get(y-1).getRuta().getPuerto()){
            f = barcosExistentes.get(y-1).getRuta().getPuerto().indexOf(puerto);
            System.out.println("Puerto: "+puerto.getNombrePuerto()+", Fecha: "+date_format.format(barcosExistentes.get(y-1).getRuta().getFechaA().get(f).getTime()));
            for (Contenedor cont:barcosExistentes.get(y-1).getContenedores()){
                for (Carga carga:cont.getCarga()){
                    if (carga.getDestino().getNombrePuerto().equals(puerto.getNombrePuerto()))
                        z+=1;   
                }      
            }
            if (z>0){
                System.out.println("Hay "+z+" contenedores a descargar.");
            }else{
                System.out.println("No hay ningun contenedor a descargar.");
            }
            z=0;
        }
    }
    
    static Calendar ingresarFecha(){
        int dia, mes, año;
        Calendar fecha;
        System.out.println("Ingrese la Fecha a Analizar\n");
        do{
            System.out.println("Dia: ");
            dia = ingresarInt();
            if (dia<1||dia>31)
                System.out.println("Debe Ingresar un Dia Entre 1 y 31");
        }while(dia<1||dia>31);
        do{
            System.out.println("Mes: ");
            mes = ingresarInt();
            if (mes<1||mes>12)
                System.out.println("Debe Ingresar un Mes Entre 1 y 12.");
        }while(mes<1||mes>12);
        do{
            System.out.println("Año: ");
            año = ingresarInt();
            if (año<2010)
                System.out.println("Debe Ingresar un Año Mayor a 2010.");
        }while(año<2010);
        fecha = Calendar.getInstance();
        fecha.set(año, mes, dia, 0, 0, 0);
        return fecha;
    }
    
    static void c4(){
        Calendar fecha; int x = 0;
        System.out.println("Ingrese la Fecha a Analizar");
        fecha = ingresarFecha();
        System.out.println("\nPara la Fecha de "+ date_format.format(fecha.getTime())+" habran:");
        for (Puerto puerto:puertosExistentes){
            for (Barco barco:barcosExistentes){
                for (Calendar fecha1:barco.getRuta().getFechaA()){
                    if (barco.getRuta().getPuerto().get(barco.getRuta().getFechaA().indexOf(fecha1)).getNombrePuerto().equals(puerto.getNombrePuerto())&&fecha1.equals(fecha)){
                        x+=1;
                    }
                }
            }
            System.out.println("En el Puerto: "+puerto.getNombrePuerto());
            if (x>0){
                System.out.println("Habran " + x + " barcos.");
                x=0;
            }else{
                System.out.println("No habra Barco Alguno.");
            }
        }
    }
    
    static void c5(){
        Calendar fecha1, fecha2; boolean x;
        System.out.println("Ingrese el Intervalo de Tiempo a Revisar");
        System.out.println("Fecha de Inicio:");
        fecha1 = ingresarFecha();
        do{
            System.out.println("Fecha Final");
            fecha2 = ingresarFecha();
            if (!fecha1.before(fecha2))
                System.out.println("Lo sentimos, la fecha final debe ser posterior a la fecha inicial");
        }while(!fecha1.before(fecha2));
        
        for (Puerto puerto:puertosExistentes){
            System.out.println("Puerto: " + puerto.getNombrePuerto());
            x = false;
            for (Barco barco:barcosExistentes){
                for (Calendar fecha:barco.getRuta().getFechaA()){
                    if (barco.getRuta().getPuerto().get(barco.getRuta().getFechaA().indexOf(fecha1)).getNombrePuerto().equals(puerto.getNombrePuerto())&&!fecha.before(fecha1)&&!fecha.after(fecha2)){
                        System.out.println("- Fecha: " + date_format.format(fecha.getTime()) + " - Barco: " + barco.getNombre());
                        x = true;
                    }
                }
            }
            if (!x)
                System.out.println("No habra Barco Alguno.");
        }
    }
    
    static void c6(){
        int y = 0;
        System.out.println("Puertos Existentes");
        listaPuertos();
        while (y<=0||y>puertosExistentes.size()){
            System.out.println("¿Cual puerto desea analizar?");
            y = ingresarInt();
            if (y<=0||y>puertosExistentes.size())
                System.out.println("Lo sentimos, ese Puerto no Existe");          
        }
        System.out.println("\nDestinatarios del Puerto " + puertosExistentes.get(y-1).getNombrePuerto());
        for (Carga carga:cargasExistentes){
            if (carga.getDestino().getNombrePuerto().equals(puertosExistentes.get(y-1).getNombrePuerto())){
                System.out.println(carga.getDueño());
            }
        }
    }
    
    
    static void consultaInformacion(){
        int op;
        System.out.println("\n-------------------------------------------------");
        do {
            System.out.println("\n¿Que Informacion Desea Consultar?\n");
            System.out.println("1.) Puertos registrados.");
            System.out.println("2.) Cantidad de contenedores ya cargados en un barco, cuyo destino es un puerto específico.");
            System.out.println("3.) La ruta que seguirá un barco, indicado los puertos y contenedores que actualmente hay que desembarcar en cada uno de esos puertos.");
            System.out.println("4.) Cantidad de barcos que hay en un puerto en un momento dado.");
            System.out.println("5.) La programación de arribos de barcos a un puerto en un periodo de tiempo dado.");
            System.out.println("6.) Consulta de las personas o empresas a quienes se les envía carga en un puerto específico.");
            System.out.println("7.) Regresar a Menu Principal");
            op = ingresarInt();
            if (op<=0||op>7)
                System.out.println("Lo Sentimos, esa Opcion no Existe.\nIngrese otra de Nuevo.");
        } while (op<=0||op>7);
        
        switch (op){
               case 1:
                   if (puertosExistentes.isEmpty()){
                       System.out.println("No Hay Ningun Puerto Registrado.");
                   }else{
                       c1();
                   }
                   break;
               case 2:
                   if (cargasExistentes.isEmpty()){
                       System.out.println("No Hay Ningun Contenedor Cargado.");
                   }else{
                       c2();
                   }
                   break;
               case 3:
                   if (barcosExistentes.isEmpty()){
                       System.out.println("No Hay Ninguna Ruta Establecida.");
                   }else{
                       c3();
                   }
                   break;
               case 4:
                   if (barcosExistentes.isEmpty()){
                       System.out.println("No Hay Ningun Barco Registrado para Algun Puerto.");
                   }else{
                       c4();
                   }
                   break;
               case 5:
                   if (barcosExistentes.isEmpty()){
                       System.out.println("No Hay Programacion de Arribo de Barcos en Ningun Puerto.");
                   }else{
                       c5();
                   }
                   break;
               case 6:
                   if (cargasExistentes.isEmpty()){
                       System.out.println("No Hay Ningun Envio de Cargas Programada.");
                   }else{
                       c6();
                   }
                   break;
               case 7:
                   return;
        }
        System.out.println("\nPresione Enter para Continuar");
        teclado.nextLine(); teclado.nextLine();
    }
    
    public static void main(String[] args){
        int opcion=0; boolean seguro = false; String op;
        System.out.println("\tBienvenido");
        while (opcion!=6 && !seguro){
           do{
               System.out.println("\n\tMenu\n");
               System.out.println("1. Agregar un Puerto");
               System.out.println("2. Agregar un Barco");
               System.out.println("3. Programar envio de Carga");
               System.out.println("4. Arribar a un Puerto");
               System.out.println("5. Realizar Consultas");
               System.out.println("6. Salir");
               opcion = ingresarInt();
               if (opcion<=0||opcion>6)
                   System.out.println("Lo Sentimos, Debe Ingresar un Numero entre 1 y 6.");
           }while (opcion<=0||opcion>6);
           
           switch (opcion){
               case 1:
                   IngresarPuerto();
                   break;
               case 2:
                   if (puertosExistentes.isEmpty()){
                       System.out.println("Lo Lamento, Debe Ingresar Puertos Antes de Poder Ingresar un Barco.");
                   }else{
                       IngresarBarco();
                   }
                   break;
               case 3:
                   if (barcosExistentes.isEmpty()){
                       System.out.println("Lo Lamento, Debe Ingresar Barcos Antes de Poder Ingresar una Carga.");
                   }else{
                       IngresarCarga();
                   }
                   break;
               case 4:
                   if (cargasExistentes.isEmpty()){
                       System.out.println("Lo Lamento, Debe Programar Envio de Cargas Antes de Poder Ingresar una Carga.");
                   }else{
                       arriboPuerto();
                   }
                   break;
               case 5:
                   consultaInformacion();
                   break;
               case 6:
                   do{
                      System.out.println("¿En Verdad Desea Salir del Programa? S/N");
                      op = teclado.nextLine();
                      if (!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"))
                          System.out.println("Lo Sentimos, Esa No es Una Opcion Valida.");
                   }while (!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"));
                   if (op.equalsIgnoreCase("S")){
                       System.out.println("Gracias por utilizar nuestro Programa");
                       System.out.println("Nos vemos");
                       seguro = true;
                   }
                   break;
           }
        }
    }
    
}
