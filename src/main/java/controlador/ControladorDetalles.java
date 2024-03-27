/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import algoritmos.GeneradorMetricas;
import algoritmos.Proceso;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import vistas.Vista;
import vistas.VistaDetallesProceso;

/**
 *
 * @author btell
 */
public class ControladorDetalles extends Controlador {

    public ControladorDetalles(Vista vista) {
        super(vista);
    }
    @Override
    public void configurar() {
        GeneradorMetricas.obtenerGeneradorMetricas().observar(this);
    }

    @Override
    public void actualizar() {
        VistaDetallesProceso detalles = (VistaDetallesProceso) vista;
        Proceso proceso = detalles.obtenerProcesoAsociado();
        GeneradorMetricas.Metrica metrica = GeneradorMetricas.obtenerGeneradorMetricas().obtenerMetricasProceso(proceso);
        if(metrica == null)
            return;
        
        VBox tiempoReal = detalles.obtenerPanelTiempoReal();
        float t;
        if((t = metrica.obtenerTiempoEjecutado()) > 0)
            ((Label)tiempoReal.getChildren().get(0)).setText("Tiempo ejecutado: " + t + " [ms]");
        if((t = metrica.obtenerPrimeraSubida()) > 0)
            ((Label)tiempoReal.getChildren().get(1)).setText("Tiempo primera subida: " + t + " [ms]");
        if((t = metrica.obtenerMaximaEspera()) > 0)
            ((Label)tiempoReal.getChildren().get(2)).setText("Tiempo ultima subida: " + t + " [ms]");
        if((t = metrica.calcularTiempoEspera()) > 0)
            ((Label)tiempoReal.getChildren().get(3)).setText("Tiempo de espera: " + t + " [ms]");
        if((t = metrica.calcularTiempoRespuesta()) > 0)
            ((Label)tiempoReal.getChildren().get(4)).setText("Tiempo de respuesta: " + t + " [ms]");
        if((t = metrica.calcularTiempoEjecucion()) > 0)
            ((Label)tiempoReal.getChildren().get(5)).setText("Tiempo de ejecución: " + t + " [ms]");
    }
    
}
