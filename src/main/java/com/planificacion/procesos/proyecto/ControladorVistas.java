/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planificacion.procesos.proyecto;

import javafx.stage.Stage;
import vistas.Vista;

class TablaHash<T> {
    private class Nodo<T> {
        final private String llave;
        final private T valor;
        public Nodo(String llave, T valor) {
            this.llave = llave;
            this.valor = valor;
        }
    }
    final private Nodo[] tabla;
    final private int tamano;
    private int index = 0;
    
    public TablaHash(int tamano) {
        tabla = new Nodo[tamano];
        this.tamano = tamano;
    }
    
    public boolean insertar(String llave, T valor) {
        if(index >= tamano)
            return false;
        
        tabla[index] = new Nodo<>(llave, valor);
        index++;
        return true;
    }
    
    public T eliminar(String llave) {
        if(this.index == 0)
            return null;
        
        int i = 0;
        T x = null;
        for(; i < index; i++) {
            if(tabla[i].llave.equalsIgnoreCase(llave)) {
                x = (T) tabla[i].valor;
                break;
            }
        }
        
        index--;
        if(i != tamano)
            for(; i < index; i++)
                tabla[i] = tabla[i + 1];
        
        return x;
    }
    
    public T recuperar(String llave) {
        if(this.index == 0)
            return null;
        
        int i = 0;
        for(; i < index; i++)
            if(tabla[i].llave.equalsIgnoreCase(llave))
                return (T) tabla[i].valor;

        return null;
    }
    
    public void vaciar() {
        this.index = 0;
    }
}
/**
 *
 * @author btell
 */
public class ControladorVistas {
    final private TablaHash tabla = new TablaHash<Vista>(10);
    private static ControladorVistas instancia;
    
    public boolean registrarVista(String nombre, Vista vista) {
        return tabla.insertar(nombre, vista);
    }
    
    public static ControladorVistas obtenerControlador() {
        if(instancia == null)
            instancia = new ControladorVistas();
        return instancia;
    }
    
    public Vista obtenerVista(String nombre) {
        return (Vista) tabla.recuperar(nombre);
    }
    
    private Stage ultimaVentana;
    public void mostrarVista(String nombre) {
        Vista vista = (Vista) tabla.recuperar(nombre);
        
        if(ultimaVentana != null)
            ultimaVentana.close();
        
        if(vista == null)
            return;
        
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(vista.recuperarEscena());
        nuevaVentana.show();
        ultimaVentana = nuevaVentana;
    }
    
    public void reiniciar() {
        tabla.vaciar();
    }
}
