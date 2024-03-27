/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import algoritmos.Proceso;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/**
 *
 * @author btell
 */
public class VistaDetallesProceso extends Vista {
    final private Proceso proceso;
    private VBox panelPrincipal, tiempoReal;
    public VistaDetallesProceso(Proceso proceso) {
        this.proceso = proceso;
        inicializarVista();
    }

    @Override
    protected void inicializarVista() {
        if(proceso == null)
            return;
        this.tiempoReal = new VBox(10,
            new Label("Tiempo ejecutado: " + 0),
            new Label("Tiempo ultima subida: " + 0),
            new Label("Tiempo de espera: " + 0),
            new Label("Tiempo de respuesta: " + 0),
            new Label("Tiempo de ejecución: " + 0)
        );
        this.panelPrincipal = new VBox(10,
            new Label(proceso.getNombre()),
            new Label("ID: " + proceso.getIdProceso()),
            new Label("Tamaño: " + proceso.getTame()),
            new Label("Duración: " + proceso.getTejecucion()),
            new Label("Tiempo llegada: " + proceso.getTllegada()),
            this.tiempoReal
        );
        this.scene = new Scene(this.panelPrincipal, 400, 400);
    }

    @Override
    public void refrescar() {
        
    }
}
