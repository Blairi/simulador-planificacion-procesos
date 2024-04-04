package simulador.cola;

// Cola de tipo generica <T>, para manejar cualquier tipo de dato
public class Cola<T> {
    //Definimos los nodos de enfrente y detras
    //Estos tienen datos tipo T, donde T es la referencia a procesos ya que cada nodo pertenece a un proceso
    // Inicializamos la cola con ambos frentes y detras apuntando a null, ya que la cola está vacía al principio
    private Nodo<T> frente;
    private Nodo<T> detras;
    // Variable para almacenar la longitud de la cola
    private int longitud;
    // Constructor de la clase Cola
    public Cola(){
        //Inicializamos la cola con un nulo de en el principio y el fin de la cola
        this.frente = null;
        this.detras = null;
        //Inicializamos la longitud de la cola
        this.longitud = 0;
    }
    //Definir si la cola esta vacia
    public Boolean estaVacia(){
        // Si el frente y detras son nulos, la cola esta vacia
        return (null == frente && null == detras);
    }
    
    public T getFrente() {
        return this.frente.getValor();
    }
    
    //Encolamos Nodos con datos tipos T, donde valor almacena la referencia al proceso
    public Boolean encolar(T valor) {
        //Creamos un constructor de nodo
        Nodo<T> nodoNuevo = new Nodo<>();
        //Al nodo creado le asignamos el valor
        nodoNuevo.setValor(valor);
        
        //Si la cola esta esta vacia, entonces al encolar ese nodo es el frente y detras ya que es el unico
        if( this.estaVacia() ){
            this.frente = this.detras = nodoNuevo;
            //Esta condicion la manejamos con el booleano True
            return true;
        }
        
        //El nodo que se encuentre adelante del nodo creado.
        this.detras.setNodoSiguiente(nodoNuevo);
        nodoNuevo.setNodoPrevio(this.detras);
        //Encolamos el nodo a la cola 
        this.detras = nodoNuevo;
        //Aumentamos la longitud de la cola ya que un proceso a sido encolado
        this.longitud ++;
        return true;
    }
    //Metodo para desencolar
    public T desencolar(){
        //Si la cola esta vacia no podemos desencolar porque no hay nada, entonces retornamos un null
        if ( this.estaVacia() ) {
            return null;
        }
        
        //Se crea una variable temporal de tipo Nodo<T> que apunta al primer elemento de la cola (el frente de la cola). Esto es para trabajar con este nodo sin modificar directamente el frente de la cola aún.
        Nodo<T> temporal = this.frente;
        //Se obtiene el valor almacenado en el nodo que está al frente de la cola y se almacena en la variable valor
        T valor = temporal.getValor();
        //Se verifica si el nodo actual (el frente de la cola) tiene un nodo siguiente.
        if( temporal.getNodoSiguiente() != null ){
            //Si el nodo frontal tiene un nodo siguiente, desconecta el nodo siguiente del nodo actual (frente de la cola), eliminando la referencia al nodo actual desde el siguiente.
            temporal.getNodoSiguiente().setNodoPrevio(null);
            // Actualizamos el frente de la cola para que apunte al siguiente nodo
            this.frente = temporal.getNodoSiguiente();
            // Limpiamos la referencia al nodo siguiente desde el nodo frontal
            temporal.setNodoSiguiente(null);
        }
        else {
            // Si el nodo frontal es el único nodo en la cola, actualizamos tanto el frente como el detrás a null
            this.frente = this.detras = null;
        }
        // Decrementamos la longitud de la cola
        this.longitud --;
        // Retornamos el valor del nodo frontal que fue desencolado
        return valor;
    }
    //Metodo para imprimir la cola
    public void imprimirCola(){
        // Empezamos desde el final de la cola
        Nodo<T> actual = this.detras;
          // Imprimimos el inicio del formato de la cola
        System.out.print("[");
        // Mientras haya nodos en la cola (hasta llegar al frente)
        while( actual != null ) {
            //Imprimimos el valor del nodo actual
            System.out.print(" " + actual.getValor().toString() + " ");
            // Avanzamos al nodo previo en la cola
            actual = actual.getNodoPrevio();
        }
        //Fin del formato
        System.out.println("]");
    }
    
}
