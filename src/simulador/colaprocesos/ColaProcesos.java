package simulador.colaprocesos;
// Importamos la clase Cola
import simulador.cola.Cola;
// Importamos la clase Proceso
import simulador.proceso.Proceso;

public class ColaProcesos {
    // Variable para almacenar la memoria actual ocupada
    private int memoriaActual;
    // Cola de procesos
    private Cola<Proceso> cola;
    // Constructor de la clase ColaProcesos
    public ColaProcesos(){
        // Inicializamos la cola de procesos y la memoria actual
        this.cola = new Cola<>();
        this.memoriaActual = 0;
    }
    // Método para encolar un proceso en la cola de procesos listos para ejecucion (PLE)
    public void encolarPLE(Proceso proceso) {
        // Verificamos si hay suficiente memoria disponible para el proceso
        if ( this.memoriaActual + proceso.getTamanio() <= 1024 ) {
            // Si hay suficiente memoria, encolamos el proceso
            this.cola.encolar(proceso);
            this.memoriaActual += proceso.getTamanio();
            // Mostramos un mensaje indicando que el proceso se ha añadido a la cola de procesos listos para ejecucion
            System.out.println("Subio el proceso " + proceso.getNombre() + 
                    " a la cola de procesos listos y restan " + (1024 - this.memoriaActual) + " unidades de memoria");
        }
        else{
            // Si no hay suficiente memoria, mostramos un mensaje de error
            System.out.println("No hay suficiente memoria disponible " + "para subir el proceso " + proceso.getNombre()+ " a la cola de procesos listos");
        }
        
        this.cola.imprimirCola();
    }
     // Método para encolar un proceso en la cola
    public void encolar(Proceso proceso) {
            // Simplemente encolamos el proceso sin verificar la memoria
            this.cola.encolar(proceso);
            this.memoriaActual += proceso.getTamanio();   
            this.imprimirEstado();
    }
    // Método para desencolar un proceso de la cola
    public Proceso desencolar() {
        // Desencolamos un proceso y actualizamos la memoria actual
        Proceso procesoDesencolado =  this.cola.desencolar();
        this.memoriaActual -= procesoDesencolado.getTamanio();
        
        this.imprimirEstado();
        return procesoDesencolado;
    }
     // Método para verificar si la cola está vacía
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
    public void imprimirEstado() {
        System.out.println("\nCola actual ultimo->primero");
        this.cola.imprimirCola();
    }
    
}
