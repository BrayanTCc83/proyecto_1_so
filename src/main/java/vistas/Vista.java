/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import controlador.Controlador;
import javafx.scene.Scene;

/**
 *
 * @author btell
 */
abstract public class Vista {
    final public static String VISTA_PRECARGAR = "precargar";
    final public static String VISTA_PRINCIPAL = "principal";
    final public static String VISTA_EJECUCION = "ejecucion";
    final public static String VISTA_PROCESO = "proceso";
    final public static String VISTA_FINAL = "final";
    
    protected Controlador controlador;
    protected Scene scene;
    public Vista() {
        inicializarVista();
    }
    abstract protected void inicializarVista();
    abstract public void refrescar();
    public Scene recuperarEscena() {
        return this.scene;
    }
}
