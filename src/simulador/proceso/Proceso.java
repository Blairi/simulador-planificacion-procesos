package simulador.proceso;

// Clase para representar un proceso
public class Proceso {
    
    public int id; // Identificador del proceso
    public String nombre; // Nombre del proceso
    public int tamanio; // Tamanio del proceso
    public int tiempoServicio; // Tiempo de ejecucion del proceso
    public int tiempoLlegada; // Tiempo de llegada
    //Este atributo se solicita pero por indicacion no se utiliza
    public int prioridad;
    //Tiempo en el que es por primera a CPU
    public int tiempoSubidaCPU;
    //Para el calculo de los tiempos de espera
    //Es un acumulador de cuanto tiempo ha sido procesado
    public int tiempoProcesado;
    
    //Constructor de la clase Proceso
    public Proceso() {
    }
    //Todo proceso debe de tener los parametros señalados
    public Proceso(int id, String nombre, int tamanio, int tiempoServicio, int tiempoLlegada,int prioridad, int tiempoSubidaCPU, int tiempoProcesado) {
        //Le asignamos valores a los parametros
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        
        //El tiempo de servicio se va actualizando conforme se vaya ejecutando el proceso
        //Ejemplo P1 con 10 de servicio, se ejecuto 4 msg -> tiempoServicio = tiemposervicio - tiempoEjecucion(en este caso 4)
        this.tiempoServicio = tiempoServicio;
        this.tiempoLlegada = tiempoLlegada;
        this.prioridad = prioridad;
        //Tiempo que subio a la CPU por primera vez
        this.tiempoSubidaCPU = tiempoSubidaCPU;
        this.tiempoProcesado = tiempoProcesado;
    }
    // Métodos de acceso para el id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Métodos de acceso para el nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Métodos de acceso para el tamaño
    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    // Métodos de acceso para el tiempoServicio
    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }
    // Métodos de acceso para el tiempoLlegada
    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }
    //Metodos de acceso para la prioridad
      public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    //Metodos de acceso para el tiempo de subida a CPU
    public int gettiempoSubidaCPU() {
        return tiempoSubidaCPU;
    }

    public void settiempoSubidaCPU(int tiempoSubidaCPU) {
        this.tiempoSubidaCPU = tiempoSubidaCPU;
    }
    // Métodos de acceso para el acumulador del tiempo procesado
    public int getTiempoProcesado() {
        return tiempoProcesado;
    }

    public void setTiempoProcesado(int tiempoProcesado) {
        this.tiempoProcesado = tiempoProcesado;
    }
    //Representamos el proceso como una cadena
    @Override
    public String toString() {
        return "Proceso{" + "id=" + id + ", nombre=" + nombre + ", tamanio=" + tamanio + ", tiempoServicio=" + tiempoServicio + ", tiempoLlegada=" + tiempoLlegada + "prioridad=" + prioridad + "tiempoSubidaCPU="+tiempoSubidaCPU+ '}';
    }
   
}
