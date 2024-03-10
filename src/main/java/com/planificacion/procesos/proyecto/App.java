package com.planificacion.procesos.proyecto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import algoritmos.*;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        
        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
       
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la memoria total disponible: ");
        int memoria = scanner.nextInt();
        System.out.print("Ingrese el tiempo de quantum: ");
        int tiempoQuantum = scanner.nextInt();

        GestorDeProcesos gestor = new GestorDeProcesos(memoria, tiempoQuantum);

        String continuar;
        do {
            gestor.agregarProcesoInteractivo();
            System.out.print("¿Desea agregar otro proceso? (s/n): ");
            continuar = scanner.next();
        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("Configuración completa. Las colas de procesos están listas.");
        // Aquí podrías seguir con la lógica para procesar las colas, etc.
    }
    
}