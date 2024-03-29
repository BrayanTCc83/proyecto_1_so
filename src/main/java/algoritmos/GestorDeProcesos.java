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
import algoritmos.GeneradorMetricas.Metrica;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

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
    
    private Timer temporizador;
    private int tiempo = -1;
    private int tiempoArriba = 0;
    private boolean cambioProceso = false;
    private Proceso proceso;
    private void subirProcesosMemoria() {
        if(colaListosSwap.estaVacia()) {
            notificar();
            return;
        }
        
        float restante = GestorDeMemoria.obtenerGestorMemoria().obtenerMemoriaRestante();
        float tamano = colaListosSwap.verFinal().getTame();
        if(restante >= tamano) {
            GestorDeMemoria.obtenerGestorMemoria().asignarMemoria(colaListosSwap.dequeue());
            notificar();
        }
    }
    
    private Metrica guardarMetrica(Proceso proceso) {
        GeneradorMetricas metricas = GeneradorMetricas.obtenerGeneradorMetricas();
        Metrica metrica = metricas.obtenerMetricasProceso(proceso);
        if(metrica == null) {
            metricas.registrarProceso(proceso);
            metrica = metricas.obtenerMetricasProceso(proceso);
            metrica.actualizarTiempoEspera(tiempo);
            cambioProceso = true;
        }
        if(cambioProceso) {
            if(tiempo == proceso.tllegada)
                metrica.actualizarTiempoMaximoEspera(tiempo);
            else
                metrica.actualizarTiempoMaximoEspera(tiempo - 1);
            metricas.notificar();
        }
        return metrica;
    }
    
    private void algoritmoRoundRobin() {
        GestorDeMemoria gestor = GestorDeMemoria.obtenerGestorMemoria();
        
        if(proceso == null) {
            proceso = gestor.obtenerUltimoProceso();
            if(proceso == null) {
                subirProcesosMemoria();
                return;
            } else if(tiempo == 0)
                tiempoArriba = 0;
            else {
                tiempoArriba = 1; 
                guardarMetrica(proceso).actualizarTiemposEjecucion();
            }
        }
        
        Metrica metrica = guardarMetrica(proceso);
        System.out.println("TIEMPO: {.Tiempo = " + tiempo + ", .Arriba = " + tiempoArriba + "}, PROCESO [" + proceso.getNombre() + "]: {.Total = " + proceso.tejecucion 
                + ", .Ejecutado = " + metrica.obtenerTiempoEjecutadoActual()+ ", .Restante = " + metrica.obtenerTiempoRestante() + "}" );
        
        if(metrica.obtenerTiempoRestante() == 0) {
            proceso = null;
            gestor.extraerDeMemoria();
            cambioProceso = true;
        } else if(tiempoArriba == tiempoQuantum) {
            colaListosSwap.enqueue(gestor.extraerDeMemoria());
            subirProcesosMemoria();
            proceso = gestor.obtenerUltimoProceso();
            metrica = guardarMetrica(proceso);
            metrica.actualizarTiemposEjecucion();
            tiempoArriba = 1;
            cambioProceso = true;
        } else {
            metrica.actualizarTiemposEjecucion();
            tiempoArriba++;
            cambioProceso = false;
        }
    }
    
    public void iniciarRoundRobin() {
        temporizador = new Timer(false);
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    ColaProcesos cola = BancoProcesos.obtenerBancoProcesos().extraerProcesos(++tiempo);
                    while(cola != null && !cola.estaVacia())
                        agregarProceso(cola.dequeue());
                    notificar();
                    algoritmoRoundRobin();
                });
            }
        }, 0, 1000);
        
    }

    public int obtenerTiempo() {
        return tiempo;
    }

    int obtenerQuantum() {
        return tiempoQuantum;
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