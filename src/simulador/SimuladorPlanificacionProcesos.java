package simulador;

//Sirve para pedir datos por teclado
import java.util.Scanner;
//Sirve para dar formato a las tablas
import jtexttable.TextTable;
import simulador.colaprocesos.ColaPL;
import simulador.colaprocesos.ColaPLE;
//Sirve para crear las colas
import simulador.colaprocesos.ColaProcesos;
//Sirve para ir almacenando los procesos
import simulador.proceso.Proceso;

public class SimuladorPlanificacionProcesos {
    
    public static void main(String[] args) {
       
        /*
            Para esta parte del codigo leemos la entradas del usuario,
            con algunas validaciones para que el programa funcione bien
        */
//        ColaProcesos colaPL = leerProcesos();
//        int quantum = leerQuantum();
        
        //Algoritmo Round Robin
        int tiempoActual = 0;
        //En caso de que el tiempo de llegada del primer proceso sea distinto de 0, arreglamos los resultados con esta variable
        int tiempoAuxiliar = 0;
        //Refiere a procesos completados, conforme vayan acabando los procesos incrementa en 1
        int totalProcesos = 0;
        //Variables usadas para almacenar la suma de los tiempos de espera,respuesta,ejecucion
        int sumaTiempoEspera = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEjecucion = 0;
        
        int quantum = 4; // TODO: leer del usuario

        // Encolamos en PL
        // TODO: leer los procesos del usuario
        ColaPL colaPL = new ColaPL();
        // Proceso(id, nombre, tamanio, tiempoServicio, tiempoLlegada, prioridad, tiempoSubidaCPU, tiempoProcesado)
        colaPL.encolarPL(new Proceso(1, "P1", 100, 20, 0, 1, 0,0));
        colaPL.encolarPL(new Proceso(2, "P2", 400, 8, 0, 1, 0,0));
        colaPL.encolarPL(new Proceso(3, "P3", 400, 10, 0, 1, 0,0));
        colaPL.encolarPL(new Proceso(4, "P4", 100, 13, 0, 1, 0,0));
        colaPL.encolarPL(new Proceso(5, "P5", 100, 2, 0, 1, 0,0));
        colaPL.encolarPL(new Proceso(6, "P6", 400, 9, 0, 1, 0,0));
        
        ColaPLE colaPLE = new ColaPLE();
        
        // Mientras haya procesos en la cola de procesos
        while ( !colaPL.estaVacia() || !colaPLE.estaVacia() ) {
            
            while ( !colaPL.estaVacia() &&
                    colaPLE.tieneMemoria(colaPL.obtenerProcesoDeEnFrente().getTamanio()) 
                    ) {
                Proceso procesoPL = colaPL.desencolar();
                colaPLE.encolarPLE( procesoPL );
            }
            
            // Obtener el proceso de la cola de procesos
            Proceso procesoActual = colaPLE.desencolar();
            
            //Este condicinal se ocupa cuando el primer proceso en llegar tiene un tiempo de llegada disntinto a 0
            //Ya que los calculos se realizan considerando un tiempo inicial de 0
            
            if(procesoActual.id == 1 && procesoActual.tiempoLlegada != 0){
                //El tiempoAuxiliar es igual al tiempo de llegada del primer proceso
                //Usado para el calculo de tiempos
                tiempoAuxiliar = procesoActual.tiempoLlegada;
            }
            
            // Si el proceso no ha sido ejecutado previamente, se le asigna el tiempo en el que subio a CPU
            //El proceso tiene que tener una id distinta a 1 ya que en algunos ejercicios el tiempo de llegada del primer proceso es 0
            //Y puede tomar valores incorrectos
            if (procesoActual.gettiempoSubidaCPU() == 0 && procesoActual.id != 1) {
                //Asignamos el tiempo de subida de cpu como el tiempo actual
                procesoActual.settiempoSubidaCPU(tiempoActual);
                //Imprimimos el tiempo actual para ver si es correcto
                //System.out.println("TIEMPO ACTUAL"+ tiempoActual);
                
            }

            // Calcular el tiempo de ejecución según el quantum y el tiempo restante del proceso
            //Retorna el valor mas pequeño entre el tiempo de servicio y el quantum
            //Por ejemplo cuando el tiempo de servicio es 2 y el quantum 4.
            int tiempoEjecucion = Math.min(procesoActual.getTiempoServicio(), quantum);
            
            //Acumulamos en la variable tiempoProcesado del proceso actual el tiempo que se ejecuto
            procesoActual.tiempoProcesado += tiempoEjecucion;
            // Avanzar el tiempo actual
            tiempoActual += tiempoEjecucion;
            
            
            
           
            // Restar el tiempo de ejecución del proceso
            procesoActual.setTiempoServicio(procesoActual.getTiempoServicio() - tiempoEjecucion);
            System.out.println("Tiempo:"+tiempoActual+ " Proceso " + procesoActual.id + " ejecutado durante " + tiempoEjecucion + " unidades. Tiempo restante: " + procesoActual.tiempoServicio);
            // Si el proceso aún tiene tiempo restante
            if (procesoActual.getTiempoServicio() > 0) {
                // Volver a encolar el proceso en la cola de procesos
                colaPLE.encolarPLE(procesoActual);
            } else {
                // El proceso ha terminado
                
                System.out.println("Proceso " + procesoActual.id + " completado y no volverá a la cola. Tiempo sobrante " + Math.abs(procesoActual.tiempoServicio) + "\n");
                //Aumentamos el numero de procesos terminados ya que uno acaba de terminar
                totalProcesos++;
                //El proceso actual finalizo en el tiempoActual
                int tiempoFinalizacion = tiempoActual;
                //Realizacion del calculo de los tiempos de respuesta,espera y ejecucion de cada proceso
                int tiempoRespuesta = procesoActual.tiempoSubidaCPU - procesoActual.tiempoLlegada + tiempoAuxiliar*2;
                int tiempoEspera = (tiempoActual - tiempoEjecucion) -  procesoActual.tiempoLlegada - (procesoActual.tiempoProcesado - tiempoEjecucion + tiempoAuxiliar) ;
                int tiempoEje = tiempoFinalizacion - procesoActual.tiempoLlegada;
                
                //tests para saber si se esta realizando el calculo correctamente.
                //System.out.println("TIEMPO RESPUESTA:"+ procesoActual.tiempoSubidaCPU + "-" + procesoActual.tiempoLlegada + "= " +tiempoRespuesta);
                //System.out.println("TIEMPO ESPERA"+ tiempoEspera);
                
                // Sumar los tiempos para calcular el promedio
                sumaTiempoEjecucion += tiempoEje; // Tiempo de ejecución es igual al tiempo de respuesta en Round Robin
                sumaTiempoRespuesta += tiempoRespuesta;
                sumaTiempoEspera += tiempoEspera;
            }
            
        }
        //Crear un TextTable para mostrar los resultados de la simulación en forma de tabla
        
        String[] encabezados2 = {"Total de procesos", "Tiempo promedio de espera", "Tiempo promedio de respuesta", "Tiempo promedio de ejecución"};
        String[][] datos = {{String.valueOf(totalProcesos), String.format("%.2f", ((double) sumaTiempoEspera / totalProcesos)),
                String.format("%.2f", ((double) sumaTiempoRespuesta / totalProcesos)), String.format("%.2f", ((double) sumaTiempoEjecucion / totalProcesos))}};
        TextTable tablaResultados = new TextTable(encabezados2, datos);

        // Mostrar la tabla de resultados
        System.out.println("\nTabla de resultados:");
        System.out.println(tablaResultados);
    }
    
