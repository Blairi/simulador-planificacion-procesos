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
//        ColaProcesos colaPL = leerProcesos();
//        int quantum = leerQuantum();

        ColaProcesos colaPL = new ColaProcesos();
        colaPL.encolarPLE(new Proceso(1, "P1", 100, 20, 0, 1, 1));
        colaPL.encolarPLE(new Proceso(2, "P2", 100, 8, 0, 1, 1));
        colaPL.encolarPLE(new Proceso(3, "P3", 100, 10, 0, 1, 1));
        colaPL.encolarPLE(new Proceso(4, "P4", 100, 13, 0, 1, 1));
        colaPL.encolarPLE(new Proceso(5, "P5", 100, 2, 0, 1, 1));
        colaPL.encolarPLE(new Proceso(6, "P6", 100, 9, 0, 1, 1));  
        int quantum = 4;

        //Algoritmo Round Robin
        int tiempoActual = 0;
        //Refiere a procesos completados
        int totalProcesos = 0;
        int sumaTiempoEspera = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEjecucion = 0;

        // Mientras haya procesos en la cola de procesos
        while (!colaPL.estaVacia()) {
            // Obtener el proceso de la cola de procesos
            Proceso procesoActual = colaPL.desencolar();
            //La pasamos a la cola de procesos listos para ejecucion
            //colaPL.encolar(procesoActual);
            
            // Si el proceso no ha sido ejecutado previamente
            if (procesoActual.gettiempoSubidaCPU() == 0) {
                procesoActual.tiempoSubidaCPU = tiempoActual;
            }

            // Calcular el tiempo de ejecución según el quantum y el tiempo restante del proceso
            int tiempoEjecucion = Math.min(procesoActual.getTiempoServicio(), quantum);
            
            // Avanzar el tiempo actual
            tiempoActual += tiempoEjecucion;
            
           
            // Restar el tiempo de ejecución del proceso
            procesoActual.setTiempoServicio(procesoActual.getTiempoServicio() - tiempoEjecucion);
            System.out.println("Proceso " + procesoActual.id + " ejecutado durante " + tiempoEjecucion + " unidades. Tiempo restante: " + procesoActual.tiempoServicio);
            // Si el proceso aún tiene tiempo restante
            if (procesoActual.getTiempoServicio() > 0) {
                // Volver a encolar el proceso en la cola de procesos
                colaPL.encolar(procesoActual);
            } else {
                // El proceso ha terminado
                System.out.println("Proceso " + procesoActual.id + " completado y no volverá a la cola. Tiempo sobrante " + Math.abs(procesoActual.tiempoServicio) + "\n");
                totalProcesos++;
                int tiempoFinalizacion = tiempoActual;
                int tiempoRespuesta = procesoActual.tiempoSubidaCPU - tiempoEjecucion;
                int tiempoEspera = (tiempoActual - procesoActual.tiempoServicio) -  procesoActual.tiempoLlegada - tiempoFinalizacion;
                int tiempoEje = tiempoFinalizacion - procesoActual.tiempoLlegada;
                
                // Sumar los tiempos para calcular el promedio
                sumaTiempoEjecucion += tiempoEje; // Tiempo de ejecución es igual al tiempo de respuesta en Round Robin
                sumaTiempoRespuesta += tiempoRespuesta;
                sumaTiempoEspera += tiempoEspera;
            }
            
             /* Mostrar los resultados de la simulación
            if (totalProcesos > 0) {
                System.out.println("\n--- Resultados de la simulación ---");
                System.out.println("Total de procesos completados: " + totalProcesos);
                System.out.println("Tiempo promedio de espera: " + ((double) sumaTiempoEspera / totalProcesos));
                System.out.println("Tiempo promedio de respuesta: " + ((double) sumaTiempoRespuesta / totalProcesos));
                System.out.println("Tiempo promedio de ejecución: " + ((double) sumaTiempoEjecucion / totalProcesos));
            }*/
        }
        //Crear un TextTable para mostrar los resultados de la simulación en forma de tabla
        
        String[] encabezados2 = {"Total de procesos", "Tiempo promedio de espera", "Tiempo promedio de respuesta", "Tiempo promedio de ejecución"};
        String[][] datos = {{String.valueOf(totalProcesos), String.format("%.2f", ((double) sumaTiempoEspera / totalProcesos)),
                String.format("%.2f", ((double) sumaTiempoRespuesta / totalProcesos)), String.format("%.2f", ((double) sumaTiempoEjecucion / totalProcesos))}};
        TextTable tablaResultados = new TextTable(encabezados2, datos);

        // Mostrar la tabla de resultados
        System.out.println("\nTabla de resultados:");
        System.out.println(tablaResultados);
    }
    
    private static ColaProcesos leerProcesos() {
        // Crear un objeto Scanner para leer datos de la consola
        Scanner scanner = new Scanner(System.in);
        
        // Definir los procesos con sus tamaños, tiempos de ejecución y tiempos de llegada
        System.out.println("================== SIMULADOR PLANIFICADOR DE PROCESOS ==================");
        System.out.println("Instrucciones");
        System.out.println("\tCada proceso que usted ingrese debe contar con valores para el tamaño, tiempo de servicio y tiempo de llegada");
        System.out.println("\tConsidere que el tamanio de memoria disponible es de 1024, por lo que ningun proceso puede ser mayor a esa cantidad\n");
        
        // Solicitar el número de procesos
        int numProcesos;
        //Creamos la cola de procesos listos (aquellos que estan en espera de subir a la CPU
        ColaProcesos colaPL = new ColaProcesos();
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
        String[][] procesosUsuario= new String[numProcesos][6];
        for ( int i = 1; i <= numProcesos; i++ ) {
            System.out.println("------------------------------------------");
            System.out.println("\nProceso " + i + ":");
            int tamanio, tiempoServicio, tiempoLlegada, id, prioridad;
            String nombre;
            //Inicializamos el tiempo en el que subio por primera vez a la CPU en 0
            int tiempoSubidaCPU = 0;

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
            
            do {
            
            System.out.print("Ingrese la prioridad del proceso: ");
            
            // Si el usuario ingresa un numero que no es entero
            while ( !scanner.hasNextInt() ) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            
            // Leemos el numero de procesos
            prioridad = scanner.nextInt();
            
            // Si el usuario pone 0 como numero de procesos, repetimos el ciclo
            if ( prioridad <= 0 ) {
                System.out.println("Error: La prioridad debe ser mayor que 0.");
            }
            } while (prioridad <= 0);
            
            // Llenamos la matriz para imprimirla al final
            procesosUsuario[i-1][0] = String.valueOf( i );
            procesosUsuario[i-1][1] = nombre;
            procesosUsuario[i-1][2] = String.valueOf( tamanio );
            procesosUsuario[i-1][3] = String.valueOf( tiempoServicio );
            procesosUsuario[i-1][4] = String.valueOf( tiempoLlegada );
            procesosUsuario[i-1][5] = String.valueOf( prioridad );
            
            // Insertamos el proceso a la cola
            colaPL.encolarPLE( new Proceso(i, nombre, tamanio, tiempoServicio, tiempoLlegada,prioridad,tiempoSubidaCPU) );       
        }
        
        // Mostrar procesos al usuario
        System.out.println("\nProcesos a simular: ");
        String []encabezados = {"id", "Proceso", "Tamanio", "Tservicio", "Tllegada", "Prioridad"};
        System.out.println( new TextTable(encabezados, procesosUsuario) );
 
        return colaPL;
    }
    
    public static int leerQuantum() {
        
        Scanner scanner = new Scanner(System.in);
        
        int quantum;
        
        // Pedir quantum
        do {
            
            System.out.print("Ingrese el quantum con el que desea trabajar: ");
            
            // Si el usuario ingresa un numero que no es entero
            while ( !scanner.hasNextInt() ) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            
            // Leemos el numero de procesos
            quantum = scanner.nextInt();
            
            // Si el usuario pone 0 como numero de procesos, repetimos el ciclo
            if ( quantum<= 0 ) {
                System.out.println("Error: El quantum debe ser mayor que 0.");
            }
            } while (quantum <= 0);

        scanner.close();
        
        return quantum;
    }

    
}

    

