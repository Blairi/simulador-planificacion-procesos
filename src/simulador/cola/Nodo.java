package simulador.cola;

public class Nodo<T> {
    // Definimos los nodos previo y siguiente
    // Nodo anterior en la lista enlazada
    private Nodo<T> nodoPrevio;
    // Nodo siguiente en la lista enlazada
    private Nodo<T> nodoSiguiente;
    // Tipo de dato generico (para guardar cualquier tipo de dato en el nodo)
    private T valor;
    
   
    //Constructor vacio usado para encolar
    public Nodo(){}
    
    //Constructor de nodo
    public Nodo(Nodo<T> nodoPrevio, Nodo<T> nodoSiguiente, T valor) {
       
        // Inicializamos los atributos del nodo
        this.nodoPrevio = nodoPrevio;
        this.nodoSiguiente = nodoSiguiente;
        //Contiene la referencia al proceso
        this.valor = valor;
    }
    
    
    // Métodos de acceso para el valor del nodo
    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    // Métodos de acceso para el nodo previo
    public Nodo<T> getNodoPrevio() {
        return nodoPrevio;
    }

    public void setNodoPrevio(Nodo<T> nodoPrevio) {
        this.nodoPrevio = nodoPrevio;
    }
    // Métodos de acceso para el nodo siguiente
    public Nodo<T> getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(Nodo<T> nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
    // Método toString para representar el nodo como una cadena de texto
    @Override
    public String toString() {
        return "Nodo{" + "nodoPrevio=" + nodoPrevio + ", nodoSiguiente=" + nodoSiguiente + ", valor=" + valor + '}';
    }
    
}
