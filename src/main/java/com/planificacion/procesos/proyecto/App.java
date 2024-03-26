package com.planificacion.procesos.proyecto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import algoritmos.*;
import componentes.BotonEstilado;
import java.util.Scanner;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
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
                Stage newStage = new Stage();
                newStage.setScene(precargar.recuperarEscena());
                newStage.show();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        launch();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la memoria total disponible: ");
        int memoria = scanner.nextInt();
        GestorDeMemoria.crearGestorMemoria(memoria);
        
        System.out.print("Ingrese el tiempo de quantum: ");
        int tiempoQuantum = scanner.nextInt();

        GestorDeProcesos gestor = GestorDeProcesos.crearGestorProcesos();
        gestor.asignarQuantum(tiempoQuantum);

        String continuar;
        do {
            gestor.agregarProcesoInteractivo();
            System.out.print("¿Desea agregar otro proceso? (s/n): ");
            continuar = scanner.next();
        } while (continuar.equalsIgnoreCase("s"));
        // Comentario

        System.out.println("Configuración completa. Las colas de procesos están listas.");
        // Aquí podrías seguir con la lógica para procesar las colas, etc.
    }   
    
}