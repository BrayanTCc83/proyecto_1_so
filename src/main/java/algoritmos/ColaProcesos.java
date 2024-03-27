/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author jcapi
 */
public class ColaProcesos {
    private Nodo frente;
    private Nodo finalCola;
    private int tamano = 0;

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
        tamano++;
    }

    // Método para remover y retornar el proceso al frente de la cola
    public Proceso dequeue() {
        if (frente == null) {
            return null; // La cola está vacía
        }
        Proceso proceso = (Proceso) frente.valor;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null; // La cola se ha vaciado completamente
        }
        tamano--;
        return proceso;
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }
    
    public Proceso[] comoArreglo() {
        Proceso[] procesos = new Proceso[this.tamano];
        int i = 0;
        
        Nodo nodo = frente;
        while(nodo != null) {
            procesos[i++] = (Proceso) nodo.valor;
            nodo = nodo.siguiente;
        }
        return procesos;
    }
}



