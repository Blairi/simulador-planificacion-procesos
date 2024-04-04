package simulador.colaprocesos;

import simulador.cola.Cola;
import simulador.proceso.Proceso;

public class ColaPLE {
    private int memoriaActual;
    private Cola<Proceso> cola;
    private final int MEMORIA_DISPONIBLE = 1024;

    public ColaPLE() {
        this.cola = new Cola<>();
        this.memoriaActual = 0;
    }
    
    public void encolarPLE(Proceso proceso) {
        if ( this.memoriaActual + proceso.getTamanio() <= this.MEMORIA_DISPONIBLE ) {
            this.cola.encolar(proceso );
            this.memoriaActual += proceso.getTamanio();
            System.out.println("Subio el proceso " + proceso.getNombre() + 
                    " a la cola de procesos listos y restan " + (this.MEMORIA_DISPONIBLE - this.memoriaActual) + " unidades de memoria");
        }
        else {
            System.out.println("No hay suficiente memoria disponible para subir el proceso " 
                    + proceso.getNombre()
                    + " a la cola de procesos listos");
        }
        this.imprimirEstado();
    }
    
    public Boolean tieneMemoria(int tamanioProceso) {
        return this.memoriaActual + tamanioProceso <= this.MEMORIA_DISPONIBLE;
    }
    
    public Proceso desencolar() {
        Proceso procesoDesencolado =  this.cola.desencolar();
        this.memoriaActual -= procesoDesencolado.getTamanio();
        this.imprimirEstado();
        return procesoDesencolado;
    }
    
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
    public void imprimirEstado() {
        System.out.println("Cola [PLE] ultimo->primero");
        this.cola.imprimirCola();
    }
}
