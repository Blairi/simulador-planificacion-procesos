# Simulador de Planificación de Procesos

## Descripción

Este es un proyecto en Java que hemos desarrollado para simular cómo funcionan los algoritmos de planificación de procesos en un sistema operativo. Implementamos el algoritmo Round Robin, que es uno de los métodos más comunes para distribuir el tiempo de procesamiento de la CPU entre varios procesos.

## Qué es y cómo funciona

El simulador nos permite gestionar procesos en memoria y ejecutarlos por turnos. Cada proceso tiene un tiempo máximo de ejecución llamado "quantum", y si no termina en ese tiempo, se envía al final de la cola para que otros procesos puedan usar la CPU.

El proyecto utiliza dos colas principales:
- **Cola de Procesos Listos (CPL)**: Donde almacenamos los procesos que esperan ser cargados en memoria
- **Cola de Procesos Listos para Ejecución (CPLE)**: Donde están los procesos que están en memoria y esperan ser ejecutados por la CPU

Cada proceso tiene información como su identificador, nombre, tamaño en memoria, tiempo de ejecución, tiempo de llegada al sistema y el quantum que le corresponde.

## Estructura del proyecto

El código está organizado en varios paquetes:

- **simulador**: Contiene la clase principal con el algoritmo Round Robin
- **cola**: Clases base para implementar las colas de datos
- **colaprocesos**: Las colas específicas para procesos (CPL y CPLE)
- **proceso**: La clase Proceso que define los atributos y comportamiento de cada proceso
- **jtexttable**: Utilidad para mostrar los resultados en forma de tablas organizadas

## Requisitos

Se necesita Java instalado en el sistema para compilar y ejecutar el simulador.

## Compilación y ejecución

Para compilar el proyecto usando el archivo build.xml:
```
ant build
```

Para ejecutar el simulador:
```
ant run
```

## Características principales

Nuestro simulador calcula y muestra:
- Tiempos de espera de cada proceso
- Tiempos de respuesta
- Tiempos de ejecución
- El estado de la memoria durante la ejecución
- Gráficos de ejecución en formato de tabla

De esta forma podemos analizar cómo se distribuye el tiempo de procesamiento y verificar que el algoritmo Round Robin funciona correctamente.
