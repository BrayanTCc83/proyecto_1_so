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

public class GestorDeProcesos implements Observable {
    static private GestorDeProcesos gestorProcesos = null;
    // Funciona como una memoria "SWAP", almacena aquellos que no han podido ser cargados en memoria
    final private ColaProcesos colaListosSwap;
    // Especifica el periodo 
    private int tiempoQuantum;

    private GestorDeProcesos() {
        this.colaListosSwap = new ColaProcesos();
    }
    
    public static GestorDeProcesos obtenerGestorProcesos() {
        if(gestorProcesos == null)
            gestorProcesos = new GestorDeProcesos();
        return gestorProcesos;
    }
    
    public void asignarQuantum(int quantum) {
        this.tiempoQuantum = quantum;
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
    
    public void agregarProceso(Proceso proceso) {
        if (GestorDeMemoria.obtenerGestorMemoria().asignarMemoria(proceso) == -1)
            colaListosSwap.enqueue(proceso);
        notificar();
    }

    private Observador o;
    @Override
    public void observar(Observador o) {
        this.o = o;
    }

    @Override
    public void olvidar(Observador o) {
        this.o = null;
    }

    @Override
    public void notificar() {
        if(this.o != null)
            this.o.actualizar();
    }

    public Proceso[] obtenerProcesos() {
        return this.colaListosSwap.comoArreglo();
    }
}