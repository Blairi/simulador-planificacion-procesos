package simulador.cola;

public class PruebaCola {
    
    public static void main(String[] args) {
        
        Cola cola = new Cola<String>();
        
        cola.encolar("Primerito");
        cola.encolar("Segundito");
        cola.encolar("Tercerito");
        
        cola.imprimirCola();
        
        Object stringDesencolado1 = cola.desencolar();
        System.out.println("stringDesencolado1 = " + stringDesencolado1);
        
        cola.imprimirCola();
        
        Object stringDesencolado2 = cola.desencolar();
        System.out.println("stringDesencolado2 = " + stringDesencolado2);
        cola.imprimirCola();
        
        System.out.println("La cola esta vacia? " + cola.estaVacia());
        
        Object stringDesencolado3 = cola.desencolar();
        System.out.println("stringDesencolado3 = " + stringDesencolado3);
        
        System.out.println("La cola esta vacia? " + cola.estaVacia());
        
        cola.imprimirCola();
        
        cola.encolar("NuevoStringFormado");
        cola.imprimirCola();
        System.out.println("La cola esta vacia? " + cola.estaVacia());
    }
}
