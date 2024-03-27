package simulador.colaprocesos;

import simulador.cola.Cola;
import simulador.proceso.Proceso;

public class ColaProcesos {
    
    private int memoriaActual;
    //ColaPL es la cola de procesos listos (aquellos que estan en espera para subir a la memoria (no caben)
    private Cola<Proceso> colaPL;
    //Cola PLE es la cola de procesos que si alcanzaron espacio en la memoria (procesos listos para ejecucion)
    private Cola<Proceso> colaPLE;
  
    
    public ColaProcesos(){
        this.colaPL = new Cola<>();
        this.colaPLE = new Cola<>();
        this.memoriaActual = 0;
    }
    
    public void encolar(Proceso proceso) {
        
        if ( this.memoriaActual + proceso.getTamanio() <= 1024 ) {
            this.colaPLE.encolar(proceso);
            this.memoriaActual += proceso.getTamanio();
            System.out.println("Subio el proceso a la cola de procesos listos para ejecucion " + proceso.getNombre() + 
                    " y restan " + (1024 - this.memoriaActual) + " unidades de memoria");
        }
        else{
            System.out.println("No hay suficiente memoria disponible "
                    + "para subir el proceso a la cola de procesos listos para ejeucion " + proceso.getNombre());
            this.colaPL.encolar(proceso);
            System.out.println("Subio el proceso a la cola de procesos listos (en espera)");
        }
    }
    
    public Proceso desencolarPL() {
        Proceso procesoDesencolado =  this.colaPL.desencolar();
        this.memoriaActual -= procesoDesencolado.getTamanio();
        return procesoDesencolado;
    }
    public Proceso desencolarPLE() {
        Proceso procesoDesencolado =  this.colaPLE.desencolar();
        return procesoDesencolado;
    }
    
    public Boolean estaVaciaPL() {
        return this.colaPL.estaVacia();
    }
    public Boolean estaVaciaPLE() {
        return this.colaPLE.estaVacia();
    }
    
}
