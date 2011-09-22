package Prueba1;

/**
 *
 * @author Juan Pablo
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Menu {
    //Se declaran las listas en donde se guardan los objetos creados a controlar.
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Puerto> puertosExistentes = new ArrayList<Puerto>();
    static ArrayList<Barco> barcosExistentes = new ArrayList<Barco>();
    static ArrayList<Carga> cargasExistentes = new ArrayList<Carga>();
    static ArrayList<Ruta> rutasExistentes = new ArrayList<Ruta>();
    static SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    
   /**
    * Permite y Evalua el Ingreso de un Número Entero. Muestra Error si el dato Ingresado
    * No es un Número Entero. Devuelve el Número Entero Ingresado Correctamente.
    * @return x Número Entero Valido.
    */
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

   /**
    * Permite y Evalua el Ingreso de un Número Decimal. Muestra Error si el dato Ingresado
    * No es un Número. Devuelve el Número Decimal Ingresado Correctamente.
    * @return x Número Decimal Valido.
    */
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
    
   /**
    * Método que da lugar al Ingreso de un Puerto al Programa. Se le Pregunta al Usuario
    * Todos los Atributos del Puerto y se Validan Para Determinar que Sean Correctos.
    * Por Ultimo se Instancia un Puerto y Se Agrega a La Lista de Puertos del Programa.
    */    
    static void IngresarPuerto(){
        String nombre, pais, EO,NS; 
        boolean x; 
        float coorNS = 0, coorEO = 0;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tIngreso de Puertos");
        do{
            System.out.println("\nNombre del Puerto: ");
            nombre = teclado.nextLine();
            x = false;
            // Se compara el nombre ingresado con los nombres existentes para buscar que no hayan duplicados
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
        // De la misma manera se busca que no hayan coordenadas duplicadas en el ingreso
        do{
            System.out.println("Coordenadas del Puerto: ");
            System.out.println("Latitud: "); coorNS = ingresarFloat();
            System.out.println("Longitud: "); coorEO = ingresarFloat();
                        
            if (coorNS<0){
                NS="S";
            }else if (coorNS >0){
                NS="N";
            }else{ NS="";}
            
            if (coorEO<0){
                EO="O";
            }else if (coorEO>0){
                EO="E";
            }else{ EO="";}
            
            x = false;
            for(Puerto puertito:puertosExistentes){
                if(puertito.getCoordenadaPuerto().equalsIgnoreCase("Latitud: "+ coorNS+NS + "\t Longitud: "+coorEO+EO)){
                    System.out.println("Lo Sentimos, Ya Existe Un Puerto Con Esas Coordenadas.");
                    System.out.println("Ingrese Otras Coordenadas De Nuevo.");
                    x = true;
                }
            }
        }while(x);
        
        Puerto puerto = new Puerto (nombre, pais, coorNS, coorEO,EO,NS);
        puertosExistentes.add(puerto);
        System.out.println("Puerto Ingresado Exitosamente.");
    }
    
   /**
    * Método que da lugar al Ingreso de un Barco al Programa. Se le Pregunta al Usuario
    * Todos los Atributos del Barco y se Validan Para Determinar que Sean Correctos.
    * Por Ultimo se Instancia un Barco y Se Agrega a La Lista de Barcos del Programa.
    */
    static void IngresarBarco(){
        String naviera, pnaviera, nombre, capitan;
        float capacidadMaxima;
        int capacidadCont;
        Ruta ruta; boolean x;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tIngreso de Barcos\n");
        // se busca entre la lista de barcos que el nombre ingresado no exista y esto controla la
        // repeticion. una vez el puerto que se ingresa es nuevo, se termina.
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
        // Se instancia el barco con la informacion ingresada anteriormente
        Barco barco = new Barco(nombre, naviera, pnaviera, capacidadMaxima,
                                capacidadCont, ruta, capitan);
        barcosExistentes.add(barco);
        System.out.println("Barco Agregado Existosamente.");
    }
    // Busca un puerto en la lista de puertos y retorna el indice en el que esta el puerto
    // si el puerto no esta se retorna un -1
    static int comparaPuerto(String puerto){
        for (Puerto x:puertosExistentes){
            if (puerto.equals(x.getNombrePuerto())){
                return puertosExistentes.indexOf(x);
            }
        }
        return -1;
    }
    // Hace una estimacion de la carga que tiene un barco en determinado puerto 
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

   /**
    * Método que da lugar a la Programacion de Envio de una Carga. Se le Pregunta al Usuario
    * Todos los Atributos de la Carga y se Validan Para Determinar que Sean Correctos.
    * Por Ultimo se Instancia la Carga y Se Agrega a La Lista de Cargas del Programa.
    * Además se Agrega a la Lista de Cargas Programadas del Barco al cual se Encargo.
    */
    static void IngresarCarga(){
        int codCarga, i=0, y=0, ind, ind1 = 0, ind2 = 0; String dueño, descripcion, puerto;
        float peso, pesoEstimado; boolean b1, b2,x;
        Puerto origen = new Puerto("","",0.0f,0.0f,"","");
        Puerto destino = new Puerto("","",0.0f,0.0f,"","");
        ArrayList<Integer> temp = new ArrayList<Integer>();
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tIngreso de Carga\n");
        // Se determina si el codigo de carga exite o no, y si no se repite el ingreso
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
        int numero;
        // se imprime la lista de puertos para que el usuario escoja a que puerto, de los existentes,
        // se desea mandar la carga
        System.out.println("Nombre del Puerto de Origen: ");
        listaPuertos();
        numero = ingresarInt();
        destino.copy(puertosExistentes.get(numero-1));
        
        System.out.println("Nombre del Puerto de Destino: ");
        listaPuertos();
        numero = ingresarInt();
        destino.copy(puertosExistentes.get(numero-1));
        // se busca que barcos son los que contienen esta ruta
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
            System.out.println("\nLo Sentimos, Ningun Barco Cumple Con Los Puertos de la Carga");
            return;
        } 
        System.out.println("\nBarcos Recomendados:");
        for (Integer b:temp){
            i+=1;
            pesoEstimado=0;
            //for (Carga producto:barcosExistentes.get(b).getCargaProg()){
            //       pesoEstimado+=producto.getPeso();
            //} 
            System.out.println(i+".) " + barcosExistentes.get(b).getNombre() + ", Espacio Disponible: " + (barcosExistentes.get(b).getCapacidad()-estimarPeso(barcosExistentes.get(b),origen)) + "\n");
        }
        while (y<=0||y>temp.size()){
            System.out.println("Seleccione el Barco que mejor le Parezca: ");
            y = ingresarInt();
            if (y<=0||y>temp.size())
                System.out.println("Esa no es una opcion valida.");
        }
        pesoEstimado=0;
        //for (Carga producto:barcosExistentes.get(temp.get(y-1)).getCargaProg()){
          //  pesoEstimado+=producto.getPeso();
        //}
        // se determina la cantidad de peso que se puede ingresar, y si se desea ingresar mas de lo posible
        // se muestra error.
        do{
            System.out.println("Peso de la Carga: ");
            peso = ingresarFloat(); 
            if (peso>barcosExistentes.get(temp.get(y-1)).getCapacidad()-estimarPeso(barcosExistentes.get(temp.get(y-1)),origen))
                System.out.println("Lo siento, el barco no puede soportar tanta carga");
        } while (peso>(barcosExistentes.get(temp.get(y-1)).getCapacidad()-estimarPeso(barcosExistentes.get(temp.get(y-1)),origen)));
        
        Carga carga = new Carga(codCarga, dueño, descripcion, peso, origen, destino);
        for (Barco barco1:barcosExistentes){
            if (barco1.getNombre().equals(barcosExistentes.get(temp.get(y-1)).getNombre())){
                barco1.getCargaProg().add(carga);
                cargasExistentes.add(carga);
            }
        }
    }
    
   /**
    * Método que da lugar al Ingreso de una Ruta de un Barco. Se le Pregunta al Usuario
    * Todos los Atributos de la Ruta y se Validan Para Determinar que Sean Correctos.
    * Se Instancia la Ruta y se Pregunta El Itinerario de Visitas a los Puertos Previamente
    * Creados. Por Ultimo se Devuelve La Ruta Creada.
    * @return ruta Ruta Creada.
    */
    static Ruta IngresarRuta(){
        Ruta ruta; Calendar fecha; boolean x, bandera;
        String op = "s"; int y=0, codigo; 
        
        System.out.println("\n\tIngreso de Ruta");
        // se repite el ingreso de codigo de ruta hasta que el codigo de ruta que se agregue sea nueva
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
        System.out.println("\nAhora Debe Ingresar Los Puertos a Visitar");
        System.out.println("Le Recordamos que Debe Visitar Al Menos Dos Puertos\n");
        // Se repite el ingreso de puertos hasta que el usuario diga que ya no quiere mas puertos, o que se hayan agregado
        // todos los puertos existentes
        while ((op.equalsIgnoreCase("S")||(ruta.getPuerto().size()<2))&&(ruta.getPuerto().size()<puertosExistentes.size())){
            op = "S";
            if (op.equalsIgnoreCase("S")){
                System.out.println("\nPuertos Existentes:");
                listaPuertos();
                y = 0; bandera = true;
                while (y<=0||y>puertosExistentes.size()||bandera){
                    System.out.println("Seleccione el Puerto a Agregar: ");
                    y = ingresarInt();
                    if (y<=0||y>puertosExistentes.size())
                        System.out.println("Lo sentimos, ese Puerto no Existe");
                    bandera = false;
                    for (Puerto puertito:ruta.getPuerto()){
                        if (puertosExistentes.get(y-1).getNombrePuerto().equals(puertito.getNombrePuerto())){
                            System.out.println("Lo Sentimos, Solo Puede Ingresar Una Vez Un Puerto");
                            bandera = true;
                        }
                    }
                }
                // Se piden las fechas de arribo a cada uno de los puertos
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
                    ruta.getFechaA().add(fecha);
                }
            }
            op = "";
            if (ruta.getPuerto().size()==puertosExistentes.size()){
                System.out.println("Se Han Ingresado Todos Los Puertos Posibles a La Ruta.");
                System.out.println("Ya No Se Podra Seguir Agregando Mas Puertos.");
                op = "N";
            }
            while(!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N") ){
                System.out.println("\n¿Desea Agregar un Puerto a la Ruta? S/N");
                op = teclado.nextLine();
                if (!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"))
                    System.out.println("Lo Sentimos, Esa No es Una Opcion Valida.");
            }
            if ((ruta.getPuerto().size()<2)&&(op.equalsIgnoreCase("N"))){
                System.out.println("Lo Sentimos, Debe Agregar Al Menos Dos Puertos A La Ruta.");
            }
        }
        rutasExistentes.add(ruta);
        return ruta;
    }

   /**
    * Método que Simula El Recorrido de las Rutas Creadas, Hasta Llegar a Una Fecha
    * Establecida Por el Usuario. Al Simular las Rutas se Realizan Los Procesos de 
    * Carga y Descarga de Todos Los Envios de Cargas Previamente Programados. Esto Da
    * Lugar También a la Consolidacion de la Carga de Los Contenedores de Cada Barco
    * Con Una Carga Asignada. Este Ultimo Proceso Se Realiza al Momento de Realizar un
    * Cargue o Descargue.
    */
    static void arriboPuerto(){
        int x; float y=0; String letra;
        boolean encontrado = false;
        ArrayList<Carga> temp = new ArrayList<Carga>();
        ArrayList<Integer> index = new ArrayList<Integer>();
        ArrayList<Integer> index2 = new ArrayList<Integer>();
        Calendar fechaMenor = Calendar.getInstance();
        Calendar fecha = Calendar.getInstance();
        fechaMenor.set(3000, 12, 1, 0, 0, 0);
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tArribo a un Puerto");
        // Encuentra La Fecha Más Pequeñas de todas las Ingresadas.
        for (int w=0; w<barcosExistentes.size(); w++){
            if (!barcosExistentes.get(w).getRuta().getFechaA().isEmpty()){
                if (barcosExistentes.get(w).getRuta().getFechaA().get(0).before(fechaMenor)){
                    fechaMenor = barcosExistentes.get(w).getRuta().getFechaA().get(0);
                }
                encontrado = true;
            }
        }
        // Evalua Si Hay Rutas Por Simular
        if (!encontrado){
            System.out.println("Todos los Barcos Han Llegado a su Destino Previamente");
            do{
                System.out.println("Para Continuar Ingrese C (Continuar).");
                letra = teclado.nextLine();
                if (!letra.equalsIgnoreCase("C"))
                    System.out.println("Lo Lamento, Ingrese C Para Continuar.");
            }while(!letra.equalsIgnoreCase("C"));
            return;
        }
        System.out.println("\nEn esta sección se Calculan todos los Cargues y Descargues automáticamente");
        System.out.println("\nIngrese la Fecha Actual: ");
        // Pide Fecha Limite a Simular.
        fecha = ingresarFecha();
        if (fecha.before(fechaMenor)){
            System.out.println("Lo sentimos, a tal Fecha no hay actividad alguna.");
        } else {
            for (Barco barco:barcosExistentes){
                // Revisa que Barcos Poseen Cargas Programadas
                if (!barco.getCargaProg().isEmpty()){
                    for (Calendar fecha2:barco.getRuta().getFechaA()){
                        if (fecha2.before(fecha)){
                            x = barco.getRuta().getFechaA().indexOf(fecha2);
                            System.out.println("\nArribo del Barco: " + barco.getNombre());
                            System.out.println("Fecha: " + date_format.format(fecha2.getTime())+" - Puerto: " + barco.getRuta().getPuerto().get(x).getNombrePuerto());
                            // Proceso de Descargue
                            for (Carga carga:barco.getCargaProg()){
                                if (carga.getDestino().getNombrePuerto().equals(barco.getRuta().getPuerto().get(x).getNombrePuerto())){
                                    System.out.println("Descargue:");
                                    System.out.println("- Codigo: " + carga.getCodCarga() + ", Descripcion: " + carga.getDescripcion() + ".");
                                    System.out.println("  Peso: " + carga.getPeso() + ", Propietario: " + carga.getDueño() + ".");
                                    // Se Eliminan Las Cargas Descargas
                                    for (Contenedor cont:barco.getContenedores()){
                                        for(Carga charge:cont.getCarga()){
                                            if (charge.getCodCarga()==carga.getCodCarga()){
                                                index2.add(cont.getCarga().indexOf(charge));
                                            }
                                        }
                                        for (int d=(index2.size()-1); d>=0; d--){
                                            cont.setCargaActual(cont.getCargaActual()-cont.getCarga().get(index2.get(d)).getPeso());
                                            cont.getCarga().remove(cont.getCarga().get(index2.get(d)));
                                        }
                                        index2.clear();
                                    }
                                }
                            }
                            // Se Consolida la Carga Para Compactarla y Liberar Contenedores
                            // Se Vacian Todas las Cargas a una Lista Temporal
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
                            // Se Vuelven a Cargar los Contenedores
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
                            // Se Realiza Proceso de Cargue 
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
                                    if (carga.getPeso()>y){
                                        Carga carga1 = new Carga (carga.getCodCarga(), carga.getDueño(), carga.getDescripcion(), (carga.getPeso()-y), carga.getOrigen(), carga.getDestino());
                                        Carga carga2 = new Carga (carga.getCodCarga(), carga.getDueño(), carga.getDescripcion(), y, carga.getOrigen(), carga.getDestino());
                                        barco.getContenedores().get(f).getCarga().add(carga2);
                                        barco.getContenedores().get(f).setCargaActual(barco.getContenedores().get(f).getCapacidad());
                                        if ((barco.getContenedores().size()-1)==f){
                                            Contenedor cont1 = new Contenedor (barco.getCapacidad()/barco.getCapContendores());
                                            barco.getContenedores().add(cont1);
                                        }
                                        f+=1;
                                        barco.getContenedores().get(f).getCarga().add(carga1);
                                        barco.getContenedores().get(f).setCargaActual(carga1.getPeso());
                                    } else {
                                        Carga carga1 = new Carga (carga.getCodCarga(), carga.getDueño(), carga.getDescripcion(), carga.getPeso(), carga.getOrigen(), carga.getDestino());
                                        barco.getContenedores().get(f).getCarga().add(carga1);
                                        barco.getContenedores().get(f).setCargaActual(barco.getContenedores().get(f).getCargaActual()+carga.getPeso());
                                    }
                                }    
                            }
                            index.add(x);
                        }  
                    }
                    // Se Sacan 
                    for (int d=(index.size()-1); d>=0; d--){
                        barco.getRuta().getFechaA().remove(barco.getRuta().getFechaA().get(index.get(d)));
                        barco.getRuta().getPuerto().remove(barco.getRuta().getPuerto().get(index.get(d)));
                    }
                    index.clear();        
                }
            }
            System.out.println("\nChilerisimo :D");
        }
        
    }
    
   /**
    * Método que imprime la Lista de Puertos Existentes, Previamente Agregados.
    */
    static void listaPuertos(){
        int i = 0;
        for (Puerto x:puertosExistentes){
            i = i+1;
            System.out.println(i+".) " + x.getNombrePuerto());
        }
    }
    
   /**
    * Método que Responde a la Primer Consulta. Imprimer la Lista de Puertos Registrados
    * Por El Usuario Previamente.
    */
    static void c1(){
        int numero;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tPuertos Registrados: ");
        listaPuertos();
        System.out.println("\n-------------------------------------------------");
        System.out.println("Ingrese el numero del puerto que desea consultar...");
        numero=ingresarInt();
        System.out.println(puertosExistentes.get(numero-1));
    }
    
   /**
    * Método que Responde a la Segunda Consulta. Muestra la Cantidad de Contenedores Ya
    * Cargados en un Barco, Cuyo Destino es un Puerto Específico. 
    */
    static void c2(){
        int y = 0,x = 0;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tPuertos Existentes");
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
    
   /**
    * Método que Responde a la Tercera Consulta. Muestra La Ruta que Seguirá un Barco,
    * Indicado los Puertos y Contenedores que Actualmente hay que Desembarcar en cada uno de esos Puertos.
    */
    static void c3(){
        int i = 0, y = 0, z=0, f=0;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tBarcos Existentes");
        for (Barco x:barcosExistentes){
            i = i+1;
            System.out.println(i+".) " + x.getNombre());
        }
        while (y<=0||y>barcosExistentes.size()){
            System.out.println("¿Cual barco desea analizar?");
            y = ingresarInt();
            if (y<=0||y>barcosExistentes.size())
                System.out.println("Lo sentimos, ese Barco no Existe");          
        }
        System.out.println("\nRuta del Barco "+barcosExistentes.get(y-1).getNombre());
        // se imprimen todos los puertos que el barco va a visitar y en que fechas va a llegar
        for (Puerto puerto:barcosExistentes.get(y-1).getRuta().getPuerto()){
            f = barcosExistentes.get(y-1).getRuta().getPuerto().indexOf(puerto);
            System.out.println("Puerto: "+puerto.getNombrePuerto()+", Fecha: "+date_format.format(barcosExistentes.get(y-1).getRuta().getFechaA().get(f).getTime()));
            for (Contenedor cont:barcosExistentes.get(y-1).getContenedores()){
                // se lleva un contador de cuantos contenedores lleva el barco aun
                for (Carga carga:cont.getCarga()){
                    if (carga.getDestino().getNombrePuerto().equals(puerto.getNombrePuerto())){
                        z+=1;
                    }
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
    
   /**
    * Método que Permite y Valida el Ingreso de Una Fecha. Luego Instancia Dicha
    * Fecha y la Devuelve.
    * @return fecha Fecha Creada y Validada.
    */
    static Calendar ingresarFecha(){
        int dia, mes, año;
        Calendar fecha;
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
        fecha.set(año, (mes-1), dia, 0, 0, 0);
        return fecha;
    }
    
   /**
    * Método que Responde a la Cuarta Consulta. Muestra Cantidad de Barcos que Hay 
    * En un Puerto en un Momento Dado.
    */
    static void c4(){
        Calendar fecha; int x = 0, y;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tIngrese la Fecha a Analizar");
        fecha = ingresarFecha();
        System.out.println("\nPara la Fecha de "+ date_format.format(fecha.getTime())+" habran:");
        for (Puerto puerto:puertosExistentes){
            for (Barco barco:barcosExistentes){
                for (Calendar fecha1:barco.getRuta().getFechaA()){
                    y = barco.getRuta().getFechaA().indexOf(fecha1);
                    if ((barco.getRuta().getPuerto().get(y).getNombrePuerto().equals(puerto.getNombrePuerto()))&&(fecha1.equals(fecha))){
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
    
   /**
    * Método que Responde a la Quinta Consulta. Muestra La Programación de 
    * Arribos de Barcos a un Puerto en un Periodo de Tiempo Dado.
    */
    static void c5(){
        Calendar fecha1, fecha2; boolean x; int y;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tIngrese el Intervalo de Tiempo a Revisar");
        System.out.println("Fecha de Inicio:");
        fecha1 = ingresarFecha();
        do{
            System.out.println("\nFecha Final");
            fecha2 = ingresarFecha();
            if (!fecha1.before(fecha2))
                System.out.println("Lo sentimos, la fecha final debe ser posterior a la fecha inicial");
        }while(!fecha1.before(fecha2));
        
        for (Puerto puerto:puertosExistentes){
            System.out.println("Puerto: " + puerto.getNombrePuerto());
            x = false;
            for (Barco barco:barcosExistentes){
                for (Calendar fecha:barco.getRuta().getFechaA()){
                    y = barco.getRuta().getFechaA().indexOf(fecha);
                    if ((barco.getRuta().getPuerto().get(y).getNombrePuerto().equals(puerto.getNombrePuerto()))&&(!fecha.before(fecha1))&&(!fecha.after(fecha2))){
                        System.out.println("- Fecha: " + date_format.format(fecha.getTime()) + " - Barco: " + barco.getNombre());
                        x = true;
                    }
                }
            }
            if (!x){
                System.out.println("No habra Barco Alguno.");
            }
        }
    }
    
   /**
    * Método que Responde a la Sexta Consulta. Muestra Las Personas o Empresas a 
    * Quienes se Les Envía Carga en un Puerto Específico.
    */
    static void c6(){
        int y = 0;
        System.out.println("\n-------------------------------------------------");
        System.out.println("\tPuertos Existentes");
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
    
   /**
    * Metodo que Muestra el Menu de Consultas.
    */
    static void consultaInformacion(){
        int op; String letra;
        System.out.println("\n-------------------------------------------------");
        do {
            System.out.println("\t¿Que Informacion Desea Consultar?\n");
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
        do{
            System.out.println("Para Continuar Ingrese C (Continuar).");
            letra = teclado.nextLine();
            if (!letra.equalsIgnoreCase("C"))
                System.out.println("Lo Lamento, Ingrese C Para Continuar.");
        }while(!letra.equalsIgnoreCase("C"));
    }
    
   /**
    * Método que Muestra el Menu Principal del Programa
    */
    public static void main(String[] args){
        try {
             FileInputStream SP = new FileInputStream("SP-G2.dat");
             ObjectInputStream is = new ObjectInputStream(SP);
             barcosExistentes = (ArrayList<Barco>) is.readObject();
             puertosExistentes = (ArrayList<Puerto>) is.readObject();
             cargasExistentes = (ArrayList<Carga>) is.readObject();
             rutasExistentes = (ArrayList<Ruta>) is.readObject();
             is.close();
        } catch (Exception e) {
            
        }
        int opcion=0; boolean seguro = false; String op;
        System.out.println("\tBienvenido");
        while (opcion!=6 && !seguro){
           do{
               System.out.println("\n-------------------------------------------------");
               System.out.println("\tMenu\n");
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
                   if (puertosExistentes.size()<2){
                       System.out.println("Lo Lamento, Debe Ingresar Al Menos 2 Puertos Antes de Poder Ingresar un Barco.");
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
        try {
             FileOutputStream sp = new FileOutputStream("SP-G2.dat");
             ObjectOutputStream os = new ObjectOutputStream(sp);
             os.writeObject(barcosExistentes);
             os.writeObject(puertosExistentes);
             os.writeObject(cargasExistentes);
             os.writeObject(rutasExistentes);
             os.close();
        } catch (Exception e) {
            
        }
    }
    
}
