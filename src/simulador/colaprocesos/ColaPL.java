package simulador.colaprocesos;

import simulador.cola.Cola;
import simulador.proceso.Proceso;

public class ColaProcesos {
    
    private int memoriaActual;
    private Cola<Proceso> cola;
    
    public ColaProcesos(){
        this.cola = new Cola<>();
        this.memoriaActual = 0;
    }
    
    public void encolarPLE(Proceso proceso) {
        
        if ( this.memoriaActual + proceso.getTamanio() <= 1024 ) {
            this.cola.encolar(proceso);
            this.memoriaActual += proceso.getTamanio();
            System.out.println("Subio el proceso " + proceso.getNombre() + 
                    " a la cola de procesos listos y restan " + (1024 - this.memoriaActual) + " unidades de memoria");
        }
        else{
            System.out.println("No hay suficiente memoria disponible " + "para subir el proceso " + proceso.getNombre()+ " a la cola de procesos listos");
        }
    }
    public void encolar(Proceso proceso) {
        
        
            this.cola.encolar(proceso);
            this.memoriaActual += proceso.getTamanio();   
    }
    
    public Proceso desencolar() {
        Proceso procesoDesencolado =  this.cola.desencolar();
        this.memoriaActual -= procesoDesencolado.getTamanio();
        return procesoDesencolado;
    }
    
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
}
