package simulador.proceso;

public class Proceso {
    
    private int id;
    private String nombre;
    private int tamanio;
    private int tiempoEjecucion;
    private int prioridad;
    private int tiempoEntradasSalidas;
    private int tiempoLlegada;

    public Proceso() {
    }

    public Proceso(int id, String nombre, int tamanio, int tiempoEjecucion, int prioridad, int tiempoEntradasSalidas, int tiempoLlegada) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.tiempoEjecucion = tiempoEjecucion;
        this.prioridad = prioridad;
        this.tiempoEntradasSalidas = tiempoEntradasSalidas;
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

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoEntradasSalidas() {
        return tiempoEntradasSalidas;
    }

    public void setTiempoEntradasSalidas(int tiempoEntradasSalidas) {
        this.tiempoEntradasSalidas = tiempoEntradasSalidas;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    @Override
    public String toString() {
        return "Proceso{" + "id=" + id + ", nombre=" + nombre + ", tamanio=" + tamanio + ", tiempoEjecucion=" + tiempoEjecucion + ", prioridad=" + prioridad + ", tiempoEntradasSalidas=" + tiempoEntradasSalidas + ", tiempoLlegada=" + tiempoLlegada + '}';
    }

}
