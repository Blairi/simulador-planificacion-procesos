package simulador;

// Clase principal que contiene la lógica del planificador Round Robin
public class RoundRobinMetodo {
    // Clase para representar un proceso
    static class Process {
        int id; // Identificador único del proceso
        String name; // Nombre del proceso
        int size; // Tamaño del proceso
        int executionTime; // Tiempo de ejecución del proceso
        int arrivalTime; // Tiempo de llegada del proceso

        // Constructor de la clase Process
        Process(int id, String name, int size, int executionTime, int arrivalTime) {
            this.id = id;
            this.name = name;
            this.size = size;
            this.executionTime = executionTime;
            this.arrivalTime = arrivalTime;
        }
    }

    // Clase para representar un nodo en la lista enlazada
    static class Node {
        Process process;
        Node next;

        Node(Process process) {
            this.process = process;
            this.next = null;
        }
    }

    // Clase para representar una cola de procesos
    static class ProcessQueue {
        Node front, rear;
        int currentMemorySize; // Tamaño actual de la memoria

        ProcessQueue() {
            this.front = this.rear = null;
            this.currentMemorySize = 0;
        }

        // Método para agregar un proceso a la cola
        void enqueue(Process process) {
            // Verificar si hay suficiente memoria disponible
            if (this.currentMemorySize + process.size <= 1024) {
                Node newNode = new Node(process);
                // Si la cola está vacía, el nuevo nodo será tanto el frente como el final de la cola
                if (this.rear == null) {
                    this.front = this.rear = newNode;
                } else {
                    // Agregar el nuevo nodo al final de la cola y actualizar el puntero rear
                    this.rear.next = newNode;
                    this.rear = newNode;
                }
                // Actualizar el tamaño actual de la memoria
                this.currentMemorySize += process.size;
                // Mostrar información sobre la subida del proceso
                System.out.println("Subió el proceso " + process.name + " y restan " + (1024 - this.currentMemorySize) + " unidades de memoria");
            } else {
                // Mostrar mensaje de que no hay suficiente memoria disponible
                System.out.println("No hay suficiente memoria disponible para subir el proceso " + process.name);
            }
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

            // Actualizar el tamaño actual de la memoria
            this.currentMemorySize -= process.size;

            return process;
        }

        // Método para verificar si la cola está vacía
        boolean isEmpty() {
            return (this.front == null);
        }
    }

    // Función para simular la carga de procesos a la memoria
    public static void simulateMemoryAllocation(int memorySize, Process[] processes) {
        // Mostrar el tamaño de memoria disponible para el usuario
        System.out.println("Tamaño de memoria disponible para el usuario: " + memorySize + "ky");

        // Mostrar la carga de los procesos a la memoria
        ProcessQueue queue = new ProcessQueue();
        for (Process process : processes) {
            queue.enqueue(process);
        }
    }

    public static void main(String[] args) {
        // Definir los procesos con sus tamaños, tiempos de ejecución y tiempos de llegada
        Process[] processes = {
            new Process(1, "P1", 200, 5, 0),
            new Process(2, "P2", 300, 4, 1),
            new Process(3, "P3", 150, 6, 2),
            new Process(4, "P4", 500, 3, 3),
            new Process(5, "P5", 100, 2, 4),
            new Process(6, "P6", 350, 1, 5)
        };

        int memorySize = 1024; // Tamaño de memoria disponible para el usuario

        // Llamar a la función para simular la carga de procesos a la memoria
        simulateMemoryAllocation(memorySize, processes);

        // Definir el quantum para el algoritmo Round Robin
        int quantum = 2;

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

                // Verificar si el proceso actual no es null
                if (currentProcess != null) {
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
                }
            } else {
                currentTime++; // Si no hay procesos en la cola, avanzar en el tiempo
            }
        }
    }
}

