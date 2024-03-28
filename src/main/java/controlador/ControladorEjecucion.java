/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import algoritmos.GeneradorMetricas;
import algoritmos.GestorDeMemoria;
import algoritmos.GestorDeProcesos;
import algoritmos.Proceso;
import componentes.BotonEstilado;
import componentes.ProcesoClickeable;
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
    public ControladorEjecucion(Vista vista) {
        super(vista);
    }

    @Override
    public void configurar() {
        VistaEjecucion vistaEjecucion = (VistaEjecucion) this.vista;
        HBox controles = vistaEjecucion.recuperarControles();
        
        GestorDeMemoria.crearGestorMemoria(4);
        GestorDeMemoria.obtenerGestorMemoria().observar(this);
        GestorDeProcesos.obtenerGestorProcesos().observar(this);
        
        ((BotonEstilado)controles.getChildren().get(1)).setOnAction(evento -> {
            GestorDeProcesos.obtenerGestorProcesos().asignarQuantum(4);
            GestorDeProcesos.obtenerGestorProcesos().iniciarRoundRobin();
        });
    }

    @Override
    public void actualizar() {
        VistaEjecucion vistaEjecucion = (VistaEjecucion) this.vista;
        GeneradorMetricas metricas = GeneradorMetricas.obtenerGeneradorMetricas();
        VBox datos = vistaEjecucion.recuperarDatos();
        float t;
        if((t = GestorDeProcesos.obtenerGestorProcesos().obtenerTiempo()) > 0)
            ((Label)datos.getChildren().get(1)).setText("" + t + " [ms]");
        if((t = metricas.calcularTiempoEsperaPromedio()) > 0)
            ((Label)datos.getChildren().get(3)).setText("" + t + " [ms]");
        if((t = metricas.calcularTiempoRespuestaPromedio()) > 0)
            ((Label)datos.getChildren().get(5)).setText("" + t + " [ms]");
        if((t = metricas.calcularTiempoEjecucionPromedio()) > 0)
            ((Label)datos.getChildren().get(7)).setText("" + t + " [ms]");
        
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
