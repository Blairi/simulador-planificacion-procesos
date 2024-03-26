package simulador.roundrobin;

import simulador.colaprocesos.ColaProcesos;
import simulador.proceso.Proceso;

public class RoundRobin {

    private Proceso[] procesos;
    private ColaProcesos cola;
    private int quantum;
    private int tiempoActual;
    private int numProcesos;
    private int numProcesosCompletados;

    public RoundRobin(Proceso[] procesos, int quantum) {
        this.procesos = procesos;
        this.quantum = quantum;
        this.cola = new ColaProcesos();
        this.tiempoActual = 0;
        this.numProcesos = procesos.length;
        this.numProcesosCompletados = 0;
    }
    
    public void ejecutar() {
        
        while ( this.numProcesosCompletados < this.numProcesos ) {
            
            // Agregar los procesos llegados al tiempo actual a la cola
            for ( Proceso proceso : this.procesos ) {
                if ( proceso.getTiempoLlegada() <= this.tiempoActual && proceso.getTiempoServicio() > 0 ) {
                    this.cola.encolar( proceso );
                }
            }

            // Si hay procesos en la cola, ejecutar el proceso en la parte frontal de la cola
            if ( !this.cola.estaVacia() ) {
                Proceso procesoActual = this.cola.desencolar(); // Obtener el proceso de la parte frontal de la cola

                // Verificar si el proceso actual no es null
                if ( procesoActual != null ) {
                    int tiempoEjecucion = Math.min(quantum, procesoActual.getTiempoServicio()); // Tiempo de ejecución actual
                    this.tiempoActual += tiempoEjecucion;// Actualizar el tiempo actual
                    
                    int tiempoRestanteProceso = procesoActual.getTiempoServicio() - tiempoEjecucion;
                    procesoActual.setTiempoServicio( tiempoRestanteProceso ); // Actualizar el tiempo restante del proceso

                    // Imprimir información sobre la ejecución del proceso
                    System.out.println("Ejecutando " + procesoActual.getNombre() + " por " + tiempoEjecucion + " unidades de tiempo.");

                    // Verificar si el proceso se ha completado
                    if ( procesoActual.getTiempoServicio() == 0 ) {
                        this.numProcesosCompletados ++; // Incrementar el contador de procesos completados
                        System.out.println(procesoActual.getNombre() + " completado en el tiempo " + this.tiempoActual);
                    } else {
                        this.cola.encolar(procesoActual); // Volver a agregar el proceso a la cola si no se ha completado
                    }
                }
            } else {
                this.tiempoActual++; // Si no hay procesos en la cola, avanzar en el tiempo
            }
        }
        
    }
}