    private static ColaProcesos leerProcesos() {
        // Crear un objeto Scanner para leer datos de la consola
        Scanner scanner = new Scanner(System.in);
        
        // Definir los procesos con sus tamaños, tiempos de ejecución y tiempos de llegada
        System.out.println("================== SIMULADOR PLANIFICADOR DE PROCESOS ==================");
        System.out.println("Instrucciones");
        System.out.println("\tCada proceso que usted ingrese debe contar con valores para el tamaño, tiempo de servicio y tiempo de llegada");
        System.out.println("\tConsidere que el tamanio de memoria disponible es de 1024, por lo que ningun proceso puede ser mayor a esa cantidad\n");
        
        // Solicitar el número de procesos
        int numProcesos;
        //Creamos la cola de procesos listos (aquellos que estan en espera de subir a la CPU
        ColaProcesos colaPL = new ColaProcesos();
        do {
            
            System.out.print("Ingrese el numero de procesos: ");
            
            // Si el usuario ingresa un numero que no es entero
            while ( !scanner.hasNextInt() ) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            
            // Leemos el numero de procesos
            numProcesos = scanner.nextInt();
            
            // Si el usuario pone 0 como numero de procesos, repetimos el ciclo
            if ( numProcesos <= 0 ) {
                System.out.println("Error: El numero de procesos debe ser mayor que 0.");
            }
        } while (numProcesos <= 0);

        // Solicitar los datos para cada proceso
        
        // Arreglo temporal, solo para imprimir la entrada
        String[][] procesosUsuario= new String[numProcesos][6];
        for ( int i = 1; i <= numProcesos; i++ ) {
            System.out.println("------------------------------------------");
            System.out.println("\nProceso " + i + ":");
            int tamanio, tiempoServicio, tiempoLlegada, id, prioridad;
            String nombre;
            //Inicializamos el tiempo en el que subio por primera vez a la CPU en 0
            int tiempoSubidaCPU = 0;
            int tiempoProcesado = 0;

            // Solicitar el tamanio del proceso
            do {
                System.out.print("Ingrese el tamanio del proceso (menor o igual a 1024): ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                
                tamanio = scanner.nextInt();
                
                if ( tamanio <= 0 || tamanio > 1024 ) {
                    System.out.println("Error: El tamanio del proceso debe ser mayor que 0 y menor o igual a 1024.");
                }
            } while ( tamanio <= 0 || tamanio > 1024 );

            // Solicitar el tiempo de servicio del proceso
            do {
                System.out.print("Ingrese el tiempo de servicio del proceso (mayor o igual a 0): ");
                
                while ( !scanner.hasNextInt() ) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                
                tiempoServicio = scanner.nextInt();
                
                if ( tiempoServicio < 0 ) {
                    System.out.println("Error: El tiempo de servicio del proceso debe ser mayor o igual a 0.");
                }
            } while ( tiempoServicio < 0 );

            // Solicitar el tiempo de Llegada del proceso
            do {
                System.out.print("Ingrese el tiempo de llegada del proceso (mayor o igual a 0): ");
                
                while ( !scanner.hasNextInt() ) {
                    System.out.println("Error: Ingrese un numero entero.");
                    scanner.next();
                }
                
                tiempoLlegada = scanner.nextInt();
                
                if ( tiempoLlegada < 0 ) {
                    System.out.println("Error: El tiempo de llegada del proceso debe ser mayor o igual a 0.");
                }
            } while ( tiempoLlegada < 0 );

            System.out.print("Ingrese el nombre del proceso: ");
            nombre = scanner.next();
            
            do {
            
            System.out.print("Ingrese la prioridad del proceso: ");
            
            // Si el usuario ingresa un numero que no es entero
            while ( !scanner.hasNextInt() ) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            
            // Leemos el numero de procesos
            prioridad = scanner.nextInt();
            
            // Si el usuario pone 0 como numero de procesos, repetimos el ciclo
            if ( prioridad <= 0 ) {
                System.out.println("Error: La prioridad debe ser mayor que 0.");
            }
            } while (prioridad <= 0);
            
            // Llenamos la matriz para imprimirla al final
            procesosUsuario[i-1][0] = String.valueOf( i );
            procesosUsuario[i-1][1] = nombre;
            procesosUsuario[i-1][2] = String.valueOf( tamanio );
            procesosUsuario[i-1][3] = String.valueOf( tiempoServicio );
            procesosUsuario[i-1][4] = String.valueOf( tiempoLlegada );
            procesosUsuario[i-1][5] = String.valueOf( prioridad );
            
            // Insertamos el proceso a la cola
            colaPL.encolarPLE( new Proceso(i, nombre, tamanio, tiempoServicio, tiempoLlegada,prioridad,tiempoSubidaCPU,tiempoProcesado) );       
        }
        
        // Mostrar procesos al usuario
        System.out.println("\nProcesos a simular: ");
        String []encabezados = {"id", "Proceso", "Tamanio", "Tservicio", "Tllegada", "Prioridad"};
        System.out.println( new TextTable(encabezados, procesosUsuario) );
 
        return colaPL;
    }
    
    public static int leerQuantum() {
        
        Scanner scanner = new Scanner(System.in);
        
        int quantum;
        
        // Pedir quantum
        do {
            
            System.out.print("Ingrese el quantum con el que desea trabajar: ");
            
            // Si el usuario ingresa un numero que no es entero
            while ( !scanner.hasNextInt() ) {
                System.out.println("Error: Ingrese un numero entero.");
                scanner.next();
            }
            
            // Leemos el numero de procesos
            quantum = scanner.nextInt();
            
            // Si el usuario pone 0 como numero de procesos, repetimos el ciclo
            if ( quantum<= 0 ) {
                System.out.println("Error: El quantum debe ser mayor que 0.");
            }
            } while (quantum <= 0);

        scanner.close();
        
        return quantum;
    }
}

    

