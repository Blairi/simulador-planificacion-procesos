package simulador.colaprocesos;

import simulador.cola.Cola;
import simulador.proceso.Proceso;

public class ColaPL {
    
    private Cola<Proceso> cola;

    public ColaPL() {
        this.cola = new Cola<>();
    }
    
    public void encolarPL(Proceso proceso) {
        this.cola.encolar( proceso );
        this.imprimirEstado();
    }
    
    public Proceso obtenerProcesoDeEnFrente() {
        return this.cola.getFrente();
    }
    
    public Proceso desencolar() {
        Proceso procesoDesencolado = this.cola.desencolar();
        this.imprimirEstado();
        return procesoDesencolado;
    }
    
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
            
    public void imprimirEstado() {
        System.out.println("Cola [PL] ultimo->primero");
        this.cola.imprimirCola();
    }
}
