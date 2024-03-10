/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author jcapi
 */

class Nodo {
    Proceso proceso;
    Nodo siguiente;

    public Nodo(Proceso proceso) {
        this.proceso = proceso;
        this.siguiente = null;
    }
}

public class ColaProcesos {
    private Nodo frente;
    private Nodo finalCola;

    public ColaProcesos() {
        this.frente = null;
        this.finalCola = null;
    }

    // Método para agregar un proceso a la cola
    public void enqueue(Proceso nuevoProceso) {
        Nodo nuevoNodo = new Nodo(nuevoProceso);
        if (frente == null) {
            frente = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
    }

    // Método para remover y retornar el proceso al frente de la cola
    public Proceso dequeue() {
        if (frente == null) {
            return null; // La cola está vacía
        }
        Proceso proceso = frente.proceso;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null; // La cola se ha vaciado completamente
        }
        return proceso;
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }
}



