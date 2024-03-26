/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public class ListaProcesos {
    private Nodo frente = null;
    private Nodo finalCola = null;
    private int tamano = 0;

    // Método para agregar un proceso a la cola
    public void insertar(Proceso nuevoProceso) {
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
    public Proceso eliminarFinal() {
        if (frente == null)
            return null; // La cola está vacía
        
        Proceso proceso = frente.proceso;
        frente = frente.siguiente;
        if (frente == null)
            finalCola = null; // La lista se ha vaciado completamente
        
        tamano--;
        return proceso;
    }
    
    // Método para remover y retornar el proceso al frente de la cola
    public Proceso eliminar(int idProceso) {
        if (frente == null)
            return null;
        
        Nodo anterior = null;
        Nodo auxiliar = frente;
        while(auxiliar != null && auxiliar.proceso.getIdProceso() != idProceso) {
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
        return auxiliar.proceso;
    }

    // Método para remover y retornar el proceso al frente de la cola
    public Proceso recuperar(int idProceso) {
        if (frente == null) {
            return null; // La cola está vacía
        }
        Nodo auxiliar = frente;
        while(auxiliar != null && auxiliar.proceso.getIdProceso() != idProceso)
            auxiliar = auxiliar.siguiente;
        
        if (auxiliar == null)
            return null;
        
        return auxiliar.proceso;
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
    
    public Proceso[] comoArreglo() {
        Proceso[] procesos = new Proceso[this.tamano];
        int i = 0;
        
        Nodo nodo = frente;
        while(nodo != null) {
            procesos[i++] = nodo.proceso;
            nodo = nodo.siguiente;
        }
        return procesos;
    }
    
    public void vaciar() {
        tamano = 0;
        frente = finalCola = null;
    }
}
