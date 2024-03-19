package simulador;
import java.util.Scanner;

import simulador.colaprocesos.ColaProcesos;
import simulador.proceso.Procesov2;
import simulador.roundrobin.RoundRobin;

public class RoundRobinMetodov2 {

    // Función para simular la carga de procesos a la memoria
    public static void simulateMemoryAllocation(int memorySize, Procesov2[] procesos) {
        // Mostrar el tamaño de memoria disponible para el usuario
        System.out.println("Tamaño de memoria disponible para el usuario: " + memorySize);

        // Mostrar la carga de los procesos a la memoria
        ColaProcesos cola = new ColaProcesos();
        for ( Procesov2 proceso : procesos ) {
            cola.encolar( proceso );
        }
    }

    public static void main(String[] args) {
        
        // Crear un objeto Scanner para leer datos de la consola
        Scanner scanner = new Scanner(System.in);
        // Definir los procesos con sus tamaños, tiempos de ejecución y tiempos de llegada
        System.out.println("================== SIMULADOR PLANIFICADOR DE PROCESOS==================");
        System.out.println("Instrucciones");
        System.out.println("\tCada proceso que usted ingrese debe contar con valores para el tamaño, tiempo de servicio y tiempo de llegada");
        System.out.println("\tConsidere que el tamaño de memoria disponible es de 1024, por lo que ningun proceso puede ser mayor a esa cantidad\n");
        
        // Solicitar el número de procesos
        int numProcesos;
        do {
            System.out.print("Ingrese el numero de procesos: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            numProcesos = scanner.nextInt();
            if (numProcesos <= 0) {
                System.out.println("Error: El numero de procesos debe ser mayor que 0.");
            }
        } while (numProcesos <= 0);

        // Solicitar los datos para cada proceso
        Procesov2[] procesos = new Procesov2[numProcesos];
        for (int i = 1; i <= numProcesos; i++) {
            System.out.println("------------------------------------------");
            System.out.println("\nProceso " + i + ":");
            int tamaño, tiempoServicio, tiempoLlegada,id;
            String nombre;

            // Solicitar el tamaño del proceso
            do {
                System.out.print("Ingrese el tamanio del proceso (menor o igual a 1024): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                tamaño = scanner.nextInt();
                if (tamaño <= 0 || tamaño > 1024) {
                    System.out.println("Error: El tamanio del proceso debe ser mayor que 0 y menor o igual a 1024.");
                }
            } while (tamaño <= 0 || tamaño > 1024);

            // Solicitar el tiempo de servicio del proceso
            do {
                System.out.print("Ingrese el tiempo de servicio del proceso (mayor o igual a 0): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                tiempoServicio = scanner.nextInt();
                if (tiempoServicio < 0) {
                    System.out.println("Error: El tiempo de servicio del proceso debe ser mayor o igual a 0.");
                }
            } while (tiempoServicio < 0);

            // Solicitar el tiempo de Llegada del proceso
            do {
                System.out.print("Ingrese el tiempo de llegada del proceso (mayor o igual a 0): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                tiempoLlegada = scanner.nextInt();
                if (tiempoLlegada < 0) {
                    System.out.println("Error: El tiempo de llegada del proceso debe ser mayor o igual a 0.");
                }
            } while (tiempoLlegada < 0);

            // Mostrar los datos ingresados para el proceso
            System.out.print("Ingrese el nombre del proceso: ");
            nombre = scanner.next();
            System.out.println("------------------------------------------");
            System.out.println("id: " + i);
            id = i;
            System.out.println("Nombre: "+ nombre);
            System.out.println("Tamaño del proceso: " + tamaño);
            System.out.println("Tiempo de servicio del proceso: " + tiempoServicio);
            System.out.println("Tiempo de Llegada del proceso: " + tiempoLlegada);
            
            procesos[i] = new Procesov2(id ,nombre, tamaño, tiempoServicio, tiempoLlegada);
            
        }
       
        scanner.close();
        
        
        /*Proceso[] procesos = {
            new Proceso(1, "P1", 100, 20, 0),
            new Proceso(2, "P2", 100, 8, 0),
            new Proceso(3, "P3", 100, 10, 0),
            new Proceso(4, "P4", 100, 13, 0),
            new Proceso(5, "P5", 100, 2, 0),
            new Proceso(6, "P6", 100, 9, 0)
        };*/

        int memorySize = 1024; // Tamaño de memoria disponible para el usuario

        // Llamar a la función para simular la carga de procesos a la memoria
        simulateMemoryAllocation(memorySize, procesos);

        // Definir el quantum para el algoritmo Round Robin
        int quantum = 4;

        // Llamar para simular la planificación de procesos
        RoundRobin roundRobin = new RoundRobin(procesos, quantum);
        roundRobin.ejecutar();
    }

}

