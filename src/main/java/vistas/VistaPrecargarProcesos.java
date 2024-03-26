/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import algoritmos.BancoProcesos;
import algoritmos.GestorDeMemoria;
import algoritmos.ListaProcesos;
import algoritmos.Nodo;
import algoritmos.Proceso;
import componentes.BotonEstilado;
import componentes.ItemProceso;
import controlador.ControladorPrecargar;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author btell
 */
public class VistaPrecargarProcesos extends Vista {
    private HBox panelPrincipal;
    private HBox controles;
    private VBox listaProcesos;
    private VBox formulario;

    @Override
    protected void inicializarVista() {
        this.listaProcesos = new VBox(10);
        this.formulario = new VBox(
                new Label("ID: #000"),
                new Label("Nombre: "), new TextField("Mi proceso"),
                new Label("Duracion: "), new TextField("En milisegundos"),
                new Label("Tiempo de llegada: "), new TextField("En milisegundos"),
                new Label("Tamaño: "), new TextField("El bytes"),
                new BotonEstilado("Crear proceso")
        );
        this.controles = new HBox(40, new BotonEstilado("Cancelar"), new BotonEstilado("Iniciar simulación"));
        this.panelPrincipal = new HBox(40, 
                new VBox(new Label("Procesos precargados"), this.listaProcesos), 
                new VBox(new Label("Creación de procesos"), formulario, controles));
        this.scene = new Scene(this.panelPrincipal, 600, 400);
        this.controlador = new ControladorPrecargar(this);
    }

    @Override
    public void refrescar() {
        listaProcesos.getChildren().clear();
        
        Proceso[] procesos = BancoProcesos.obtenerBancoProcesos().recuperarProcesos();
        for(Proceso proceso: procesos)
            listaProcesos.getChildren().add(new ItemProceso(proceso));
    }
    
    public VBox recuperarFormulario() {
        return this.formulario;
    }
    
    public HBox recuperarControles() {
        return this.controles;
    }
}
