/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

import java.lang.reflect.Array;

/**
 *
 * @author btell
 * @param <T>
 */
public class Lista<T> {
    protected Nodo frente = null;
    protected Nodo finalCola = null;
    protected int tamano = 0;
    final private Class clazz;
    
    public Lista(Class<T> clazz) {
        this.clazz = clazz;
    }

    // Método para agregar un proceso a la cola
    public void insertar(T nuevoProceso) {
        Nodo nuevoNodo = new Nodo<>(nuevoProceso);
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
    public T eliminarFinal() {
        if (frente == null)
            return null; // La cola está vacía
        
        T valor = (T) frente.valor;
        frente = frente.siguiente;
        if (frente == null)
            finalCola = null; // La lista se ha vaciado completamente
        
        tamano--;
        return valor;
    }
    
    public T eliminar(T o) {
        if (frente == null)
            return null;
        
        Nodo anterior = null;
        Nodo auxiliar = frente;
        while(auxiliar != null && ((T)auxiliar.valor).equals(o)) {
            anterior = auxiliar;
            auxiliar = auxiliar.siguiente;
        }
        
        if(auxiliar == null)
            return null;
        
        if(anterior != null) {
            anterior.siguiente = auxiliar.siguiente;
            auxiliar.siguiente = null;
        }
        
        if(auxiliar == frente)
            frente = auxiliar.siguiente;
        else if(auxiliar == finalCola)
            finalCola = anterior;
        
        tamano--;
        return (T) auxiliar.valor;
    }

    public Nodo obtenerInicio() {
        return this.frente;
    }
    
    public int obtenerTamano() {
        return this.tamano;
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }
    
    public T[] comoArreglo() {
        final T[] arreglo = (T[]) Array.newInstance(this.clazz, this.tamano);
        int i = 0;
        
        Nodo nodo = frente;
        while(nodo != null) {
            arreglo[i++] = (T) nodo.valor;
            nodo = nodo.siguiente;
        }
        return arreglo;
    }
    
    public void vaciar() {
        tamano = 0;
        frente = finalCola = null;
    }
}
