package simulador.proceso;

// Clase para representar un proceso
public class Proceso {
    
    private int id; // Identificador del proceso
    private String nombre; // Nombre del proceso
    private int tamanio; // Tamanio del proceso
    private int tiempoServicio; // Tiempo de ejecucion del proceso
    private int tiempoLlegada; // Tiempo de llegada

    public Proceso() {
    }

    public Proceso(int id, String nombre, int tamanio, int tiempoServicio, int tiempoLlegada) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.tiempoServicio = tiempoServicio;
        this.tiempoLlegada = tiempoLlegada;
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

    @Override
    public String toString() {
        return "Proceso{" + "id=" + id + ", nombre=" + nombre + ", tamanio=" + tamanio + ", tiempoServicio=" + tiempoServicio + ", tiempoLlegada=" + tiempoLlegada + '}';
    }
   
}
