package ui;
import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] ar_precios;
    public static int[] ar_unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+ ar_precios.length +" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        ar_precios = new double[referencias];
        ar_unidades = new int[referencias];

    }

    /**
     * Descripcion: Este metodo se encarga de solicitar la cantidad de unidades y los precios de cada una de las referencias, guardandolos
     * en los arreglos de cada uno.
     * pre: El scanner debe estar inicializado.
     * pre: Los arreglos ar_precios y ar_unidades deben estar inicializados.
     * pos: Los datos deben quedar ingresados en los arreglos correspondientes a cada uno. 
     * 
     */
    
    public static void solicitarDatos(){        


        for(int i=0;i<ar_precios.length;i++){

        System.out.println("Digite el numero de unidades del producto " + (i+1) + ".");
        int unidades_product = reader.nextInt();

        System.out.println("Digite el precio del producto " + (i+1) + ".");
        double precio = reader.nextDouble();
               
        ar_precios[i]=precio; //escribe en el arreglo los precios
                
        ar_unidades[i]=unidades_product; //escribe en el arreglo la cantidad de unidades
                
            }
        
    }

    /**
     * Descripcion:Este metodo se encarga de calcular el total de unidades que se vendieron en el dia, recorriendo el arreglo ar_unidades
     * y sumando los valores de cada uno de los espacios del arreglo
     * pre: el arreglo ar_unidades debe estar inicializado
     * pre: el arreglo ar_unidades no puede estar vacio 
     * 
     * @return double suma_unidades Se devuelve el total de ventas como un valor entero
     */

    public static int calcularTotalUnidadesVendidas(){

        int suma_unidades=0;

        for(int i=0;i<ar_precios.length;i++){

        suma_unidades=suma_unidades + ar_unidades[i];//hace la suma de todas las unidades
        }

        return suma_unidades;

    }

    /**
     * Descripcion: El metodo debe calcular el precio promedio de las unidades vendidas en el dia.
     * pre: El arreglo precios debe estar inicializado
     * pre: el arreglo no puede estar vacio
     * @return double promedio_precios El metodo devolvera el calculo del precio promedio como un valor entero 
     */

    public static double calcularPrecioPromedio(){

        double suma_precio=0;

         for(int i=0;i<ar_precios.length;i++){

        suma_precio=suma_precio + ar_precios[i];//hace la suma de todos los precios
        }
        double promedio_precios= suma_precio/ ar_precios.length;//saca el promedio


        return promedio_precios;

    }


    /**
     * Descripcion: Calculara el dinero recaudado en las ventas totales durante el dia recorriendo los 2 arreglos.
     * pre: Los arreglos unidades y precios deben estar inicializados
     * pre: el arreglo no puede estar vacio  
     * @return double ventas_total El metodo devolvera el total de dinero recaudado durante todo el dia 
     */

    public static double calcularVentasTotales(){

    double ventas_total=0;

        for(int i=0;i<ar_precios.length;i++){

        ventas_total=ar_unidades[i] * ar_precios[i];//calcula las ventas totales
        }

        return ventas_total;

    }

/**
     * Descripcion: el programa calculara el numero de referencias de productos que superaron un limite minimo de ventas, multiplicando
     * los valores de precios y unidades, posteriormente evaluara cuantos superaron el limite o no.
     * pre: los arreglos precios y unidades deben estar inicializados
     * pre: los arreglos no pueden estar vacios
     * @param double limite El limite de ventas dado por el usuario 
     * @return int ref_limit El metodo devolvera el numero de referencias que han superado el limite minimo.
     */

    public static int consultarReferenciasSobreLimite(double limite){

    double ventas=0;
    int ref_limit=0;
    

        for(int i=0;i<ar_precios.length;i++){
        
        ventas=ar_unidades[i] * ar_precios[i];//calcula las ventas totales
        
        if (ventas>limite){
        
        ref_limit= ref_limit + 1;

        }
        }


        return ref_limit;

    }

}
