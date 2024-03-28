package com.planificacion.procesos.proyecto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import algoritmos.*;
import componentes.BotonEstilado;
import java.util.Scanner;
import javafx.scene.layout.VBox;
import vistas.Vista;
import vistas.VistaPrecargarProcesos;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Bienvenido a este simulador de gestor de procesos.");
        BotonEstilado botonPrecargar = new BotonEstilado("Precargar procesos");
        BotonEstilado botonTiempoReal = new BotonEstilado("Procesos en tiempo real");
        
        var scene = new Scene(new VBox(label, botonPrecargar, botonTiempoReal), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        botonPrecargar.setOnAction(evento -> {
            try {
                VistaPrecargarProcesos precargar = new VistaPrecargarProcesos();
                ControladorVistas controlador = ControladorVistas.obtenerControlador();
                controlador.registrarVista(Vista.VISTA_PRECARGAR, precargar);
                controlador.mostrarVista(Vista.VISTA_PRECARGAR);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }   
    
}