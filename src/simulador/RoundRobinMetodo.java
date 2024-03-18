package simulador;

import simulador.colaprocesos.ColaProcesos;
import simulador.proceso.Proceso;
import simulador.roundrobin.RoundRobin;

public class RoundRobinMetodo {

    // Función para simular la carga de procesos a la memoria
    public static void simulateMemoryAllocation(int memorySize, Proceso[] procesos) {
        // Mostrar el tamaño de memoria disponible para el usuario
        System.out.println("Tamaño de memoria disponible para el usuario: " + memorySize + "ky");

        // Mostrar la carga de los procesos a la memoria
        ColaProcesos cola = new ColaProcesos();
        for ( Proceso proceso : procesos ) {
            cola.encolar( proceso );
        }
    }

    public static void main(String[] args) {
        // Definir los procesos con sus tamaños, tiempos de ejecución y tiempos de llegada
        Proceso[] procesos = {
            new Proceso(1, "P1", 200, 5, 0),
            new Proceso(2, "P2", 300, 4, 1),
            new Proceso(3, "P3", 150, 6, 2),
            new Proceso(4, "P4", 500, 3, 3),
            new Proceso(5, "P5", 100, 2, 4),
            new Proceso(6, "P6", 350, 1, 5)
        };

        int memorySize = 1024; // Tamaño de memoria disponible para el usuario

        // Llamar a la función para simular la carga de procesos a la memoria
        simulateMemoryAllocation(memorySize, procesos);

        // Definir el quantum para el algoritmo Round Robin
        int quantum = 2;

        // Llamar para simular la planificación de procesos
        RoundRobin roundRobin = new RoundRobin(procesos, quantum);
        roundRobin.ejecutar();
    }

}

