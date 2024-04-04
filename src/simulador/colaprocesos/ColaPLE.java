package simulador.colaprocesos;

// importando cola
import simulador.cola.Cola;
// importando proceso
import simulador.proceso.Proceso;

public class ColaPLE {
    // atributo para ver cuanto memoria disponible hay
    private int memoriaActual;
    // atributo cola
    private Cola<Proceso> cola;
    // constante para manejar la memoria disponible
    private final int MEMORIA_DISPONIBLE = 1024;

    // cremos la instancia de la cola e iniciamos la memoria en 0
    public ColaPLE() {
        this.cola = new Cola<>();
        this.memoriaActual = 0;
    }
    
    // metodo para encolar un proceso
    public void encolarPLE(Proceso proceso) {
        // si hay suficiente memoria en la cola PLE...
        if ( this.memoriaActual + proceso.getTamanio() <= this.MEMORIA_DISPONIBLE ) {
            
            this.cola.encolar(proceso );
            
            // actualizamos la memoria actual
            this.memoriaActual += proceso.getTamanio();
            
            // imprimimos mensaje para el usuario
            System.out.println("Subio el proceso " + proceso.getNombre() + 
                    " a la cola de procesos listos y restan " + (this.MEMORIA_DISPONIBLE - this.memoriaActual) + " unidades de memoria");
        }
        // si no hay espacio en la memoria
        else {
            // imprimimos mensaje para el usuario
            System.out.println("No hay suficiente memoria disponible para subir el proceso " 
                    + proceso.getNombre()
                    + " a la cola de procesos listos");
        }
        this.imprimirEstado();
    }
    
    // metodo que verifica si hay suficiente espacio en la memoria
    public Boolean tieneMemoria(int tamanioProceso) {
        // hay suficiente memoria en la cola PLE?
        return this.memoriaActual + tamanioProceso <= this.MEMORIA_DISPONIBLE;
    }
    
    // metodo para desencolar 
    public Proceso desencolar() {
        // desencolamos el proceso
        Proceso procesoDesencolado =  this.cola.desencolar();
        // actualizamos la memoria actual
        this.memoriaActual -= procesoDesencolado.getTamanio();
        // imprimimos el estado de la cola PLE
        this.imprimirEstado();
        // retornamos el proceso desencolado
        return procesoDesencolado;
    }
    
    // metodo para verificar si la cola esta vacia
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
    // metodo que imprime el estado de la cola PLE,
    // llamando a la funcion de la cola
    public void imprimirEstado() {
        System.out.println("Cola [PLE] ultimo->primero");
        this.cola.imprimirCola();
    }
}
