// Clase para representar un proceso
package simulador;

class Process {
    int id; // Identificador único del proceso
    String name; // Nombre del proceso
    int size; // Tamaño del proceso
    int executionTime; // Tiempo de ejecución del proceso
    int priority; // Prioridad del proceso
    int ioTime; // Tiempo de entradas/salidas del proceso
    int arrivalTime; // Tiempo de llegada del proceso

    // Constructor de la clase Process
    Process(int id, String name, int size, int executionTime, int priority, int ioTime, int arrivalTime) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.executionTime = executionTime;
        this.priority = priority;
        this.ioTime = ioTime;
        this.arrivalTime = arrivalTime;
    }
}

// Clase para representar un nodo en la lista enlazada
class Node {
    Process process;
    Node next;

    Node(Process process) {
        this.process = process;
        this.next = null;
    }
}

// Clase para representar una cola de procesos
class ProcessQueue {
    Node front, rear;

    ProcessQueue() {
        this.front = this.rear = null;
    }

    // Método para agregar un proceso a la cola
    void enqueue(Process process) {
        Node newNode = new Node(process);

        // Si la cola está vacía, el nuevo nodo será tanto el frente como el final de la cola
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }

        // Agregar el nuevo nodo al final de la cola y actualizar el puntero rear
        this.rear.next = newNode;
        this.rear = newNode;
    }

    // Método para eliminar y devolver el proceso en la parte frontal de la cola
    Process dequeue() {
        // Si la cola está vacía, devolver null
        if (this.front == null)
            return null;

        // Guardar el proceso en la parte frontal de la cola y mover el puntero front al siguiente nodo
        Process process = this.front.process;
        this.front = this.front.next;

        // Si el frente se vuelve null, entonces la cola está vacía, actualizar también el puntero rear
        if (this.front == null)
            this.rear = null;

        return process;
    }

    // Método para verificar si la cola está vacía
    boolean isEmpty() {
        return (this.front == null);
    }
}

// Clase principal que contiene la lógica del planificador Round Robin
public class RoundRobinMetodo {
    public static void main(String[] args) {
        // Definir los procesos con sus tiempos de llegada y tiempos de ráfaga
        Process[] processes = {
            new Process(1, "P1", 10, 5, 1, 2, 0),
            new Process(2, "P2", 8, 4, 2, 3, 1),
            new Process(3, "P3", 12, 6, 3, 1, 2),
            new Process(4, "P4", 9, 3, 4, 2, 3)
        };

        int quantum = 2; // Quantum de tiempo para el algoritmo Round Robin

        // Llamar a la función para simular la planificación de procesos
        roundRobin(processes, quantum);
    }

    // Función para simular la planificación de procesos utilizando el algoritmo Round Robin
    public static void roundRobin(Process[] processes, int quantum) {
        ProcessQueue queue = new ProcessQueue(); // Crear una cola de procesos

        int currentTime = 0; // Tiempo actual de la simulación
        int totalProcesses = processes.length; // Número total de procesos
        int completedProcesses = 0; // Número de procesos completados

        // Mientras haya procesos incompletos, continuar la simulación
        while (completedProcesses < totalProcesses) {
            // Agregar los procesos llegados al tiempo actual a la cola
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.executionTime > 0) {
                    queue.enqueue(process);
                }
            }

            // Si hay procesos en la cola, ejecutar el proceso en la parte frontal de la cola
            if (!queue.isEmpty()) {
                Process currentProcess = queue.dequeue(); // Obtener el proceso de la parte frontal de la cola
                int executionTime = Math.min(quantum, currentProcess.executionTime); // Tiempo de ejecución actual
                currentTime += executionTime; // Actualizar el tiempo actual
                currentProcess.executionTime -= executionTime; // Actualizar el tiempo restante del proceso

                // Imprimir información sobre la ejecución del proceso
                System.out.println("Ejecutando " + currentProcess.name + " por " + executionTime + " unidades de tiempo.");

                // Verificar si el proceso se ha completado
                if (currentProcess.executionTime == 0) {
                    completedProcesses++; // Incrementar el contador de procesos completados
                    System.out.println(currentProcess.name + " completado en el tiempo " + currentTime);
                } else {
                    queue.enqueue(currentProcess); // Volver a agregar el proceso a la cola si no se ha completado
                }
            } else {
                currentTime++; // Si no hay procesos en la cola, avanzar en el tiempo
            }
        }
    }
}
