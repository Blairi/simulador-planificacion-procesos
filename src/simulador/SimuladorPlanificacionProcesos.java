package simulador;

import java.util.Scanner;
import jtexttable.TextTable;
import simulador.colaprocesos.ColaProcesos;
import simulador.proceso.Proceso;

public class SimuladorPlanificacionProcesos {

    public static void main(String[] args) {
       
        /*
        Para esta parte del codigo leemos la entradas del usuario,
        con algunas validaciones para que el programa funcione bien
        */
        
        // Crear un objeto Scanner para leer datos de la consola
        Scanner scanner = new Scanner(System.in);
        
        // Definir los procesos con sus tamaños, tiempos de ejecución y tiempos de llegada
        System.out.println("================== SIMULADOR PLANIFICADOR DE PROCESOS ==================");
        System.out.println("Instrucciones");
        System.out.println("\tCada proceso que usted ingrese debe contar con valores para el tamaño, tiempo de servicio y tiempo de llegada");
        System.out.println("\tConsidere que el tamaño de memoria disponible es de 1024, por lo que ningun proceso puede ser mayor a esa cantidad\n");
        
        // Solicitar el número de procesos
        int numProcesos;
        ColaProcesos procesosListos = new ColaProcesos();
        do {
            
            System.out.print("Ingrese el numero de procesos: ");
            
            // Si el usuario ingresa un numero que no es entero
            while ( !scanner.hasNextInt() ) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            
            // Leemos el numero de procesos
            numProcesos = scanner.nextInt();
            
            // Si el usuario pone 0 como numero de procesos, repetimos el ciclo
            if ( numProcesos <= 0 ) {
                System.out.println("Error: El numero de procesos debe ser mayor que 0.");
            }
        } while (numProcesos <= 0);

        // Solicitar los datos para cada proceso
        
        // Arreglo temporal, solo para imprimir la entrada
        String[][] procesosUsuario= new String[numProcesos][5];
        for ( int i = 1; i <= numProcesos; i++ ) {
            System.out.println("------------------------------------------");
            System.out.println("\nProceso " + i + ":");
            int tamanio, tiempoServicio, tiempoLlegada, id;
            String nombre;

            // Solicitar el tamanio del proceso
            do {
                System.out.print("Ingrese el tamanio del proceso (menor o igual a 1024): ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                
                tamanio = scanner.nextInt();
                
                if ( tamanio <= 0 || tamanio > 1024 ) {
                    System.out.println("Error: El tamanio del proceso debe ser mayor que 0 y menor o igual a 1024.");
                }
            } while ( tamanio <= 0 || tamanio > 1024 );

            // Solicitar el tiempo de servicio del proceso
            do {
                System.out.print("Ingrese el tiempo de servicio del proceso (mayor o igual a 0): ");
                
                while ( !scanner.hasNextInt() ) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                
                tiempoServicio = scanner.nextInt();
                
                if ( tiempoServicio < 0 ) {
                    System.out.println("Error: El tiempo de servicio del proceso debe ser mayor o igual a 0.");
                }
            } while ( tiempoServicio < 0 );

            // Solicitar el tiempo de Llegada del proceso
            do {
                System.out.print("Ingrese el tiempo de llegada del proceso (mayor o igual a 0): ");
                
                while ( !scanner.hasNextInt() ) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                
                tiempoLlegada = scanner.nextInt();
                
                if ( tiempoLlegada < 0 ) {
                    System.out.println("Error: El tiempo de llegada del proceso debe ser mayor o igual a 0.");
                }
            } while ( tiempoLlegada < 0 );

            System.out.print("Ingrese el nombre del proceso: ");
            nombre = scanner.next();
            
            // Llenamos la matriz para imprimirla al final
            procesosUsuario[i-1][0] = String.valueOf( i );
            procesosUsuario[i-1][1] = nombre;
            procesosUsuario[i-1][2] = String.valueOf( tamanio );
            procesosUsuario[i-1][3] = String.valueOf( tiempoServicio );
            procesosUsuario[i-1][4] = String.valueOf( tiempoLlegada );
            
            // Insertamos el proceso a la cola
            procesosListos.encolar( new Proceso(i, nombre, tamanio, tiempoServicio, tiempoLlegada) );
        }       
        scanner.close();
        
        // Mostrar procesos al usuario
        System.out.println("\nProcesos a simular: ");
        String []encabezados = {"id", "Proceso", "Tamanio", "Tservicio", "Tllegada"};
        System.out.println( new TextTable(encabezados, procesosUsuario) );
        
        /*
            Simular memoria 
        */
        
        /*
            Simular RR
        */
        
        // Pedir quantum
        
        // Crear una instancia de RR y pasarle la cola de procesos listos 
        // TODO: modificar la clase RR, en vez de un arrreglo, que sea una colaProcesosListos

        
    }
    
}
