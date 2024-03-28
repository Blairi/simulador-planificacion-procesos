package simulador.proceso;

// Clase para representar un proceso
public class Proceso {
    
    public int id; // Identificador del proceso
    public String nombre; // Nombre del proceso
    public int tamanio; // Tamanio del proceso
    public int tiempoServicio; // Tiempo de ejecucion del proceso
    public int tiempoLlegada; // Tiempo de llegada
    public int prioridad;
    public int tiempoSubidaCPU;
    //Para el calculo de los tiempos de espera
    //Es un acumulador de cuanto tiempo ha sido procesado
    public int tiempoProcesado;
    

    public Proceso() {
    }

    public Proceso(int id, String nombre, int tamanio, int tiempoServicio, int tiempoLlegada,int prioridad, int tiempoSubidaCPU, int tiempoProcesado) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        //Tiempo de servicio constantemente se tiene que actualizar
        //tiempoServicio = tiempoServicio - Tprev ejecutado
        this.tiempoServicio = tiempoServicio;
        this.tiempoLlegada = tiempoLlegada;
        this.prioridad = prioridad;
        //Tiempo que subio a la CPU por primera vez
        this.tiempoSubidaCPU = tiempoSubidaCPU;
        this.tiempoProcesado = tiempoProcesado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }
      public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    public int gettiempoSubidaCPU() {
        return tiempoSubidaCPU;
    }

    public void settiempoSubidaCPU(int tiempoSubidaCPU) {
        this.tiempoSubidaCPU = tiempoSubidaCPU;
    }
    public int getTiempoProcesado() {
        return tiempoProcesado;
    }

    public void setTiempoProcesado(int tiempoProcesado) {
        this.tiempoProcesado = tiempoProcesado;
    }
    @Override
    public String toString() {
        return "Proceso{" + "id=" + id + ", nombre=" + nombre + ", tamanio=" + tamanio + ", tiempoServicio=" + tiempoServicio + ", tiempoLlegada=" + tiempoLlegada + "prioridad=" + prioridad + "tiempoSubidaCPU="+tiempoSubidaCPU+ '}';
    }
   
}
