/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author jcapi
 * @author btell
 */
import java.util.Scanner;

public class GestorDeProcesos {
    // Funciona como una memoria "SWAP", almacena aquellos que no han podido ser cargados en memoria
    final private ColaProcesos colaListosSwap;
    // Especifica el periodo 
    final private int tiempoQuantum;

    public GestorDeProcesos(int tiempoQuantum) {
        this.colaListosSwap = new ColaProcesos();
        this.tiempoQuantum = tiempoQuantum;
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
        if (GestorDeMemoria.obtenerGestorMemoria().asignarMemoria(proceso) == -1) {
            colaListosSwap.enqueue(proceso);
            System.out.println("Proceso agregado a la cola de listos (no hay suficiente memoria).");
        }
    }
}