/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import algoritmos.BancoProcesos;
import algoritmos.ColaProcesos;
import algoritmos.GestorDeMemoria;
import algoritmos.GestorDeProcesos;
import algoritmos.Proceso;
import componentes.BotonEstilado;
import componentes.ProcesoClickeable;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vistas.Vista;
import vistas.VistaEjecucion;

/**
 *
 * @author btell
 */
public class ControladorEjecucion extends Controlador {
    private Timer temporizador;
    private int t = -1;
    
    public ControladorEjecucion(Vista vista) {
        super(vista);
    }

    @Override
    public void configurar() {
        VistaEjecucion vistaEjecucion = (VistaEjecucion) this.vista;
        HBox controles = vistaEjecucion.recuperarControles();
        VBox datos = vistaEjecucion.recuperarDatos();
        
        GestorDeMemoria.crearGestorMemoria(3);
        GestorDeMemoria.obtenerGestorMemoria().observar(this);
        GestorDeProcesos.obtenerGestorProcesos().observar(this);
        
        ((BotonEstilado)controles.getChildren().get(1)).setOnAction(evento -> {
            temporizador = new Timer(false);
            temporizador.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        t++;
                        ColaProcesos cola = BancoProcesos.obtenerBancoProcesos().extraerProcesos(t);
                        while(cola != null && !cola.estaVacia())
                            GestorDeProcesos.obtenerGestorProcesos().agregarProceso(cola.dequeue());
                        ((Label)datos.getChildren().get(1)).setText("" + t + " [ms]");
                    });
                }
            }, 0, 1000);
        });
    }

    @Override
    public void actualizar() {
        VistaEjecucion vistaEjecucion = (VistaEjecucion) this.vista;
        HBox SWAP = vistaEjecucion.recuperarSWAP();
        VBox RAM = vistaEjecucion.recuperarRAM();
        
        Proceso[] procesosSWAP = GestorDeProcesos.obtenerGestorProcesos().obtenerProcesos();
        Proceso[] procesosRAM = GestorDeMemoria.obtenerGestorMemoria().obtenerProcesos();
        
        SWAP.getChildren().clear();
        for(Proceso proceso: procesosSWAP)
            SWAP.getChildren().add(new ProcesoClickeable(proceso));
        
        RAM.getChildren().clear();
        for(Proceso proceso: procesosRAM)
            RAM.getChildren().add(new ProcesoClickeable(proceso));
    }
}
