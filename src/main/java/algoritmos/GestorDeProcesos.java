/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author jcapi
 */
import java.util.Scanner;

public class GestorDeProcesos {
    private ColaProcesos colaEjecucion;
    private ColaProcesos colaListos;
    private int tiempoQuantum;
    private int memoria;
    private int memoria_listo;

    public GestorDeProcesos(int memoria, int tiempoQuantum) {
        this.colaEjecucion = new ColaProcesos();
        this.colaListos = new ColaProcesos();
        this.memoria = memoria;
        this.tiempoQuantum = tiempoQuantum;
        this.memoria_listo = 0;
    }

    public void agregarProcesoInteractivo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del proceso: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el tamaño del proceso: ");
        int tamano = scanner.nextInt();
        System.out.print("Ingrese el tiempo de llegada: ");
        int tiempoLlegada = scanner.nextInt();
        System.out.print("Ingrese el tiempo de ejecución: ");
        int tiempoEjecucion = scanner.nextInt();


        Proceso proceso = new Proceso(nombre, tamano, tiempoEjecucion, tiempoLlegada);

        // Verificar si el proceso cabe en la memoria para ejecución
        if (this.memoria_listo + tamano <= this.memoria) {
            this.memoria_listo += tamano;
            colaEjecucion.enqueue(proceso);
            System.out.println("Proceso agregado a la cola de ejecución.");
        } else {
            colaListos.enqueue(proceso);
            System.out.println("Proceso agregado a la cola de listos (no hay suficiente memoria).");
        }
    }
}