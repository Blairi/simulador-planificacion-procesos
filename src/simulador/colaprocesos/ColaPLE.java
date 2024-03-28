package simulador.colaprocesos;

import simulador.cola.Cola;
import simulador.proceso.Proceso;

public class ColaPLE {
    
    private Cola<Proceso> cola;
    private int memoriaActual;
    
    public ColaPLE(){
        this.cola = new Cola<>();
        this.memoriaActual = 0;
    }
    
    public Boolean encolarPLE(Proceso proceso) {
        if ( this.memoriaActual + proceso.getTamanio() <= 1024 ) {
            System.out.println("Se va encolar en PLE a "+proceso);
            this.cola.encolar(proceso);
            this.memoriaActual += proceso.getTamanio();
            System.out.println("Subio el proceso " + proceso.getNombre() + 
                    " a la cola de procesos listos y restan " + (1024 - this.memoriaActual) + " unidades de memoria");
            this.imprimirEstado();
            return true;
        }
        
        System.out.println("No hay suficiente memoria disponible " + "para subir el proceso " + proceso.getNombre()+ " a la cola de procesos listos");
        return false;
    }
    
    public Proceso desencolarPLE() {
        Proceso procesoDesencolado =  this.cola.desencolar();
        this.memoriaActual -= procesoDesencolado.getTamanio();
        this.imprimirEstado();
        return procesoDesencolado;
    }
    
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
    public void imprimirEstado() {
        System.out.println("------ Cola [PLE] Actual ultimo->primero ------");
        this.cola.imprimirCola();
        System.out.println("");
    }

    public int getMemoriaActual() {
        return memoriaActual;
    }
    
}
