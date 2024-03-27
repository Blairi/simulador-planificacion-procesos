package tmpRR; // Paquete del proyecto

import java.util.HashMap; // Importar la clase HashMap del paquete java.util
import java.util.Map; // Importar la clase Map del paquete java.util

// Definición de la clase principal RoundRobinSimulacion
public class RoundRobinSimulacion {
    static class Nodo {
        int id;
        int tiempoRestante;
        Nodo siguiente;
        int tiempoInicio; // Nuevo campo para registrar el tiempo de inicio de ejecución del proceso

        public Nodo(int id, int tiempoRestante) {
            this.id = id;
            this.tiempoRestante = tiempoRestante;
            this.siguiente = null;
            this.tiempoInicio = 0; // Inicialmente establecemos el tiempo de inicio a -1
        }
    }

    static class Cola {
        Nodo frente, fin;

        public Cola() {
            this.frente = this.fin = null;
        }

        void encolar(int id, int tiempo) {
            Nodo nuevoNodo = new Nodo(id, tiempo);
            if (this.fin == null) {
                this.frente = this.fin = nuevoNodo;
                return;
            }
            this.fin.siguiente = nuevoNodo;
            this.fin = nuevoNodo;
        }

        Nodo desencolar() {
            if (this.frente == null) {
                return null;
            }
            Nodo temp = this.frente;
            this.frente = this.frente.siguiente;
            if (this.frente == null) {
                this.fin = null;
            }
            return temp;
        }

        boolean estaVacia() {
            return frente == null;
        }
    }

    public static void ejecutarRoundRobin(Cola cola, int quantum) {
        Map<Integer, Integer> tiempoLlegada = new HashMap<>();
        int tiempoActual = 0;
        //Se tiene que definir dinamicamente
        int totalProcesos = 6;
        int sumaTiempoEspera = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEjecucion = 0;
        int tiempoEsperaMaximo = 0; // Variable para mantener el tiempo de espera máximo en la simulación

        while (!cola.estaVacia()) {
            Nodo procesoActual = cola.desencolar();
            if (!tiempoLlegada.containsKey(procesoActual.id)) {
                tiempoLlegada.put(procesoActual.id, tiempoActual);
            }

            // Calculamos el tiempo de espera del proceso
            int tiempoEspera = Math.max(0, tiempoEsperaMaximo - tiempoLlegada.get(procesoActual.id) - procesoActual.tiempoRestante);
            sumaTiempoEspera += tiempoEspera;

            // Actualizamos el tiempo de espera máximo si es necesario
            tiempoEsperaMaximo = Math.max(tiempoEsperaMaximo, tiempoActual);

            // Calculamos el tiempo de respuesta del proceso
            int tiempoRespuesta = tiempoActual - tiempoLlegada.get(procesoActual.id);
            sumaTiempoRespuesta += tiempoRespuesta;

            // Calculamos el tiempo de ejecución del proceso
            int tiempoEjecucion = tiempoActual - tiempoLlegada.get(procesoActual.id);
            sumaTiempoEjecucion += tiempoEjecucion;

            // Ejecutamos el proceso durante un tiempo igual al quantum o el tiempo restante del proceso, lo que sea menor
            tiempoEjecucion = Math.min(procesoActual.tiempoRestante, quantum);
            tiempoActual += tiempoEjecucion;
            procesoActual.tiempoRestante -= tiempoEjecucion;

            System.out.println("Proceso " + procesoActual.id + " ejecutado durante " + tiempoEjecucion + " unidades. Tiempo restante: " + procesoActual.tiempoRestante);

            if (procesoActual.tiempoRestante > 0) {
                cola.encolar(procesoActual.id, procesoActual.tiempoRestante);
            } else {
                System.out.println("Proceso " + procesoActual.id + " completado en el tiempo " + tiempoActual);
                totalProcesos++;
            }
        }

        mostrarPromedios(totalProcesos, sumaTiempoEspera, sumaTiempoRespuesta, sumaTiempoEjecucion);
    }

    private static void mostrarPromedios(int totalProcesos, int sumaTiempoEspera, int sumaTiempoRespuesta, int sumaTiempoEjecucion) {
        if (totalProcesos > 0) {
            System.out.println("\n--- Promedios ---");
            System.out.println("Tiempo promedio de espera: " + ((double) sumaTiempoEspera / totalProcesos));
            System.out.println("Tiempo promedio de respuesta: " + ((double) sumaTiempoRespuesta / totalProcesos));
            System.out.println("Tiempo promedio de ejecución: " + ((double) sumaTiempoEjecucion / totalProcesos));
        }
    }

    public static void main(String[] args) {
        Cola cola = new Cola();
        cola.encolar(1, 8);
        cola.encolar(2, 5);
        cola.encolar(3, 10);
        cola.encolar(4, 7);
        cola.encolar(5, 2);
        cola.encolar(6, 12);

        int quantum = 4;
        ejecutarRoundRobin(cola, quantum);
    }
}




