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
