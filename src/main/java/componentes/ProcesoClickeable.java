/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import algoritmos.Proceso;
import com.planificacion.procesos.proyecto.ControladorVistas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import vistas.Vista;
import vistas.VistaDetallesProceso;

/**
 *
 * @author btell
 */
public class ProcesoClickeable extends VBox {
    final private Proceso proceso;
    public ProcesoClickeable(Proceso proceso) {
        super(new Label("" + proceso.getIdProceso()), new Label(proceso.getNombre()), new Label("Tamaño: " + proceso.getTame()));
        this.proceso = proceso;
        configurar();
    }

    private void configurar() {
        setOnMouseClicked(evento -> {
            ControladorVistas controlador = ControladorVistas.obtenerControlador();
            VistaDetallesProceso detalles = new VistaDetallesProceso(this.proceso);
            controlador.remplazarVista(Vista.VISTA_PROCESO, detalles);
            controlador.mostrarEmergente(Vista.VISTA_PROCESO);
        });
    }
}
