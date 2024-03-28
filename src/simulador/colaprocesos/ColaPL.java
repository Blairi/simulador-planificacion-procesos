package simulador.colaprocesos;

import simulador.cola.Cola;
import simulador.proceso.Proceso;

public class ColaPL {
    
    private Cola<Proceso> cola;
    
    public ColaPL(){
        this.cola = new Cola<>();
    }
 
    public void encolarPL(Proceso proceso) {
        this.imprimirEstado();
        this.cola.encolar(proceso);
    }
    
    public Proceso desencolarPL() {
        Proceso procesoDesencolado =  this.cola.desencolar();
        this.imprimirEstado();
        return procesoDesencolado;
    }
    
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
    public Proceso getFrente() {
        return this.cola.getFrente();
    }
    
    public void imprimirEstado() {
        System.out.println("------ Cola Actual [PL] ultimo->primero ------");
        this.cola.imprimirCola();
        System.out.println("");
    }
    
}
