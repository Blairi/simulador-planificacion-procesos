package simulador.cola;

public class Nodo<T> {

    private Nodo<T> nodoPrevio;
    private Nodo<T> nodoSiguiente;
    // Tipo de dato generico (para guardar cualquier tipo de dato en el nodo)
    private T valor;
    
    public Nodo(){}

    public Nodo(Nodo<T> nodoPrevio, Nodo<T> nodoSiguiente, T valor) {
        this.nodoPrevio = nodoPrevio;
        this.nodoSiguiente = nodoSiguiente;
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public Nodo<T> getNodoPrevio() {
        return nodoPrevio;
    }

    public void setNodoPrevio(Nodo<T> nodoPrevio) {
        this.nodoPrevio = nodoPrevio;
    }

    public Nodo<T> getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(Nodo<T> nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }

    @Override
    public String toString() {
        return "Nodo{" + "nodoPrevio=" + nodoPrevio + ", nodoSiguiente=" + nodoSiguiente + ", valor=" + valor + '}';
    }
    
}
