package simulador.colaprocesos;

// Importando cola
import simulador.cola.Cola;
// Importando proceso
import simulador.proceso.Proceso;

public class ColaPL {
    
    // atributo cola
    private Cola<Proceso> cola;

    // contructor para iniciar la cola
    public ColaPL() {
        this.cola = new Cola<>();
    }
    
    // meotodo para encolar e imprimir el estado
    public void encolarPL(Proceso proceso) {
        this.cola.encolar( proceso );
        this.imprimirEstado();
    }
    
    // metodo para obtener el proceso que esta
    // al frente de la cola
    public Proceso obtenerProcesoDeEnFrente() {
        return this.cola.getFrente();
    }
    
    // metodo para desencolar de la cola e imprimir el estado
    public Proceso desencolar() {
        Proceso procesoDesencolado = this.cola.desencolar();
        this.imprimirEstado();
        return procesoDesencolado;
    }
    
    // metodo para checar si la cola esta vacia
    public Boolean estaVacia() {
        return this.cola.estaVacia();
    }
    
    // metodo que imprime el estado de la cola PL,
    // llamando a la funcion de la cola
    public void imprimirEstado() {
        System.out.println("Cola [PL] ultimo->primero");
        this.cola.imprimirCola();
    }
}
