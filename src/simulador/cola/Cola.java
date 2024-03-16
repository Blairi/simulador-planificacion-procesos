package simulador.cola;

// Cola de tipo generica <T>, para manejar cualquier tipo de dato
public class Cola<T> {
    
    private Nodo<T> frente;
    private Nodo<T> detras;
    private int longitud;

    public Cola(){
        this.frente = null;
        this.detras = null;
        this.longitud = 0;
    }
    
    public Boolean estaVacia(){
        // Si el frente y detras son nulos, la cola esta vacia
        return (null == frente && null == detras);
    }

    public Boolean encolar(T valor) {
        
        Nodo<T> nodoNuevo = new Nodo<>();
        nodoNuevo.setValor(valor);
        
        if( this.estaVacia() ){
            this.frente = this.detras = nodoNuevo;
            return true;
        }
        
        this.detras.setNodoSiguiente(nodoNuevo);
        nodoNuevo.setNodoPrevio(this.detras);
        this.detras = nodoNuevo;
       
        this.longitud ++;
        return true;
    }
    
    public T desencolar(){
        if ( this.estaVacia() ) {
            return null;
        }
        
        Nodo<T> temporal = this.frente;
        T valor = temporal.getValor();
        
        if( temporal.getNodoSiguiente() != null ){
            temporal.getNodoSiguiente().setNodoPrevio(null);
            this.frente = temporal.getNodoSiguiente();
            temporal.setNodoSiguiente(null);
        }
        else {
            this.frente = this.detras = null;
        }
        
        this.longitud --;
        
        return valor;
    }
    
    public void imprimirCola(){
        
        Nodo<T> actual = this.detras;
        
        System.out.print("[");
        while( actual != null ) {
            System.out.print(" " + actual.getValor().toString() + " ");
            actual = actual.getNodoPrevio();
        }
        System.out.println("]");
    }
    
}
