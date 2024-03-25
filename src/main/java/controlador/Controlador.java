/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vistas.Vista;

/**
 *
 * @author btell
 */
public abstract class Controlador {
    protected final Vista vista;
    public Controlador(Vista vista) {
        this.vista = vista;
        this.configurar();
    }
    
    abstract public void configurar();
}
