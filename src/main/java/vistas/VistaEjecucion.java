/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import componentes.BotonEstilado;
import controlador.ControladorEjecucion;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author btell
 */
public class VistaEjecucion extends Vista {
    private VBox panelPrincipal;
    private VBox memoria;
    private HBox procesosNoCargados;
    private GridPane ejecucion;
    private VBox formulario;
    private VBox datosTiempoReal;
    private HBox controles;

    @Override
    protected void inicializarVista() {
        this.formulario = new VBox(
                new Label("Nombre: "), new TextField("Mi proceso"),
                new Label("Duracion: "), new TextField("En milisegundos"),
                new Label("Tiempo de llegada: "), new TextField("En milisegundos"),
                new Label("Tamaño: "), new TextField("El bytes"),
                new BotonEstilado("Crear proceso")
        );
        this.controles = new HBox(40, new BotonEstilado("Cancelar"), new BotonEstilado("Iniciar simulación"), new BotonEstilado("Terminar"));
        this.memoria = new VBox(10);
        this.procesosNoCargados = new HBox(10);
        this.ejecucion = new GridPane();
        this.datosTiempoReal = new VBox(10, 
                new Label("Tiempo ejecucicion:"), new Label("0 [ms]"), 
                new Label("Tiempo espera:"), new Label("0 [ms]"), 
                new Label("Tiempo respuesta:"), new Label("0 [ms]"), 
                new Label("Tiempo promedio:"), new Label("0 [ms]")
        );
        this.panelPrincipal = new VBox(10, 
            new HBox(40, 
                new VBox(new Label("Memoria"), this.memoria),
                new VBox(new Label("Procesos no cargados en memoria"), this.procesosNoCargados, new Label("Ejecución de procesos"), this.ejecucion),
                new VBox(new Label("Creación de procesos"), this.formulario, new Label("Datos ejecución"), this.datosTiempoReal)
            ),
            this.controles
        );
        this.scene = new Scene(this.panelPrincipal, 800, 600);
        this.controlador = new ControladorEjecucion(this);
    }

    @Override
    public void refrescar() {
    }
    
    public VBox recuperarFormulario() {
        return this.formulario;
    }
    
    public HBox recuperarControles() {
        return this.controles;
    }
    
    public VBox recuperarDatos() {
        return this.datosTiempoReal;
    }

    public HBox recuperarSWAP() {
        return this.procesosNoCargados;
    }
    
    public VBox recuperarRAM() {
        return this.memoria;
    }
}
